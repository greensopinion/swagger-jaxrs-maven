/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import java.util.Set;

public abstract class JsonIntrospector {

	public abstract Set<Class<?>> fieldModelClasses(Class<?> modelClass);

	public abstract ApiModel createApiModel(Class<?> modelClass);

}
