package io.springbatch.springbatchlecture.batch.chunk.writer;

import io.springbatch.springbatchlecture.batch.domain.dto.ApiRequestDTO;
import io.springbatch.springbatchlecture.batch.domain.dto.ApiResponseDTO;
import io.springbatch.springbatchlecture.service.AbstractApiService;
import io.springbatch.springbatchlecture.service.ApiService3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ApiItemWriter3 implements ItemWriter<ApiRequestDTO> {

    private final AbstractApiService apiService;

    @Override
    public void write(List<? extends ApiRequestDTO> items) throws Exception {
        ApiResponseDTO service = apiService.service(items);
        log.info("responseDTO={}", service);
    }
}
