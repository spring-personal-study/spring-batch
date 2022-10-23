package io.springbatch.springbatchlecture.batch.domain.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiRequestDTO {

    private long id;
    private ProductDTO productDTO;
}
