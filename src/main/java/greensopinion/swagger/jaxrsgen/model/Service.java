package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.ImmutableList.copyOf;

import java.util.List;

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

	public Service(String apiVersion, String basePath, String path, String description, List<ServiceApi> serviceApis) {
		this.apiVersion = checkNotNull(apiVersion, "Must provide apiVersion");
		this.basePath = checkNotNull(basePath, "Must provide a basePath");
		this.path = checkNotNull(path, "Must provide a path");
		this.description = description;
		this.serviceApis = copyOf(checkNotNull(serviceApis, "Must provide serviceApis"));
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

	public static ServiceBuilder builder() {
		return new ServiceBuilder();
	}
}
