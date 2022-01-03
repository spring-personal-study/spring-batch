
package io.springbatch.springbatchlecture;

import io.springbatch.springbatchlecture.basic.JobConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = {JobConfiguration.class})
class SpringBatchLectureApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobLauncher jobLauncher;


    @Test
    public void test() {
        System.out.println("");

        // Assertions.assertThat(getJobTester("job")).isNotNull();

    }

    public JobLauncherTestUtils getJobTester(String jobName) {
        Job bean = applicationContext.getBean(jobName, Job.class);
        JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        jobLauncherTestUtils.setJobRepository(jobRepository);
        jobLauncherTestUtils.setJob(bean);
        return jobLauncherTestUtils;
    }

}

