/*******************************************************************************
 * Copyright (c) 2014, 2016 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public class ServiceOperationBuilder {

	private String path;

	private String httpMethod;

	private String nickname;

	private String summary;

	private List<String> produces;

	private String notes;

	private List<Parameter> parameters;

	private String type;

	private Class<?> typeClass;

	private List<ResponseMessage> responseMessages;

	private Map<String, String> arrayItems;

	ServiceOperationBuilder() {
	}

	public ServiceOperationBuilder method(Method method) {
		path(calculatePath(method.getDeclaringClass(), method));
		httpMethod(calculateHttpMethod(method));
		nickname(method.getName());
		produces(calculateProduces(method));
		type(ApiTypes.calculateTypeName(method.getReturnType()));

		typeClass = method.getReturnType();

		Class<?>[] parameterTypes = method.getParameterTypes();
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		for (int x = 0; x < parameterTypes.length; ++x) {
			addParameter(parameterTypes[x], genericParameterTypes[x], parameterAnnotations[x]);
		}

		ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
		if (apiOperation != null) {
			summary(apiOperation.value());
			notes(apiOperation.notes());
			if (apiOperation.response() != Void.class) {
				type(ApiTypes.calculateTypeName(apiOperation.response()));
				typeClass = apiOperation.response();
			}
		}

		ApiResponses apiResponses = method.getAnnotation(ApiResponses.class);
		if (apiResponses != null && apiResponses.value().length > 0) {
			responseMessages = Lists.newArrayList();
			for (ApiResponse response : apiResponses.value()) {
				responseMessages.add(new ResponseMessage(response.code(), response.message(),
						response.response() == Void.class ? null : ApiTypes.calculateTypeName(response.response()),
						response.response()));
			}
		}

		arrayItems = ApiTypes.calculateArrayItems(method.getReturnType(), method.getGenericReturnType());

		return this;
	}

	void addParameter(Class<?> parameterType, Type genericParameterType, Annotation[] annotations) {
		QueryParam queryParam = getAnnotation(annotations, QueryParam.class);
		PathParam pathParam = getAnnotation(annotations, PathParam.class);
		DefaultValue defaultValueAnnotation = getAnnotation(annotations, DefaultValue.class);

		String paramType;
		String name;
		String defaultValue = defaultValueAnnotation == null ? null : defaultValueAnnotation.value();
		boolean required = defaultValue == null;
		boolean allowMultiple = false;
		String type = ApiTypes.calculateTypeName(parameterType);
		String format = ApiTypes.calculateTypeFormat(parameterType);
		String description = null;
		String allowableValues = null;
		Map<String, String> arrayItems = null;

		if (queryParam != null) {
			paramType = "query";
			name = queryParam.value();
			arrayItems = ApiTypes.calculateArrayItems(parameterType, genericParameterType);
			allowMultiple = arrayItems != null;
		} else if (pathParam != null) {
			paramType = "path";
			name = pathParam.value();
		} else {
			paramType = "body";
			name = "body";
		}
		ApiParam apiParam = getAnnotation(annotations, ApiParam.class);
		if (apiParam != null) {
			if (apiParam.hidden()) {
				return;
			}
			name = firstNonEmpty(apiParam.name(), name);
			description = firstNonEmpty(apiParam.value(), description);
			defaultValue = firstNonEmpty(apiParam.defaultValue(), defaultValue);
			allowableValues = firstNonEmpty(apiParam.allowableValues(), allowableValues);
			if (defaultValue == null) {
				required = apiParam.required();
			}
			if (apiParam.allowMultiple()) {
				allowMultiple = apiParam.allowMultiple();
			}
		}

		parameter(new Parameter(name, defaultValue, required, allowMultiple, type, parameterType, format, paramType,
				arrayItems, allowableValues, description));
	}

	private void parameter(Parameter parameter) {
		if (parameters == null) {
			parameters = Lists.<Parameter> newArrayList();
		}
		parameters.add(parameter);
	}

	private String firstNonEmpty(String value1, String value2) {
		if (!Strings.isNullOrEmpty(value1)) {
			return value1;
		}
		return value2;
	}

	@SuppressWarnings("unchecked")
	private <T extends Annotation> T getAnnotation(Annotation[] annotations, Class<T> annotationType) {
		for (Annotation annotation : annotations) {
			if (annotationType.isAssignableFrom(annotation.getClass())) {
				return (T) annotation;
			}
		}
		return null;
	}

	public ServiceOperationBuilder type(String type) {
		this.type = type;
		return this;
	}

	public ServiceOperationBuilder summary(String summary) {
		this.summary = summary;
		return this;
	}

	public ServiceOperationBuilder notes(String notes) {
		this.notes = notes;
		return this;
	}

	public ServiceOperationBuilder httpMethod(String httpMethod) {
		this.httpMethod = checkNotNull(httpMethod, "Must proviee an httpMethod");
		return this;
	}

	public ServiceOperationBuilder nickname(String nickname) {
		this.nickname = checkNotNull(nickname, "Must provide a nickname");
		return this;
	}

	public ServiceOperationBuilder path(String path) {
		this.path = checkNotNull(path, "Must provide a path");
		return this;
	}

	public ServiceOperationBuilder produces(List<String> mediaTypes) {
		this.produces = ImmutableList.copyOf(checkNotNull(mediaTypes, "Must provide mediaTypes"));
		return this;
	}

	public ServiceOperation create() {
		return new ServiceOperation(path, httpMethod, nickname, summary, produces, notes, type, arrayItems, typeClass,
				parameters, responseMessages);
	}

	private List<String> calculateProduces(Method method) {
		Produces produces = method.getAnnotation(Produces.class);
		if (produces == null) {
			produces = method.getDeclaringClass().getAnnotation(Produces.class);
		}
		if (produces != null) {
			return Lists.newArrayList(produces.value());
		}
		return Collections.emptyList();
	}

	private String calculateHttpMethod(Method method) {
		if (method.getAnnotation(GET.class) != null) {
			return "GET";
		} else if (method.getAnnotation(PUT.class) != null) {
			return "PUT";
		} else if (method.getAnnotation(DELETE.class) != null) {
			return "DELETE";
		} else if (method.getAnnotation(POST.class) != null) {
			return "POST";
		}
		throw new IllegalStateException(MessageFormat.format("Unable to determine HTTP method of Java method: {0}.{1}",
				method.getDeclaringClass().getName(), method.getName()));
	}

	private String calculatePath(Class<?> clazz, Method method) {
		Path path = clazz.getAnnotation(Path.class);
		String apiPath = path.value();
		Api api = clazz.getAnnotation(Api.class);
		if (api != null) {
			apiPath = api.value();
		}
		Path methodPath = method.getAnnotation(Path.class);
		if (methodPath != null) {
			apiPath += '/' + stripLeadingSlash(methodPath.value());
		}
		return apiPath;
	}

	private String stripLeadingSlash(String value) {
		if (value.startsWith("/")) {
			return value.substring(1);
		}
		return value;
	}
}
