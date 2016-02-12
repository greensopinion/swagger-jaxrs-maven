/*******************************************************************************
 * Copyright (c) 2014, 2015 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.mock.model;

import io.swagger.annotations.ApiModelProperty;

public class PetValues {

	@ApiModelProperty(value = "The name of the pet", required = true)
	private String name;

	@ApiModelProperty(value = "The kind of pet", required = true)
	private PetKind kind;

	private String notes;

	public String getName() {
		return name;
	}

	public PetKind getKind() {
		return kind;
	}

	public String getNotes() {
		return notes;
	}

}
