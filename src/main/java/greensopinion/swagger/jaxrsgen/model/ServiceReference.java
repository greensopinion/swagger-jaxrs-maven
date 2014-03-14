/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;

public class ServiceReference {

	private final String path;

	private final String description;

	public ServiceReference(String path, String description) {
		this.path = checkNotNull(path, "Must provide a path");
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public String getDescription() {
		return description;
	}
}
