/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.mock.noswagger.model;

import java.net.URI;
import java.util.Date;

public class Pet extends PetValues {

	private long id;

	private URI url;

	private Date created;

	public long getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public URI getUrl() {
		return url;
	}

}
