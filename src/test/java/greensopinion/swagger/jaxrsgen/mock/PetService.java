/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.mock;

import greensopinion.swagger.jaxrsgen.mock.model.Pet;
import greensopinion.swagger.jaxrsgen.mock.model.PetHandle;
import greensopinion.swagger.jaxrsgen.mock.model.PetListing;
import greensopinion.swagger.jaxrsgen.mock.model.PetValues;
import greensopinion.swagger.jaxrsgen.mock.model.ServerError;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/pet")
@Api(value = "/pet", description = "Operations about pets")
@Produces(MediaType.APPLICATION_JSON)
public class PetService {

	@GET
	@ApiOperation(value = "List all pets", notes = "List all pets. Results are paginated.", response = PetListing.class)
	public PetListing list(@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("count") @DefaultValue("50") int count) {
		return null;
	}

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Retrieve pet by id", notes = "Retrieves a pet by it's id.", response = Pet.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Pet not found", response = ServerError.class) })
	public Pet retrievePet(@PathParam("id") long id) {
		return null;
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete pet by id", notes = "Deletes a pet by it's id.")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Pet not found", response = ServerError.class) })
	public void deletePet(@PathParam("id") long id) {
	}

	@PUT
	@ApiOperation(value = "Creates a new pet", notes = "Creates a new pet with the given name.", response = PetHandle.class)
	public PetHandle createPet(PetValues petValues) {
		return null;
	}

	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Updates a pet", notes = "Updates an existing pet with the provided details.")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Pet not found", response = ServerError.class) })
	public void updatePet(Pet pet, @PathParam("id") long id) {
	}
}