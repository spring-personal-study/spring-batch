package io.springbatch.springbatchlecture.batch.domain.dto;

import lombok.*;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private int price;
    private String type;

    public ProductDTO() {}

    @Builder
    public ProductDTO(Long id, String name, int price, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
