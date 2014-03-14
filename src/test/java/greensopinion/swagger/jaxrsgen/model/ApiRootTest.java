/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.gson.GsonBuilder;

public class ApiRootTest {

	@Test
	public void serializedForm() {
		List<Service> services = ImmutableList.of(new Service("1.0.1", "/api/latest", "/pets", "Pet service",
				Collections.<ServiceApi> emptyList()));
		ApiRoot root = new ApiRoot("1.0.0", services);

		String json = new GsonBuilder().disableHtmlEscaping().create().toJson(root);
		assertEquals(
				"{\"apiVersion\":\"1.0.0\",\"swaggerVersion\":\"1.2\",\"apis\":[{\"path\":\"/pets\",\"description\":\"Pet service\"}]}",
				json);
	}
}
