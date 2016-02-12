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

package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

public class ApiModel {

	public static class Property {

		private final String type;

		private final String format;

		@SerializedName("items")
		private final Map<String, String> arrayItems;

		private final String description;

		@SerializedName("enum")
		private final List<String> enumeratedValues;

		public Property(String type, String format, String description, List<String> enumeratedValues,
				Map<String, String> arrayItems) {
			this.type = type;
			this.format = format;
			this.description = Strings.emptyToNull(description);
			this.arrayItems = arrayItems == null ? null : ImmutableMap.copyOf(arrayItems);
			this.enumeratedValues = enumeratedValues == null ? null : ImmutableList.copyOf(enumeratedValues);
		}

		public String getType() {
			return type;
		}

		public String getFormat() {
			return format;
		}

		public String getDescription() {
			return description;
		}

		public List<String> getEnumeratedValues() {
			return enumeratedValues;
		}

	}

	private transient final String name;

	private final String id;

	private final String description;

	private final List<String> required;

	private final Map<String, ApiModel.Property> properties;

	ApiModel(String name, String description, List<String> required, LinkedHashMap<String, Property> properties) {
		this.name = checkNotNull(name);
		this.id = name;
		this.description = Strings.emptyToNull(description);
		this.required = ImmutableList.copyOf(checkNotNull(required));
		this.properties = Maps.newLinkedHashMap(checkNotNull(properties));
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getRequired() {
		return required;
	}

	public Map<String, Property> getProperties() {
		return ImmutableMap.copyOf(properties);
	}

}
