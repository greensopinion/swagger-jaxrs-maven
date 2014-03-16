/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import java.awt.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

public class ApiTypes {

	public static boolean isModelClass(Class<?> clazz) {
		return !clazz.isPrimitive() && !clazz.getPackage().getName().startsWith("java.")
				&& !clazz.getPackage().getName().startsWith("javax.");
	}

	public static String calculateTypeName(Class<?> parameterType) {
		if (parameterType == Long.class || parameterType == long.class || parameterType == Integer.class
				|| parameterType == int.class) {
			return "integer";
		} else if (parameterType == Float.class || parameterType == float.class || parameterType == Double.class
				|| parameterType == double.class) {
			return "number";
		} else if (parameterType == Byte.class || parameterType == byte.class) {
			return "string";
		} else if (List.class.isAssignableFrom(parameterType) || Set.class.isAssignableFrom(parameterType)
				|| parameterType.isArray()) {
			return "array";
		} else if (parameterType.isPrimitive() || parameterType.getPackage().getName().equals("java.lang")) {
			return parameterType.getSimpleName().toLowerCase();
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
		if (parameterType == Long.class || parameterType == long.class) {
			return "int64";
		} else if (parameterType == Integer.class || parameterType == int.class) {
			return "int32";
		} else if (parameterType == Float.class || parameterType == float.class) {
			return "float";
		} else if (parameterType == Double.class || parameterType == double.class) {
			return "double";
		} else if (parameterType == Byte.class || parameterType == byte.class) {
			return "byte";
		}
		return null;
	}

}
