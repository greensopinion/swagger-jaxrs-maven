package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ApiRoot {

	private final String apiVersion;

	private final String swaggerVersion = Swagger.VERSION;

	private final transient List<Service> services;

	private final List<ServiceReference> apis;

	public ApiRoot(String apiVersion, List<Service> services) {
		this.apiVersion = checkNotNull(apiVersion, "Must provide apiVersion");
		this.services = ImmutableList.copyOf(services);
		List<ServiceReference> apis = Lists.newArrayList();
		for (Service service : services) {
			apis.add(new ServiceReference(service.getPath(), service.getDescription()));
		}
		this.apis = ImmutableList.copyOf(apis);
	}

	public List<ServiceReference> getApis() {
		return apis;
	}

	public List<Service> getServices() {
		return services;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public String getSwaggerVersion() {
		return swaggerVersion;
	}

	public static ApiBuilder builder() {
		return new ApiBuilder();
	}
}
