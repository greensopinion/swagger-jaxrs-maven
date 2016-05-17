/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
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
