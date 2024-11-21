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
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@EnableScheduling
@Service
@RequiredArgsConstructor
public class SchedulerConfig {
    private final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    private final JudgementMapper judgementMapper;
    private final ArticleMapper articleMapper;
    private final BoardMapper boardMapper;

    @Transactional
    @Scheduled(cron = "0 * * * * ?") // 매분마다 실행
    public void runJudgementJob() {
        List<Judgement> judgements = null;
        if (judgements == null) {
            // Load judgements using MyBatis Mapper
            LocalDateTime createdDttm = LocalDateTime.now().minusMinutes(1);
            judgements = judgementMapper.findByStatus();
            logger.info("Loaded {} judgements with status = 'ON'", judgements.size());
        }

        for (Judgement judgement : judgements) {
            int likeCount = judgement.getLikeCount();
            int dislikeCount = judgement.getDislikeCount();
            int totalVotes = likeCount + dislikeCount;
            if (totalVotes >= 6 && likeCount >= dislikeCount*2) {
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
    }
}
