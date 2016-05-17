/*******************************************************************************
 * Copyright (c) 2015, 2016 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.Path;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.greensopinion.swagger.jaxrsgen.ClassCollector;
import com.greensopinion.swagger.jaxrsgen.mock.ServiceWithLambda;
import com.greensopinion.swagger.jaxrsgen.mock.ServiceWithResponseReturnType;

import io.swagger.annotations.Api;

public class ClassCollectorTest {

	private final ClassCollector collector = new ClassCollector();

	@Test
	public void findsClasses() {
		collector.addClasses(ClassCollectorTest.class.getClassLoader(), "com.greensopinion.swagger.jaxrsgen", Api.class);
		collector.addClasses(ClassCollectorTest.class.getClassLoader(), "com.greensopinion.swagger.jaxrsgen", Path.class);

		assertEquals(
				ImmutableList.of(com.greensopinion.swagger.jaxrsgen.mock.PetService.class, ServiceWithLambda.class,
						ServiceWithResponseReturnType.class,
						com.greensopinion.swagger.jaxrsgen.mock.noswagger.PureJaxrsPetService.class),
				collector.getClasses());
	}
}
