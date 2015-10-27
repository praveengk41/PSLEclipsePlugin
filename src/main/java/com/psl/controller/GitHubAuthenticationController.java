package com.psl.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author praveen_kumatekar
 *
 */
@Path("/Outh/authentication")
public class GitHubAuthenticationController {

	final private String URL = "https://github.com/login/oauth/access_token";
	final private String clientId = "13a5bec734f0b4f91435";
	final private String clientSecret = "ff00139c8507f5a49180ffd9726258cff6589412";
	final private String redirectURL = "http://localhost:9090/PslEclipsePlugin/Outh/authentication";

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMsg(@QueryParam("code") String code) {
		Client client = Client.create();
		WebResource webResource = client.resource(URL);
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("client_id", clientId);
		queryParams.add("client_secret", clientSecret);
		queryParams.add("code", code);
		queryParams.add("redirect_uri", redirectURL);
		ClientResponse response = webResource.queryParams(queryParams)
				.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		return Response.status(200).entity(response.getEntity(String.class))
				.build();
	}
}
