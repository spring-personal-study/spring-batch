/*
package io.springbatch.springbatchlecture.basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    // Job 생성 빌더 팩토리
    private final JobBuilderFactory jobBuilderFactory;
    // Step 생성 빌더 팩토리
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        // simpleJob이라는 이름으로 Job 생성
        return jobBuilderFactory.get("simpleJob")
                .start(stepFirst())
                .next(stepSecond())
                .next(stepThird())
                .build();
    }

    // Job 구동 -> Step을 실행 -> Tasklet을 실행
    @Bean
    public Step stepFirst() {
        // step1이라는 이름으로 Step 생성
        // tasklet: Step 안에서 단일 태스크로 수행되는 로직 구현
        return stepBuilderFactory.get("step1")
                .tasklet(((contribution, chunkContext) -> {
                    log.info("===== Hello Spring Batch step 1 ===== ");
                    return RepeatStatus.FINISHED;
                })).build();
    }

    @Bean
    public Step stepSecond() {
        // step2이라는 이름으로 Step 생성
        // tasklet: Step 안에서 단일 태스크로 수행되는 로직 구현
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        // StepContribution, ChunkContext 모두 JobParameter를 필드로 가지고 있으므로 parameter들을 참조할 수 있다.
                        // 그러나 반환 타입은 조금 다르다.
                        // StepContribution에서 JobParamter를 불러올떄:
                        JobParameters contributionJobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
                        String name = contributionJobParameters.getString("name");
                        Long seq = contributionJobParameters.getLong("seq");
                        Date date = contributionJobParameters.getDate("date");
                        Double age = contributionJobParameters.getDouble("age");
                        log.info("name: {}, seq: {}, date: {}, age: {}", name, seq, date, age);

                        // ChunkContext에서 JobParamter를 불러올 때:
                        Map<String, Object> chunkContextJobParameters = chunkContext.getStepContext().getJobParameters();

                        for (Map.Entry entry : chunkContextJobParameters.entrySet()) {
                            log.info("{}", entry.getValue());
                        }

                        log.info("===== Hello Spring Batch step 2 =====");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step stepThird() {
        // customStep 이라는 이름으로 Step 생성
        // tasklet: Step 안에서 단일 태스크로 수행되는 로직 구현
        return stepBuilderFactory.get("customStep")
                .tasklet(new CustomTasklet())
                .build();
    }
}
*/
