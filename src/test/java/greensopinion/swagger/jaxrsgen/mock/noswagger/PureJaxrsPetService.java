/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.mock.noswagger;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
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

import com.sun.jersey.multipart.MultiPart;

import greensopinion.swagger.jaxrsgen.mock.noswagger.model.Pet;
import greensopinion.swagger.jaxrsgen.mock.noswagger.model.PetHandle;
import greensopinion.swagger.jaxrsgen.mock.noswagger.model.PetListing;
import greensopinion.swagger.jaxrsgen.mock.noswagger.model.PetValues;

@Path("/pet")
@Produces(MediaType.APPLICATION_JSON)
public class PureJaxrsPetService {

	@GET
	public PetListing list(@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("count") @DefaultValue("50") int count) {
		return null;
	}

	@GET
	@Path("as-list")
	public List<PetHandle> listAsList(@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("count") @DefaultValue("50") int count) {
		return null;
	}

	@GET
	@Path("as-collection")
	public Collection<PetHandle> listAsCollection(@QueryParam("start") @DefaultValue("0") int start,
			@QueryParam("count") @DefaultValue("50") int count) {
		return null;
	}

	@GET
	@Path("{id}")
	public Pet retrievePet(@PathParam("id") long id) {
		return null;
	}

	@GET
	@Path("{id}/properties")
	public Map<String, Object> retrievePet(@PathParam("id") long id,
			@QueryParam("properties") List<String> properties) {
		return null;
	}

	@DELETE
	@Path("/{id}")
	public void deletePet(@PathParam("id") long id) {
	}

	@PUT
	public PetHandle createPet(PetValues petValues) {
		return null;
	}

	@POST
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updatePet(Pet pet, @PathParam("id") long id) {
	}

	@POST
	@Path("{id}/photo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void postPetPhoto(@PathParam("id") long id, MultiPart multiPart) {
	}

}
