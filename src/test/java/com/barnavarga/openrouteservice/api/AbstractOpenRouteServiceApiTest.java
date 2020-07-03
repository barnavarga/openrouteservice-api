package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.util.Const;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Spy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author bvarga and created on 2020. 07. 03..
 */
public abstract class AbstractOpenRouteServiceApiTest
{
	protected final Logger logger = LogManager.getLogger(getClass());

	@Spy
	protected OpenRouteServiceClient client = new OpenRouteServiceClient("unit-test-api-key");

	protected void mockSuccessOkHttpResponse(final String filePath) throws IOException, URISyntaxException
	{
		mockOkHttpResponse(200, filePath);
	}

	protected void mockInvalidOkHttpResponse(final String filePath) throws IOException, URISyntaxException
	{
		mockOkHttpResponse(404, filePath);
	}

	protected void mockOkHttpResponse(final int statusCode, final String filePath) throws IOException, URISyntaxException
	{
		final URL resource = getClass().getClassLoader().getResource(filePath);
		assertNotNull(resource, "Resource url must not be null!");
		final Path path = Paths.get(resource.toURI());
		try (final Stream<String> lines = Files.lines(path))
		{
			final String content = lines.collect(Collectors.joining("\n"));
			final Response response = new Response.Builder()
					.request(new Request.Builder().url("http://unit.test").build())
					.protocol(Protocol.HTTP_1_1).code(statusCode).message("")
					.body(ResponseBody.create(content, Const.Request.JSON))
					.build();
			doReturn(response).when(client).sendRequest(any(Request.class));
		}
	}
}
