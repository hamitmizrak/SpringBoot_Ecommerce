package com.hamitmizrak.springboot_ecommerce.data.repository;

//import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository
//public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
//}


import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to convert ResultSet to AddressEntity
    private static class AddressRowMapper implements RowMapper<AddressEntity> {
        @Override
        public AddressEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            AddressEntity address = new AddressEntity();
            address.setId(rs.getLong("id"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setPostalCode(rs.getString("postal_code"));
            return address;
        }
    }

    // Create a new address
    public int save(AddressEntity address) {
        String sql = "INSERT INTO addresses (street, city, state, postal_code) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, address.getStreet(), address.getCity(), address.getState(), address.getPostalCode());
    }

    // Get an address by ID
    public Optional<AddressEntity> findById(Long id) {
        String sql = "SELECT * FROM addresses WHERE id = ?";
        return jdbcTemplate.query(sql, new AddressRowMapper(), id)
                .stream()
                .findFirst();
    }

    // Update an existing address
    public int update(AddressEntity address) {
        String sql = "UPDATE addresses SET street = ?, city = ?, state = ?, postal_code = ? WHERE id = ?";
        return jdbcTemplate.update(sql, address.getStreet(), address.getCity(), address.getState(), address.getPostalCode(), address.getId());
    }

    // Delete an address by ID
    public int deleteById(Long id) {
        String sql = "DELETE FROM addresses WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Get all addresses
    public List<AddressEntity> findAll() {
        String sql = "SELECT * FROM addresses";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    // Get the last inserted ID
    public Long getLastInsertedId() {
        String sql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}


/*
Açıklamalar:
JdbcTemplate: Veritabanına erişmek ve SQL sorgularını çalıştırmak için Spring'in JdbcTemplate sınıfını kullandık.
RowMapper: Veritabanından dönen ResultSet verisini AddressEntity nesnesine dönüştüren bir sınıf.
save(AddressEntity address): Veritabanına yeni bir adres kaydı eklemek için kullanılan SQL sorgusudur.
findById(Long id): Belirli bir ID'ye sahip adresi bulmak için kullanılan SQL sorgusudur.
update(AddressEntity address): Var olan bir adres kaydını güncellemek için kullanılan SQL sorgusudur.
deleteById(Long id): Belirli bir ID'ye sahip adresi silmek için kullanılan SQL sorgusudur.
findAll(): Tüm adresleri listelemek için kullanılan SQL sorgusudur.
Bu yapı, Spring Data JPA yerine JDBC Template kullanarak veritabanı işlemlerini manuel bir şekilde gerçekleştirmeni sağlar.
*
*/