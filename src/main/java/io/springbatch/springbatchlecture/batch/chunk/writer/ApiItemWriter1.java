package io.springbatch.springbatchlecture.batch.chunk.writer;

import io.springbatch.springbatchlecture.batch.domain.dto.ApiRequestDTO;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ApiItemWriter1 implements ItemWriter<ApiRequestDTO> {

    @Override
    public void write(List<? extends ApiRequestDTO> items) throws Exception {

    }
}
