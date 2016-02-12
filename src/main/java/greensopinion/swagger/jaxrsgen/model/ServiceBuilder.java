/*******************************************************************************
 * Copyright (c) 2014, 2015 David Green.
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
package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import io.swagger.annotations.ApiOperation;

public class ServiceBuilder {

	private String path;

	private String description;

	private String version;

	private String basePath;

	private List<ServiceApi> serviceApis;

	private List<ApiModel> models;

	ServiceBuilder() {
	}

	public Service create() {
		return new Service(version, basePath, path, description, serviceApis, models);
	}

	public ServiceBuilder version(String version) {
		this.version = checkNotNull(version, "Must provide a version");
		return this;
	}

	public ServiceBuilder path(String path) {
		this.path = checkNotNull(path, "Must provide a path");
		return this;
	}

	public ServiceBuilder description(String description) {
		this.description = checkNotNull(description, "Must provide a description");
		return this;
	}

	public ServiceBuilder basePath(String apiBasePath) {
		this.basePath = checkNotNull(apiBasePath, "Must provide apiBasePath");
		return this;
	}

	public ServiceBuilder models(List<ApiModel> models) {
		this.models = ImmutableList.copyOf(checkNotNull(models, "Must provide models"));
		return this;
	}

	public ServiceBuilder methods(Class<?> clazz) {
		checkNotNull(clazz, "Must provide a class");

		Multimap<String, ServiceOperation> operationByPath = HashMultimap.create();
		for (Method method : clazz.getMethods()) {
			if (isApi(method)) {
				ServiceOperation operation = ServiceOperation.builder().method(method).create();
				operationByPath.put(operation.getPath(), operation);
			}
		}
		Set<Class<?>> modelClasses = Sets.newHashSet();

		List<ServiceApi> serviceApis = Lists.newArrayList();
		for (String path : operationByPath.keySet()) {
			List<ServiceOperation> operations = Lists.newArrayList();
			operations.addAll(operationByPath.get(path));
			Collections.sort(operations);
			ServiceApi serviceApi = new ServiceApi(path, description, operations);
			serviceApis.add(serviceApi);

			modelClasses.addAll(serviceApi.getModelClasses());
		}

		for (Class<?> modelClass : ImmutableSet.copyOf(modelClasses)) {
			modelClasses.addAll(getJsonIntrospector().fieldModelClasses(modelClass));
		}

		Collections.sort(serviceApis);
		this.serviceApis = serviceApis;

		List<ApiModel> models = Lists.newArrayList();
		for (Class<?> modelClass : modelClasses) {
			if (ApiTypes.isModelClass(modelClass)) {
				models.add(getJsonIntrospector().createApiModel(modelClass));
			}
		}
		this.models = models;

		return this;
	}

	private boolean isApi(Method method) {
		ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
		if (apiOperation != null) {
			return true;
		}
		if (isHttpMethod(method)) {
			return true;
		}
		return false;
	}

	boolean isHttpMethod(Method method) {
		return method.getAnnotation(GET.class) != null || method.getAnnotation(PUT.class) != null
				|| method.getAnnotation(POST.class) != null || method.getAnnotation(DELETE.class) != null;
	}

	protected JsonIntrospector getJsonIntrospector() {
		return new GsonIntrospector();
	}

}
