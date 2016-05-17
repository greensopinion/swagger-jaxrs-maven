/*******************************************************************************
 * Copyright (c) 2014, 2015 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import io.swagger.annotations.ApiModel;

public class ApiTypes {

	private static final Map<Class<?>, String> typeNameByClass;

	static {
		Map<Class<?>, String> types = Maps.newHashMap();
		types.put(Void.class, "void");
		types.put(void.class, "void");
		types.put(Long.class, "integer");
		types.put(long.class, "integer");
		types.put(Integer.class, "integer");
		types.put(int.class, "integer");
		types.put(Float.class, "number");
		types.put(float.class, "number");
		types.put(Double.class, "number");
		types.put(double.class, "number");
		types.put(Byte.class, "string");
		types.put(byte.class, "string");
		types.put(URI.class, "string");
		types.put(Date.class, "string");
		types.put(List.class, "array");
		types.put(Collection.class, "array");
		typeNameByClass = ImmutableMap.copyOf(types);
	}

	private static final Map<Class<?>, String> typeFormatByClass;

	static {
		Map<Class<?>, String> types = Maps.newHashMap();
		types.put(Long.class, "int64");
		types.put(long.class, "int64");
		types.put(Integer.class, "int32");
		types.put(int.class, "int32");
		types.put(Float.class, "float");
		types.put(float.class, "float");
		types.put(Double.class, "double");
		types.put(double.class, "double");
		types.put(Byte.class, "byte");
		types.put(byte.class, "byte");
		types.put(Date.class, "date-time");
		typeFormatByClass = ImmutableMap.copyOf(types);
	}

	public static boolean isModelClass(Class<?> clazz) {
		return !clazz.isPrimitive() && !clazz.getPackage().getName().startsWith("java.")
				&& !clazz.getPackage().getName().startsWith("javax.")
				&& !clazz.getPackage().getName().startsWith("com.sun.jersey.multipart");
	}

	public static Map<String, String> calculateArrayItems(Class<?> parameterType, Type genericType) {
		Map<String, String> arrayItems;
		if (parameterType.isArray()) {
			String key = isModelClass(parameterType.getComponentType()) ? "$ref" : "type";
			arrayItems = ImmutableMap.of(key, ApiTypes.calculateTypeName(parameterType.getComponentType()));
		} else if (Collection.class.isAssignableFrom(parameterType)) {
			String key = isModelClass(typeArgument(genericType)) ? "$ref" : "type";
			arrayItems = ImmutableMap.of(key, ApiTypes.calculateTypeParameterName(genericType));
		} else {
			return null;
		}
		return arrayItems;
	}

	public static String calculateTypeName(Class<?> parameterType) {
		String typeName = typeNameByClass.get(parameterType);
		if (typeName != null) {
			return typeName;
		}
		if (parameterType.isPrimitive() || parameterType.getPackage().getName().equals("java.lang")) {
			return "string";
		}
		if (parameterType.getName().equals("com.sun.jersey.multipart.MultiPart")) {
			return "File";
		}
		ApiModel apiModel = parameterType.getAnnotation(ApiModel.class);
		typeName = apiModel == null ? null : Strings.emptyToNull(apiModel.value());
		return Objects.firstNonNull(typeName, parameterType.getSimpleName());
	}

	public static Class<?> modelClass(Type type) {
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type typeArgument = parameterizedType.getActualTypeArguments()[0];
			if (typeArgument instanceof Class) {
				return (Class<?>) typeArgument;
			}
		}
		return (Class<?>) type;
	}

	public static String calculateTypeParameterName(Type type) {
		return calculateTypeName(typeArgument(type));
	}

	private static Class<?> typeArgument(Type type) {
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type typeArgument = parameterizedType.getActualTypeArguments()[0];
			if (typeArgument instanceof Class) {
				return (Class<?>) typeArgument;
			}
			throw new IllegalStateException(typeArgument.getClass().getName());
		}
		throw new IllegalStateException(type.getClass().getName());
	}

	public static String calculateTypeFormat(Class<?> parameterType) {
		return typeFormatByClass.get(parameterType);
	}

}
