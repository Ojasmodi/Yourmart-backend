package com.nagarro.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.models.Comment;
import com.nagarro.models.Product;
import com.nagarro.models.Seller;
import com.nagarro.repositories.CommentRepository;

@Service
public class CommentsService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	Comment comment;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Product product;
	

	// method to add a comment to a rejected product
	public Comment addCommentToProduct(Comment newComment, String prodId) throws Exception {
		newComment.setDateOfComment(new Date());
		product=productService.getProductByProductId(prodId);
		newComment.setProduct(product);
		comment=commentRepository.save(newComment);
		return comment;
		
	}

}
