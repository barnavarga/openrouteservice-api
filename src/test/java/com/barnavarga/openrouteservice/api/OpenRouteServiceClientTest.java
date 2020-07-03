package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.Coordinate;
import com.barnavarga.openrouteservice.model.enums.ErrorType;
import com.barnavarga.openrouteservice.model.enums.RouteProfile;
import com.barnavarga.openrouteservice.model.exception.OpenRouteServiceApiException;
import com.barnavarga.openrouteservice.model.exception.OpenRouteServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.geojson.FeatureCollection;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
@ExtendWith(MockitoExtension.class)
public class OpenRouteServiceClientTest extends AbstractOpenRouteServiceApiTest
{
	static
	{
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);
	}

	@RepeatedTest(3)
	public void testExecuteSimpleRequest_success() throws OpenRouteServiceException, IOException, URISyntaxException
	{
		mockSuccessOkHttpResponse("sample/basic-direction-response-200.json");

		final BasicDirectionHttpRequest basicDirectionHttpRequest = new BasicDirectionHttpRequest(RouteProfile.CAR)
				.start(Coordinate.valueOf(46.6900557, 17.2991067))
				.end(Coordinate.valueOf(46.6840769, 17.305384));
		final FeatureCollection response = client.execute(basicDirectionHttpRequest);
		assertNotNull(response);
	}

	@RepeatedTest(3)
	public void testExecuteSimpleRequest_invalidLatLon() throws IOException, URISyntaxException
	{
		mockInvalidOkHttpResponse("sample/basic-direction-response-404.json");

		final BasicDirectionHttpRequest basicDirectionHttpRequest = new BasicDirectionHttpRequest(RouteProfile.CAR)
				.start(Coordinate.valueOf(17.2991067, 46.6900557))
				.end(Coordinate.valueOf(17.305384, 46.6840769));
		final OpenRouteServiceApiException exception = assertThrows(OpenRouteServiceApiException.class, () -> client.execute(basicDirectionHttpRequest));
		assertNotNull(exception);
		assertEquals(ErrorType.ERROR_IN_REQUEST, exception.getErrorType());
		assertTrue(exception.getMessage().startsWith(ErrorType.ERROR_IN_REQUEST.getMessage()));
	}
}
