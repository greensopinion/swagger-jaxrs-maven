/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.CharStreams;

public class TestResources {

	public static String read(Class<?> clazz, String resource) {
		String filename = clazz.getSimpleName() + "_" + resource;
		try {
			InputStream stream = clazz.getResourceAsStream(filename);
			checkNotNull(stream, "Cannot load resource: %s", filename);
			try {
				return CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
			} finally {
				stream.close();
			}
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
