package com.greensopinion.swagger.integrationtest.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("simple-path")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleWebService {

	@GET
	@Path("{id}")
	public SimpleModel retrieve(@PathParam("id") String id) {
		return new SimpleModel(id, "Model " + id);
	}
}
