/*******************************************************************************
 * Copyright (c) 2015, 2016 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.Path;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import greensopinion.swagger.jaxrsgen.mock.ServiceWithLambda;
import greensopinion.swagger.jaxrsgen.mock.ServiceWithResponseReturnType;
import io.swagger.annotations.Api;

public class ClassCollectorTest {

	private final ClassCollector collector = new ClassCollector();

	@Test
	public void findsClasses() {
		collector.addClasses(ClassCollectorTest.class.getClassLoader(), "greensopinion.swagger.jaxrsgen", Api.class);
		collector.addClasses(ClassCollectorTest.class.getClassLoader(), "greensopinion.swagger.jaxrsgen", Path.class);

		assertEquals(ImmutableSet.of(greensopinion.swagger.jaxrsgen.mock.noswagger.PureJaxrsPetService.class,
				greensopinion.swagger.jaxrsgen.mock.PetService.class, ServiceWithLambda.class,
				ServiceWithResponseReturnType.class), collector.getClasses());
	}
}
