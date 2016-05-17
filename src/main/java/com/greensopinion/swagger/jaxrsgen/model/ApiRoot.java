/*******************************************************************************
 * Copyright (c) 2014 David Green.
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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ApiRoot {

	private final String apiVersion;

	private final String swaggerVersion = Swagger.VERSION;

	private final transient List<Service> services;

	private final List<ServiceReference> apis;

	public ApiRoot(String apiVersion, List<Service> services) {
		this.apiVersion = checkNotNull(apiVersion, "Must provide apiVersion");
		this.services = ImmutableList.copyOf(services);
		List<ServiceReference> apis = Lists.newArrayList();
		for (Service service : services) {
			apis.add(new ServiceReference(service.getPath(), service.getDescription()));
		}
		this.apis = ImmutableList.copyOf(apis);
	}

	public List<ServiceReference> getApis() {
		return apis;
	}

	public List<Service> getServices() {
		return services;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public String getSwaggerVersion() {
		return swaggerVersion;
	}

	public Set<Class<?>> getModelClasses() {
		Set<Class<?>> modelClasses = Sets.newHashSet();
		for (Service service : getServices()) {
			modelClasses.addAll(service.getModelClasses());
		}
		return ImmutableSet.copyOf(modelClasses);
	}

	public static ApiBuilder builder() {
		return new ApiBuilder();
	}
}
