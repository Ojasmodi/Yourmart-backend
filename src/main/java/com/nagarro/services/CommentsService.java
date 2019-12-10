package com.nagarro.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.models.Comment;
import com.nagarro.models.Product;
import com.nagarro.models.Seller;
import com.nagarro.repositories.CommentRepository;
import com.nagarro.utils.SendMail;

@Service
public class CommentsService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private Comment comment;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Product product;
	
	@Autowired
	private SendMail sendMailer;
	
	@Autowired
	private Seller seller;
	

	// method to add a comment to a rejected product
	public Comment addCommentToProduct(Comment newComment, String prodId) throws Exception {
		newComment.setDateOfComment(new Date());
		product=productService.getProductByProductId(prodId);
		seller=product.getSeller();
		newComment.setProduct(product);
		comment=commentRepository.save(newComment);
		sendMailer.sendMail(seller.getEmail());
		return comment;	
	}

}
