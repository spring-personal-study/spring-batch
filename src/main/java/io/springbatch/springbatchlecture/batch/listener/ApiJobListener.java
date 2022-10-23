package io.springbatch.springbatchlecture.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class ApiJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long spendTime = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        log.info("total spend time: {} ms", spendTime);
    }
}
