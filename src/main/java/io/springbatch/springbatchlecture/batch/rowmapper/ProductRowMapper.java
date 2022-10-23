package io.springbatch.springbatchlecture.batch.rowmapper;

import io.springbatch.springbatchlecture.batch.domain.dto.ProductDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<ProductDTO> {

    @Override
    public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ProductDTO.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .type(rs.getString("type"))
                .build();
    }
}
