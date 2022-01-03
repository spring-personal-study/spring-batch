/*
package io.springbatch.springbatchlecture.basic.customer;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Customer.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .birthdate(rs.getDate("birthdate"))
                .build();
    }
}
*/
