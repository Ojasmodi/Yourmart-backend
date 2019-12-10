package com.nagarro.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.models.Comment;
import com.nagarro.services.CommentsService;

@Path("/comment")
public class CommentsController {

	@Autowired
	private Comment comment;

	@Autowired
	private CommentsService commentService;

	@POST
	@Path("/add/{prodId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCommentToProduct(Comment newComment,@PathParam("prodId") String prodId) throws Exception {

		comment = commentService.addCommentToProduct(newComment, prodId);
		return Response.status(Status.OK).entity(comment).build();
	}

}
