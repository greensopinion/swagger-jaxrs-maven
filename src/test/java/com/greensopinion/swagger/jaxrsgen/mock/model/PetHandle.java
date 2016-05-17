/*******************************************************************************
 * Copyright (c) 2014, 2015 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen.mock.model;

import io.swagger.annotations.ApiModelProperty;

public class PetHandle {

	@ApiModelProperty(required = true)
	private long id;

	@ApiModelProperty(value = "The name of the pet", required = false)
	private String name;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
