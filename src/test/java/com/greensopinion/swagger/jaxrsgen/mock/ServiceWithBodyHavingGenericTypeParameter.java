/*******************************************************************************
 * Copyright (c) 2017 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen.mock;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.greensopinion.swagger.jaxrsgen.mock.model.Pet;

@Path("/bodyWithGenericTypeParameter")
public class ServiceWithBodyHavingGenericTypeParameter {

	@POST
	public void get(List<Pet> pets) {
	}
}
