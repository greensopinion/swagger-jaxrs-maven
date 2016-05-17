/*******************************************************************************
 * Copyright (c) 2015 Tasktop Technologies.
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

import java.net.URI;
import java.util.List;

import org.junit.Test;

import com.greensopinion.swagger.jaxrsgen.model.ApiTypes;

import io.swagger.annotations.ApiModel;

public class ApiTypesTest {

	public static class TestModel {

	}

	@ApiModel("test-model")
	public static class TestModel2 {

	}

	@Test
	public void apiTypeName() {
		assertEquals("string", ApiTypes.calculateTypeName(String.class));
		assertEquals("integer", ApiTypes.calculateTypeName(Long.class));
		assertEquals("integer", ApiTypes.calculateTypeName(Integer.class));
		assertEquals("string", ApiTypes.calculateTypeName(URI.class));
		assertEquals("array", ApiTypes.calculateTypeName(List.class));
		assertEquals("TestModel", ApiTypes.calculateTypeName(TestModel.class));
		assertEquals("test-model", ApiTypes.calculateTypeName(TestModel2.class));
	}
}
