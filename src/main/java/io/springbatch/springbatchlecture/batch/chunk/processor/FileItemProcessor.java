package io.springbatch.springbatchlecture.batch.chunk.processor;

import io.springbatch.springbatchlecture.batch.domain.dto.ProductDTO;
import io.springbatch.springbatchlecture.batch.domain.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;


public class FileItemProcessor implements ItemProcessor<ProductDTO, Product> {
    @Override
    public Product process(ProductDTO item) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(item, Product.class);

        return product;
    }
}
