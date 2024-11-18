package com.team2.slind.judgement.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final JobLauncher jobLauncher;
    private final Job judgementJob;

    public SchedulerConfig(JobLauncher jobLauncher, Job judgementJob) {
        this.jobLauncher = jobLauncher;
        this.judgementJob = judgementJob;
    }

    @Scheduled(cron = "0 * * * * ?") // 매분마다 실행
    public void runJudgementJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("run.id", System.currentTimeMillis()) // 고유한 파라미터 추가
                    .toJobParameters();

            jobLauncher.run(judgementJob, jobParameters);
            log.info("Judgement cleanup job executed successfully.");
        } catch (Exception e) {
            log.error("Error occurred while executing judgement cleanup job", e);
        }
    }
}
