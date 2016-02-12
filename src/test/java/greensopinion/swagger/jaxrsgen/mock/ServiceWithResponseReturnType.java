/*******************************************************************************
 * Copyright (c) 2015, 2016 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/
package greensopinion.swagger.jaxrsgen.mock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import greensopinion.swagger.jaxrsgen.mock.model.PetListing;
import io.swagger.annotations.ApiOperation;

@Path("/pets")
public class ServiceWithResponseReturnType {

	@GET
	@ApiOperation(value = "List all pets", notes = "List all pets. Results are paginated.", response = PetListing.class)
	public Response listPets() {
		throw new UnsupportedOperationException();
	}
}