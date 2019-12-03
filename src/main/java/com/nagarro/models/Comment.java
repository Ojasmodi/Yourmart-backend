package com.nagarro.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // to remove problem of seller->product->seller->product
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long commentId;
	
	String commentValue;
	
	String commentByUserId;
	
	String commentByUserName;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date dateOfComment;
	
	@ManyToOne
	@JsonIgnore
	private Product product;

	public String getCommentByUserName() {
		return commentByUserName;
	}

	public void setCommentByUserName(String commentByUserName) {
		this.commentByUserName = commentByUserName;
	}

	public void setCommentByUserId(String commentByUserId) {
		this.commentByUserId = commentByUserId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getCommentValue() {
		return commentValue;
	}

	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}


	public String getCommentByUserId() {
		return commentByUserId;
	}

	public Date getDateOfComment() {
		return dateOfComment;
	}


	public void setDateOfComment(Date dateOfComment) {
		this.dateOfComment = dateOfComment;
	}
	
	
	
	

}
