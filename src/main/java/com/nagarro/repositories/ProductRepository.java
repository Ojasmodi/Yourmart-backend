package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
