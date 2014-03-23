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
import greensopinion.swagger.jaxrsgen.mock.noswagger.PureJaxrsPetService;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	@Test
	public void pureJaxRsSerializedForm() {
		ApiBuilder builder = ApiRoot.builder().version("1.0").basePath("/api/lastest");
		builder.service(PureJaxrsPetService.class);
		ApiRoot apiRoot = builder.create();

		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		String json = gson.toJson(apiRoot);
		assertEquals(TestResources.read(ServiceBuilderTest.class, "pureJaxRsSerializedForm-api-root.json"), json);

		List<Service> services = apiRoot.getServices();
		assertEquals(1, services.size());
		String json2 = gson.toJson(services.get(0));
		assertEquals(
				TestResources.read(ServiceBuilderTest.class,
						"pureJaxRsSerializedForm-" + PureJaxrsPetService.class.getSimpleName() + ".json"), json2);

	}
}
