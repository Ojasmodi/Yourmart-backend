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
	
	@Autowired
	Seller seller;
	
	@Autowired 
	SellerService sellerService;


	public Comment addCommentToProduct(Comment newComment, String prodId) throws Exception {
		newComment.setDateOfComment(new Date());
		System.out.println(newComment.getCommentByUserId());
		product=productService.getProductByProductId(prodId);
		System.out.println(product);
		newComment.setProduct(product);
		System.out.println(newComment);
		comment=commentRepository.save(newComment);
		//product.getComments().add(comment);
		//System.out.println(product);
		//product=productService.getProductByProductId(newComment.getCommentByUserId());
		//product.getComments().add(comment);
		//productService.saveProduct(product);
		return comment;
		
	}

}
