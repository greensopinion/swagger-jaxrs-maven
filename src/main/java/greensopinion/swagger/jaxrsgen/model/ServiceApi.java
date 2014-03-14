/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;

// https://github.com/wordnik/swagger-core/wiki/Api-Declaration
public class ServiceApi implements Comparable<ServiceApi> {

	private final String path;

	private final String description;

	private final List<ServiceOperation> operations;

	ServiceApi(String path, String description, List<ServiceOperation> operations) {
		this.path = path;
		this.description = description;
		this.operations = operations;
	}

	public String getPath() {
		return path;
	}

	public List<ServiceOperation> getOperations() {
		return operations;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int compareTo(ServiceApi o) {
		int i = path.compareTo(o.path);
		checkState(i != 0, "Two service APIs have the same path");
		return i;
	}
}
