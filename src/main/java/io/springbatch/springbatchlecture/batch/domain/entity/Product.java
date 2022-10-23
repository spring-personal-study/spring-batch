package io.springbatch.springbatchlecture.batch.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    private Long id;

    private String name;
    private int price;
    private String type;

    public Product() {}
}
