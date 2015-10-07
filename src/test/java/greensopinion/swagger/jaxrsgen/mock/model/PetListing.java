/*******************************************************************************
 * Copyright (c) 2014 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen.mock.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.ImmutableList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("A listing of pets")
public class PetListing {

	@ApiModelProperty("The 0-based start index offset.")
	private final int start;

	@ApiModelProperty("The number of pets requested.  May differ from the actual number of pets in the listing.")
	private final int count;

	@ApiModelProperty("The total number of pets in the system that correspond to the listing.")
	private final int total;

	@ApiModelProperty("The list of pet handles.")
	private final List<PetHandle> pets;

	public PetListing(int start, int count, int total, List<PetHandle> pets) {
		this.start = start;
		this.count = count;
		this.total = total;
		this.pets = ImmutableList.copyOf(checkNotNull(pets));
	}

	public int getStart() {
		return start;
	}

	public int getCount() {
		return count;
	}

	public int getTotal() {
		return total;
	}

	public List<PetHandle> getPets() {
		return pets;
	}
}
