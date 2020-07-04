package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.Coordinate;
import com.barnavarga.openrouteservice.model.enums.RouteProfile;
import com.barnavarga.openrouteservice.model.exception.OpenRouteServiceException;
import com.barnavarga.openrouteservice.model.request.WaypointDirectionBody;
import com.barnavarga.openrouteservice.model.response.Route;
import com.barnavarga.openrouteservice.model.response.Segment;
import com.barnavarga.openrouteservice.model.response.Step;
import com.barnavarga.openrouteservice.model.response.WaypointDirectionResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.geojson.FeatureCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bvarga and created on 2020. 07. 04..
 */
@ExtendWith(MockitoExtension.class)
public class DirectionApiRequestTest extends AbstractOpenRouteServiceApiTest
{
	static
	{
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);
	}

	@Test
	public void testBasicDirectionHttpRequest_success() throws OpenRouteServiceException, IOException, URISyntaxException
	{
		mockSuccessOkHttpResponse("sample/basic-direction-response-200.json");

		final BasicDirectionHttpRequest httpRequest = new BasicDirectionHttpRequest(RouteProfile.CAR)
				.start(Coordinate.valueOf(46.6900557, 17.2991067))
				.end(Coordinate.valueOf(46.6840769, 17.305384));
		final FeatureCollection response = client.execute(httpRequest);
		assertNotNull(response);
		assertNotNull(response.getFeatures());
		assertFalse(response.getFeatures().isEmpty());
	}

	@Test
	public void testWaypointDirectionHttpRequest_success() throws OpenRouteServiceException, IOException, URISyntaxException
	{
		mockSuccessOkHttpResponse("sample/waypoint-direction-response-200.json");

		final List<Coordinate> coordinates = Arrays.asList(
				Coordinate.valueOf(46.692986, 17.294576),
				Coordinate.valueOf(46.6900557, 17.2991067),
				Coordinate.valueOf(46.6840769, 17.305384));
		final WaypointDirectionBody requestBody = WaypointDirectionBody.builder()
				.coordinates(coordinates).build();
		final WaypointDirectionHttpRequest httpRequest = new WaypointDirectionHttpRequest(RouteProfile.CAR).setRequestBody(requestBody);
		final WaypointDirectionResponse response = client.execute(httpRequest);
		assertNotNull(response);
		assertNotNull(response.getBoundingBox());

		assertNotNull(response.getRoutes());
		assertFalse(response.getRoutes().isEmpty());
		for (final Route route : response.getRoutes())
		{
			assertNotNull(route.getBoundingBox());
			assertNotNull(route.getGeometry());
			assertNotNull(route.getSummary());
			assertNotNull(route.getSegments());
			assertFalse(route.getSegments().isEmpty());
			for (final Segment segment : route.getSegments())
			{
				assertNotNull(segment.getSteps());
				assertFalse(segment.getSteps().isEmpty());
				for (final Step step : segment.getSteps())
				{
					assertNotNull(step.getName());
					assertNotNull(step.getInstruction());
					assertNotNull(step.getWayPoints());
				}
			}
		}

		assertNotNull(response.getMetadata());
		assertNotNull(response.getMetadata().getQuery());
		assertEquals(RouteProfile.CAR.getValue(), response.getMetadata().getQuery().getProfile());

	}
}
