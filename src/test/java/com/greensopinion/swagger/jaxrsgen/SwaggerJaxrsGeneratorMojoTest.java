/*******************************************************************************
 * Copyright (c) 2017 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.greensopinion.swagger.jaxrsgen.model.TestResources;

public class SwaggerJaxrsGeneratorMojoTest {

	@Rule
	public final TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void generate() throws MojoExecutionException, MojoFailureException {
		SwaggerJaxrsGeneratorMojo mojo = new SwaggerJaxrsGeneratorMojo();
		mojo.apiVersion = "1.0.0";
		mojo.outputFolder = temporaryFolder.getRoot();
		mojo.packageNames = ImmutableList.of("com.greensopinion.swagger.jaxrsgen.mock");
		mojo.mavenProject = mockMavenProject();
		mojo.execute();

		File apiDocs = new File(temporaryFolder.getRoot(), "api-docs");
		assertThat(apiDocs).exists();
		assertThat(apiDocs).isDirectory();

		assertFileWithContent("index.json", new File(apiDocs, "index.json"));
		assertFileWithContent("pets.json", new File(new File(apiDocs, "pets"), "index.json"));
	}

	private void assertFileWithContent(String resource, File file) {
		assertThat(file).exists();
		assertThat(file).isFile();
		assertThat(file).hasContent(TestResources.read(SwaggerJaxrsGeneratorMojoTest.class, resource));
	}

	private MavenProject mockMavenProject() {
		MavenProject project = mock(MavenProject.class);
		doReturn(ImmutableSet.of()).when(project).getArtifacts();
		return project;
	}
}
