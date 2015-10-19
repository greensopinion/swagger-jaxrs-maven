/*******************************************************************************
 * Copyright (c) 2015 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.Test;

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
