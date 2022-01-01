package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    // Job 생성 빌더 팩토리
    private final JobBuilderFactory jobBuilderFactory;
    // Step 생성 빌더 팩토리
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        // helloJob이라는 이름으로 Job 생성
        return jobBuilderFactory.get("helloJob")
                .start(helloStepFirst())
                .next(helloStepSecond())
                .build();
    }

    // Job 구동 -> Step을 실행 -> Tasklet을 실행
    @Bean
    public Step helloStepFirst() {
        // helloStep1이라는 이름으로 Step 생성
        // tasklet: Step 안에서 단일 태스크로 수행되는 로직 구현
        return stepBuilderFactory.get("helloStep1")
                .tasklet(((contribution, chunkContext) -> {
                    log.info("===== Hello Spring Batch step 1 ===== ");
                    return RepeatStatus.FINISHED;
                })).build();
    }

    @Bean
    public Step helloStepSecond() {
        // helloStep2이라는 이름으로 Step 생성
        // tasklet: Step 안에서 단일 태스크로 수행되는 로직 구현
        return stepBuilderFactory.get("helloStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        log.info("===== Hello Spring Batch step 2 =====");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
