package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.enums.ErrorType;
import com.barnavarga.openrouteservice.model.exception.OpenRouteServiceApiException;
import com.barnavarga.openrouteservice.model.exception.OpenRouteServiceException;
import com.barnavarga.openrouteservice.model.exception.OpenRouteServiceProcessException;
import com.barnavarga.openrouteservice.util.Const;
import com.barnavarga.openrouteservice.util.ErrorUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public class OpenRouteServiceClient
{
	private static final Logger logger = LogManager.getLogger(OpenRouteServiceClient.class);

	private static final String SCHEME = "https";
	private static final String HOST = "api.openrouteservice.org";

	private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	});

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

	public <ResponseType extends Serializable> ResponseType execute(final AbstractHttpRequest<ResponseType> request) throws OpenRouteServiceException
	{
		try (final Response response = HTTP_CLIENT.newCall(request.toRequest(SCHEME, HOST, getApiKey())).execute())
		{
			if (response.isSuccessful())
			{
				final ResponseBody responseBody = response.body();
				return responseBody != null ? OBJECT_MAPPER_THREAD_LOCAL.get().readValue(responseBody.string(), request.getResponseClass()) : null;
			}
			else
			{
				final ErrorType errorType = ErrorUtil.findErrorTypeByStatusCode(response.code());
				throw new OpenRouteServiceApiException(errorType);
			}
		} catch (final IOException e)
		{
			logger.error(e.getMessage(), e);
			throw new OpenRouteServiceProcessException("Cannot process OpenRoute Service response, check details or the log for more information!", e);
		}
	}
}
