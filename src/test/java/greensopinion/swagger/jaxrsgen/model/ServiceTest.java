/*******************************************************************************
 * Copyright (c) 2014, 2016 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.gson.GsonBuilder;

import greensopinion.swagger.jaxrsgen.mock.PetService;
import greensopinion.swagger.jaxrsgen.mock.ServiceWithResponseReturnType;

public class ServiceTest {

	@Test
	public void serializedForm() {
		Service service = Service.builder()
				.basePath("/api/latest")
				.path("/pets/{petId}")
				.description("Operations about pets")
				.version("1.0.1")
				.methods(PetService.class)
				.create();
		assertNotNull(service);

		String json = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(service);
		assertEquals(TestResources.read(ServiceTest.class, "serializedForm.json"), json);
	}

	@Test
	public void serviceWithResponseReturnType() {
		Service service = Service.builder()
				.basePath("/api/latest")
				.path("/apath")
				.version("1.0.1")
				.methods(ServiceWithResponseReturnType.class)
				.create();
		assertNotNull(service);

		String json = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(service);
		assertEquals(TestResources.read(ServiceTest.class, "serviceWithResponseReturnType.json"), json);
	}
}
