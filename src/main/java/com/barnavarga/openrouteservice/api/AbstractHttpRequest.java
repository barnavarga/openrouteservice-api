package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.enums.HttpMethod;
import lombok.Getter;
import okhttp3.Request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
@Getter
public abstract class AbstractHttpRequest<Response extends Serializable> implements Serializable
{
	private final String path;
	private final HttpMethod httpMethod;
	private final Class<Response> responseClass;
	private final Map<String, String> requestParameters = new HashMap<>();

	protected AbstractHttpRequest(final String path, final HttpMethod httpMethod, Class<Response> responseClass)
	{
		this.path = path;
		this.httpMethod = httpMethod;
		this.responseClass = responseClass;
	}

	protected abstract Request toRequest(final String scheme, final String host, final String apiKey);

	protected void addRequestParam(final String parameter, final String value)
	{
		requestParameters.put(parameter, value);
	}
}
