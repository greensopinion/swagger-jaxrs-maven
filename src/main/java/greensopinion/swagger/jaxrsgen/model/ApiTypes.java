/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

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
				&& !clazz.getPackage().getName().startsWith("javax.");
	}

	public static String calculateTypeName(Class<?> parameterType) {
		String typeName = typeNameByClass.get(parameterType);
		if (typeName != null) {
			return typeName;
		}
		if (parameterType.isPrimitive() || parameterType.getPackage().getName().equals("java.lang")) {
			return "string";
		}
		return parameterType.getSimpleName();
	}

	public static String calculateTypeParameterName(Type type) {
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type typeArgument = parameterizedType.getActualTypeArguments()[0];
			if (typeArgument instanceof Class) {
				return calculateTypeName((Class<?>) typeArgument);
			}
			throw new IllegalStateException(typeArgument.getClass().getName());
		}
		throw new IllegalStateException(type.getClass().getName());
	}

	public static String calculateTypeFormat(Class<?> parameterType) {
		return typeFormatByClass.get(parameterType);
	}

}
