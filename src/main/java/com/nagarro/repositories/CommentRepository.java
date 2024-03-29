package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
