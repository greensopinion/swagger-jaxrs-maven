package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.ImmutableList.copyOf;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.annotations.SerializedName;

public class Service {

	private final String apiVersion;

	private final String swaggerVersion = Swagger.VERSION;

	private final String basePath;

	@SerializedName("resourcePath")
	private final String path;

	private final String description;

	@SerializedName("apis")
	private final List<ServiceApi> serviceApis;

	private final Map<String, ApiModel> models;

	public Service(String apiVersion, String basePath, String path, String description, List<ServiceApi> serviceApis,
			List<ApiModel> models) {
		this.apiVersion = checkNotNull(apiVersion, "Must provide apiVersion");
		this.basePath = checkNotNull(basePath, "Must provide a basePath");
		this.path = checkNotNull(path, "Must provide a path");
		this.description = description;
		this.serviceApis = copyOf(checkNotNull(serviceApis, "Must provide serviceApis"));
		Map<String, ApiModel> modelByName = Maps.newTreeMap();
		checkNotNull(models, "Must provide models");
		for (ApiModel model : models) {
			modelByName.put(model.getName(), model);
		}
		this.models = ImmutableMap.copyOf(modelByName);

	}

	public String getApiVersion() {
		return apiVersion;
	}

	public String getSwaggerVersion() {
		return swaggerVersion;
	}

	public String getBasePath() {
		return basePath;
	}

	public String getPath() {
		return path;
	}

	public String getDescription() {
		return description;
	}

	public List<ServiceApi> getServiceApis() {
		return serviceApis;
	}

	public Map<String, ApiModel> getModels() {
		return models;
	}

	public static ServiceBuilder builder() {
		return new ServiceBuilder();
	}

	public Set<Class<?>> getModelClasses() {
		Set<Class<?>> modelClasses = Sets.newHashSet();
		for (ServiceApi serviceApi : getServiceApis()) {
			modelClasses.addAll(serviceApi.getModelClasses());
		}
		return ImmutableSet.copyOf(modelClasses);
	}
}
