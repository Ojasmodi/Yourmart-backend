package com.nagarro.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
