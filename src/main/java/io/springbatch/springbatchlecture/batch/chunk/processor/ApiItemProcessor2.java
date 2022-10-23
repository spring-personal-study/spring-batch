package io.springbatch.springbatchlecture.batch.chunk.processor;


import io.springbatch.springbatchlecture.batch.domain.dto.ApiRequestDTO;
import io.springbatch.springbatchlecture.batch.domain.dto.ProductDTO;
import org.springframework.batch.item.ItemProcessor;

public class ApiItemProcessor2 implements ItemProcessor<ProductDTO, ApiRequestDTO> {

    @Override
    public ApiRequestDTO process(ProductDTO item) throws Exception {
        return ApiRequestDTO.builder()
                .id(item.getId())
                .productDTO(item)
                .build();
    }
}
