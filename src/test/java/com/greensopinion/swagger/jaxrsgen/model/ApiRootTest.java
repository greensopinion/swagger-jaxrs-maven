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

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.gson.GsonBuilder;
import com.greensopinion.swagger.jaxrsgen.model.ApiModel;
import com.greensopinion.swagger.jaxrsgen.model.ApiRoot;
import com.greensopinion.swagger.jaxrsgen.model.Service;
import com.greensopinion.swagger.jaxrsgen.model.ServiceApi;

public class ApiRootTest {

	@Test
	public void serializedForm() {
		List<Service> services = ImmutableList.of(new Service("1.0.1", "/api/latest", "/pets", "Pet service",
				Collections.<ServiceApi> emptyList(), Collections.<ApiModel> emptyList()));
		ApiRoot root = new ApiRoot("1.0.0", services);

		String json = new GsonBuilder().disableHtmlEscaping().create().toJson(root);
		assertEquals(
				"{\"apiVersion\":\"1.0.0\",\"swaggerVersion\":\"1.2\",\"apis\":[{\"path\":\"/pets\",\"description\":\"Pet service\"}]}",
				json);
	}
}
