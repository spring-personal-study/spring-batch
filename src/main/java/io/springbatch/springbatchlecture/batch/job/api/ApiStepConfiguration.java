package io.springbatch.springbatchlecture.batch.job.api;

import io.springbatch.springbatchlecture.batch.chunk.processor.ApiItemProcessor1;
import io.springbatch.springbatchlecture.batch.chunk.processor.ApiItemProcessor2;
import io.springbatch.springbatchlecture.batch.chunk.processor.ApiItemProcessor3;
import io.springbatch.springbatchlecture.batch.chunk.writer.ApiItemWriter1;
import io.springbatch.springbatchlecture.batch.chunk.writer.ApiItemWriter2;
import io.springbatch.springbatchlecture.batch.chunk.writer.ApiItemWriter3;
import io.springbatch.springbatchlecture.batch.classifier.ProcessorClassifier;
import io.springbatch.springbatchlecture.batch.classifier.WriterClassifier;
import io.springbatch.springbatchlecture.batch.domain.dto.ApiRequestDTO;
import io.springbatch.springbatchlecture.batch.domain.dto.ProductDTO;
import io.springbatch.springbatchlecture.batch.domain.entity.Product;
import io.springbatch.springbatchlecture.batch.partition.ProductPartitioner;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ApiStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private final int chunkSize = 10;

    @Bean
    public Step apiMasterStep() throws Exception {
        return stepBuilderFactory.get("apiMasterStep")
                .partitioner(apiSlaveStep().getName(), partitioner())
                .step(apiSlaveStep())
                .gridSize(3)
                .taskExecutor(taskExecutor())
                .build();
    }


    @Bean
    public Step apiSlaveStep() throws Exception {
        return stepBuilderFactory.get("apiSlaveStep")
                .<ProductDTO, Product>chunk(chunkSize)
                .reader(itemReader(null))
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Partitioner partitioner() {
        ProductPartitioner productPartitioner = new ProductPartitioner();
        productPartitioner.setDataSource(dataSource);

        return productPartitioner;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(6);
        taskExecutor.setThreadNamePrefix("api-thread");
        return taskExecutor;
    }

    @Bean
    @StepScope
    public ItemReader<ProductDTO> itemReader(@Value("#{stepExecutionContext['product']}") ProductDTO productDTO) throws Exception {

        JdbcPagingItemReader<ProductDTO> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(dataSource);
        reader.setPageSize(chunkSize);
        reader.setRowMapper(new BeanPropertyRowMapper<>(ProductDTO.class));

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, name, price, type");
        queryProvider.setFromClause("from product");
        queryProvider.setWhereClause("where type = :type");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);

        reader.setParameterValues(QueryGenerator.getParameterForQuery("type", productDTO.getType()));
        reader.setQueryProvider(queryProvider);
        reader.afterPropertiesSet();

        return reader;
    }

    @Bean
    public ItemProcessor itemProcessor() {
        ClassifierCompositeItemProcessor<ProductDTO, ApiRequestDTO> processor = new ClassifierCompositeItemProcessor<ProductDTO, ApiRequestDTO>();
        ProcessorClassifier<ProductDTO, ItemProcessor<?, ? extends ApiRequestDTO>> classifier = new ProcessorClassifier();
        Map<String, ItemProcessor<ProductDTO, ApiRequestDTO>> processorMap = new HashMap<>();
        processorMap.put("1", new ApiItemProcessor1());
        processorMap.put("2", new ApiItemProcessor2());
        processorMap.put("3", new ApiItemProcessor3());
        classifier.setProcessorMap(processorMap);
        processor.setClassifier(classifier);

        return processor;
    }

    @Bean
    public ItemWriter itemWriter() {
        ClassifierCompositeItemWriter<ApiRequestDTO> processor = new ClassifierCompositeItemWriter<>();
        WriterClassifier<ApiRequestDTO, ItemWriter<? super ApiRequestDTO>> classifier = new WriterClassifier<>();
        Map<String, ItemWriter<ApiRequestDTO>> writerMap = new HashMap<>();
        writerMap.put("1", new ApiItemWriter1());
        writerMap.put("2", new ApiItemWriter2());
        writerMap.put("3", new ApiItemWriter3());
        classifier.setWriterMap(writerMap);
        processor.setClassifier(classifier);

        return processor;
    }
}
