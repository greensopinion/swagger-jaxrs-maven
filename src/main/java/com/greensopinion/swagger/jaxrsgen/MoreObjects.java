/*******************************************************************************
 * Copyright (c) 2017 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen;

public class MoreObjects {

	public static <T> T firstNonNull(T first, T second) {
		return first == null ? second : first;
	}
}
