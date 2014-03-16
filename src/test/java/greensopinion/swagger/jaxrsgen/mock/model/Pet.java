/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.mock.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class Pet extends PetValues {

	@ApiModelProperty(required = true)
	private long id;

	public long getId() {
		return id;
	}
}
