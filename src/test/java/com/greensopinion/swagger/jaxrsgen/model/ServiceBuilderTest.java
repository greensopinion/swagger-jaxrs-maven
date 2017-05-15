/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greensopinion.swagger.jaxrsgen.mock.PetService;
import com.greensopinion.swagger.jaxrsgen.mock.noswagger.PureJaxrsPetService;

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
		assertEquals(TestResources.read(ServiceBuilderTest.class,
				"pureJaxRsSerializedForm-" + PureJaxrsPetService.class.getSimpleName() + ".json"), json2);

	}
}
