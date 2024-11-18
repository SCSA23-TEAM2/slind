package com.team2.slind.judgement.batch;

import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.board.mapper.BoardMapper;
import com.team2.slind.judgement.mapper.JudgementMapper;
import com.team2.slind.judgement.vo.Judgement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class BatchConfig extends DefaultBatchConfiguration {
    private Logger logger = LoggerFactory.getLogger(BatchConfig.class);
    private final int CHUNK_SIZE = 3;
    private final JudgementMapper judgementMapper;
    private final ArticleMapper articleMapper;
    private final BoardMapper boardMapper;
    private final DataSource dataSource;


    @Bean
    public Job judgementJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("judgementCleanupJob", jobRepository)
                .start(judgementStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step judgementStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("judgementCleanupStep", jobRepository)
                .<Judgement, Judgement>chunk(CHUNK_SIZE, transactionManager)
                .reader(judgementReader())
                .processor(judgementProcessor())
                .writer(judgementWriter())
                .build();
    }
    @Bean
    public ItemReader<Judgement> judgementReader() {
        return new ItemReader<>() {
            private List<Judgement> judgements = null;
            private int nextIndex = 0;

            @Override
            public Judgement read() {
                if (judgements == null) {
                    // Load judgements using MyBatis Mapper
                    LocalDateTime createdDttm = LocalDateTime.now().minusMinutes(1);
                    judgements = judgementMapper.findByStatus();
                    logger.info("Loaded {} judgements with status = 'ON'", judgements.size());
                }

                if (nextIndex < judgements.size()) {
                    return judgements.get(nextIndex++);
                } else {
                    return null; // No more items
                }
            }
        };
    }


    @Bean
    public ItemProcessor<Judgement, Judgement> judgementProcessor() {
        return judgement -> {
            logger.info("Jugement in Progressor : {}", judgement.getJudgementPk());
            logger.info("Jugement in Progressor Filtering : {}", judgement.getJudgementPk());

            // 상태 처리 로직 추가
            return judgement;
        };
    }

    @Bean
    public ItemWriter<Judgement> judgementWriter() {
        return items -> {
            for (Judgement judgement : items) {
                int likeCount = judgement.getLikeCount();
                int dislikeCount = judgement.getDislikeCount();
                int totalVotes = likeCount + dislikeCount;
                if (totalVotes >= 1000 && likeCount >= dislikeCount*2) {
                    logger.info("Judgement {} marked as 승소", judgement.getJudgementPk());
                    judgementMapper.finishJudgementWin(judgement.getJudgementPk());
                    if (judgement.getArticlePk() != null) {
                        // Article 삭제
                        articleMapper.deleteArticle(judgement.getArticlePk());
                        logger.info("Deleted Article: {}", judgement.getArticlePk());
                    } else if (judgement.getBoardPk() != null) {
                        // Board 삭제

                        boardMapper.deleteByBoardPk(judgement.getBoardPk());
                        logger.info("Deleted Board: {}", judgement.getBoardPk());
                    }
                }else{
                    judgementMapper.finishJudgementLose(judgement.getJudgementPk());

                }
                logger.info("Finished Judgement : {}", judgement.getJudgementPk());
            }
        };
    }
}
