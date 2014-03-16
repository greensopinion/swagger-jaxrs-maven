/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static org.junit.Assert.assertEquals;
import greensopinion.swagger.jaxrsgen.mock.PetService;

import org.junit.Test;

public class ServiceBuilderTest {

	@Test
	public void create() {
		Service service = Service.builder()
				.path("/test")
				.basePath("/one/two")
				.description("desc")
				.version("1.2.3")
				.methods(PetService.class)
				.create();
		assertEquals("1.2.3", service.getApiVersion());
		assertEquals("/test", service.getPath());
		assertEquals("/one/two", service.getBasePath());
		assertEquals("desc", service.getDescription());
	}
}
