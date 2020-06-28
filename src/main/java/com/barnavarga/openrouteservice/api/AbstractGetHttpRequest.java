package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.enums.HttpMethod;
import com.barnavarga.openrouteservice.util.Const;
import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.Serializable;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
@Getter
public abstract class AbstractGetHttpRequest<Response extends Serializable> extends AbstractHttpRequest<Response>
{
	protected AbstractGetHttpRequest(final String path, Class<Response> responseClass)
	{
		super(path, HttpMethod.GET, responseClass);
	}

	@Override
	protected Request toRequest(final String scheme, final String host, final String apiKey)
	{
		final HttpUrl.Builder urlBuilder = new HttpUrl.Builder().scheme(scheme).host(host).addPathSegment(getPath());
		urlBuilder.addQueryParameter(Const.Request.QUERY_PARAMETER_API_KEY, apiKey);
		getRequestParameters().forEach(urlBuilder::addQueryParameter);
		return new Request.Builder().url(urlBuilder.build()).addHeader("Content-Type", "application/json; charset=utf-8").get().build();
	}
}
