package io.springbatch.springbatchlecture.batchapp.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

    private Long id;
    private String name;
    private int price;
    private String type;
}