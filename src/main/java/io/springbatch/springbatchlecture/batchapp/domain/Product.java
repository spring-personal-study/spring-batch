package io.springbatch.springbatchlecture.batchapp.domain;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
public class Product {

    @Id
    private Long id;
    private String name;
    private int price;
    private String type;
}