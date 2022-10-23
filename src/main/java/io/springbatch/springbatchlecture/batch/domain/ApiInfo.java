package io.springbatch.springbatchlecture.batch.domain;

import io.springbatch.springbatchlecture.batch.domain.dto.ApiRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiInfo {

    private String url;
    private List<? extends ApiRequestDTO> apiRequestList;

}
