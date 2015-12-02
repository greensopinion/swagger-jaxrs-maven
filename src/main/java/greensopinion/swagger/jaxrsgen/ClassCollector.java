/*******************************************************************************
 * Copyright (c) 2015 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import com.google.common.collect.ImmutableSet;

public class ClassCollector {

	private final Set<Class<?>> classes = new HashSet<>();

	public void addClasses(ClassLoader classLoader, String packageName, Class<? extends Annotation> annotation) {
		checkNotNull(classLoader);
		checkNotNull(packageName);
		checkNotNull(annotation);
		ConfigurationBuilder configurationBuilder = ConfigurationBuilder.build(packageName, classLoader);
		Reflections reflections = new Reflections(configurationBuilder);

		classes.addAll(reflections.getTypesAnnotatedWith(annotation));
	}

	public Set<Class<?>> getClasses() {
		return ImmutableSet.copyOf(classes);
	}
}
