package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.exception.OpenRouteServiceException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	protected static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	});

	private final String path;
	private final Class<Response> responseClass;
	private final Map<String, String> requestParameters = new HashMap<>();

	protected AbstractHttpRequest(final String path, Class<Response> responseClass)
	{
		this.path = path;
		this.responseClass = responseClass;
	}

	protected abstract Request toRequest(final String scheme, final String host, final String apiKey) throws OpenRouteServiceException;

	protected void addRequestParam(final String parameter, final String value)
	{
		requestParameters.put(parameter, value);
	}
}
