package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.util.Const;
import lombok.Getter;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public class OpenRouteServiceClient
{
	private static final String SCHEME = "https";
	private static final String HOST = "api.openrouteservice.org";

	private static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
			.connectTimeout(Const.Connection.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
			.readTimeout(Const.Connection.READ_TIMEOUT, TimeUnit.SECONDS)
			.writeTimeout(Const.Connection.WRITE_TIMEOUT, TimeUnit.SECONDS).build();

	@Getter
	private final String apiKey;

	public OpenRouteServiceClient(final String apiKey)
	{
		this.apiKey = apiKey;
	}

//	public <ResponseType extends AbstractResponse> ResponseType execute(final AbstractHttpRequest<ResponseType> request)
//	{
//		try (final Response response = HTTP_CLIENT.newCall(request.toRequest(SCHEME, HOST, getApiKey())).execute())
//		{
//			final ResponseBody responseBody = response.body();
//
//		} catch (final IOException e)
//		{
//
//		}
//	}
}
