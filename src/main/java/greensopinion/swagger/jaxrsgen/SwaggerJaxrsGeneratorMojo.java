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
package greensopinion.swagger.jaxrsgen;

import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import greensopinion.swagger.jaxrsgen.model.ApiBuilder;
import greensopinion.swagger.jaxrsgen.model.ApiRoot;
import greensopinion.swagger.jaxrsgen.model.Service;
import io.swagger.annotations.Api;

/**
 * @goal generate
 * @phase compile
 * @requiresDependencyResolution compile+runtime
 */
public class SwaggerJaxrsGeneratorMojo extends AbstractMojo {

	/**
	 * @parameter
	 */
	protected List<String> packageNames = Lists.newArrayList();

	/**
	 * Output folder.
	 * 
	 * @parameter expression="${project.build.directory}/generated-swagger-docs"
	 * @required
	 */
	protected File outputFolder;

	/**
	 * @parameter
	 */
	protected String apiVersion = "0.1";

	/**
	 * @parameter
	 */
	protected String apiBasePath = "/api/latest";

	/**
	 * @parameter property="project"
	 * @required
	 * @readonly
	 */
	protected MavenProject mavenProject;

	/**
	 * @parameter
	 */
	protected boolean excludeUnannotatedApi = false;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		checkState(!packageNames.isEmpty(), "Must specify package names");

		ClassLoader classLoader = getProjectClassLoader();

		ApiBuilder builder = ApiRoot.builder().version(apiVersion).basePath(apiBasePath);

		ClassCollector collector = new ClassCollector();
		for (String packageName : packageNames) {
			collector.addClasses(classLoader, packageName, Api.class);
			if (!excludeUnannotatedApi) {
				collector.addClasses(classLoader, packageName, Path.class);
			}
		}
		for (Class<?> apiClass : collector.getClasses()) {
			getLog().info("API class: " + apiClass.getName());
			builder.service(apiClass);
		}
		ApiRoot apiRoot = builder.create();
		output(apiRoot);
	}

	private ClassLoader getProjectClassLoader() {
		List<URL> urls = Lists.newArrayList();
		try {
			for (Artifact artifact : mavenProject.getArtifacts()) {
				if (artifact.getArtifactId().contains("swagger")) {
					continue;
				}
				urls.add(artifact.getFile().toURI().toURL());

			}
			for (String classpathElement : mavenProject.getRuntimeClasspathElements()) {
				urls.add(new File(classpathElement).toURI().toURL());
			}
		} catch (MalformedURLException e) {
			throw Throwables.propagate(e);
		} catch (DependencyResolutionRequiredException e) {
			throw Throwables.propagate(e);
		}
		return new URLClassLoader(urls.toArray(new URL[urls.size()]), SwaggerJaxrsGeneratorMojo.class.getClassLoader());
	}

	private void output(ApiRoot apiRoot) throws MojoExecutionException {
		File file = new File(outputFolder, "api-docs/index.json");
		output(file, apiRoot);
		for (Service service : apiRoot.getServices()) {
			File serviceFile = new File(file.getParentFile(),
					CharMatcher.is('/').trimFrom(service.getPath()) + "/index.json");
			output(serviceFile, service);
		}
	}

	private void output(File file, Object model) throws MojoExecutionException {
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				throw new MojoExecutionException("Cannot create folder " + file.getParentFile());
			}
		}
		OutputStreamWriter writer;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);
		} catch (FileNotFoundException e) {
			throw new MojoExecutionException("Cannot create file: " + file, e);
		}
		try {
			try {
				Gson gson = createGson();
				gson.toJson(model, writer);
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			throw new MojoExecutionException("Cannot write file: " + file, e);
		}
	}

	Gson createGson() {
		return new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	}

}
