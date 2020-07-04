package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.exception.OpenRouteServiceProcessException;
import com.barnavarga.openrouteservice.util.Const;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public abstract class AbstractPostHttpRequest<Body extends Serializable, Response extends Serializable, T extends AbstractPostHttpRequest<Body, Response, T>>
		extends AbstractHttpRequest<Response>
{
	protected final Logger logger = LogManager.getLogger(getClass());

	private Body body;

	protected abstract T getThis();

	protected AbstractPostHttpRequest(final String path, Class<Response> responseClass)
	{
		super(path, responseClass);
	}

	public T setRequestBody(final Body requestBody)
	{
		this.body = requestBody;
		return getThis();
	}

	@Override
	protected Request toRequest(final String scheme, final String host, final String apiKey) throws OpenRouteServiceProcessException
	{
		final HttpUrl.Builder urlBuilder = new HttpUrl.Builder().scheme(scheme).host(host).addPathSegment(getPath());
		getRequestParameters().forEach(urlBuilder::addQueryParameter);
		try
		{
			final String rawRequestBody = OBJECT_MAPPER_THREAD_LOCAL.get().writeValueAsString(body);
			final RequestBody requestBody = RequestBody.create(rawRequestBody, Const.Request.JSON);
			return new Request.Builder().url(urlBuilder.build())
					.addHeader(Const.Header.AUTHORIZATION, apiKey)
					.addHeader(Const.Header.CONTENT_TYPE.one, Const.Header.CONTENT_TYPE.two).post(requestBody).build();
		} catch (JsonProcessingException e)
		{
			throw new OpenRouteServiceProcessException("Cannot serialize request-body!", e);
		}
	}
}
