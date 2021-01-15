package io.lippia.api.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crowdar.core.PropertyManager;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.TagType;
import com.github.jknack.handlebars.Template;

public class EndpointConfiguration {

	private static ThreadLocal<EndpointConfiguration> INSTANCE = new ThreadLocal<EndpointConfiguration>();
	
	private HttpConfiguration httpConfiguration;
	private Map<String, String> urlParameters;
	private Map<String, String> headers;
	private String body;
	private String tokenPath;
	
	public String getTokenPath() {
		return tokenPath;
	}

	public void setTokenPath(String tokenPath) {
		this.tokenPath = tokenPath;
	}

	private EndpointConfiguration() {
	}
	
	public HttpConfiguration getHttConfiguration() {
		if(httpConfiguration == null) {
			httpConfiguration = new HttpConfiguration();
		}
		return httpConfiguration;
	}
	
	public Map<String, String> getUrlParameters() {
		if(urlParameters == null) {
			urlParameters = new HashMap<String, String>();
		}
		return urlParameters;
	}
	
	public Map<String, String> getHeaders() {
		if(headers == null) {
			headers = new HashMap<String, String>();
		}
		return headers;
	}
	
	private void setBody(String body) {
		this.body = body;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public static EndpointConfiguration getInstance() {
		if(INSTANCE.get() == null) {
			INSTANCE.set(new EndpointConfiguration());
		}
		return INSTANCE.get();
	}
	
	public static EndpointConfiguration clean() {
		INSTANCE.set(null);
		return INSTANCE.get();
	}
	
	public static EndpointConfiguration url(String url) {
		 getInstance().getHttConfiguration().setUrl(url);
		 return getInstance();
	}
	
	public static EndpointConfiguration endpoint(String endpoint) {
		 getInstance().getHttConfiguration().setEndpoint(endpoint);
		 return getInstance();
	}
	
	public static EndpointConfiguration protocol(String protocol) {
		 getInstance().getHttConfiguration().setProtocol(protocol);
		 return getInstance();
	}
	
	public static EndpointConfiguration method(String method) {
		 getInstance().getHttConfiguration().setMethod(method);
		 return getInstance();
	}
	
	public static EndpointConfiguration port(String port) {
		 getInstance().getHttConfiguration().setPort(port);
		 return getInstance();
	}
	
	public static EndpointConfiguration urlParameter(String key, String value) {
		 getInstance().getUrlParameters().put(key, value);
		 return getInstance();
	}
	
	public static EndpointConfiguration body(String body) {
		 getInstance().setBody(body);
		 return getInstance();
	}

	public static EndpointConfiguration header(String key, String value) {
		getInstance().getHeaders().put(key, value);		
		return getInstance();
	}
	
	public static EndpointConfiguration replaceOnBody(String key, String value) {
		getInstance().setBody(getInstance().getBody().replace("{{" + key + "}}", value));	
		return getInstance();
	}
	
}
