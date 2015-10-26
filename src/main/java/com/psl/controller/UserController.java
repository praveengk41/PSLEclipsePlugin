/**
 * 
 */
package com.psl.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * @author praveen_kumatekar
 *
 */
@Path("/hello")
public class UserController {

	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Hello : " + msg;

		return Response.status(200).entity(output).build();

	}

}
