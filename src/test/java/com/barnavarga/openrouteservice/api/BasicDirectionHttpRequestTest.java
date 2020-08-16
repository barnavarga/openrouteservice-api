package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.Coordinate;
import com.barnavarga.openrouteservice.model.enums.RouteProfile;
import org.geojson.FeatureCollection;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bvarga and created on 2020. 07. 04..
 */
public class BasicDirectionHttpRequestTest
{
	@Test
	public void testCreateBasicDirectionHttpRequestInstance()
	{
		final Coordinate start = Coordinate.valueOf(46.6900557, 17.2991067);
		final Coordinate end = Coordinate.valueOf(46.6840769, 17.305384);
		final BasicDirectionHttpRequest basicDirectionHttpRequest = new BasicDirectionHttpRequest(RouteProfile.CAR).start(start).end(end);
		assertNotNull(basicDirectionHttpRequest);
		assertEquals(FeatureCollection.class, basicDirectionHttpRequest.getResponseClass());
		final Map<String, String> requestParameters = basicDirectionHttpRequest.getRequestParameters();
		assertNotNull(requestParameters);
		assertTrue(requestParameters.containsKey("start"), "Request parameters must contains `start` attribute");
		assertTrue(requestParameters.containsKey("end"), "Request parameters must contains `end` attribute");
		assertEquals(start.toString(), requestParameters.get("start"));
		assertEquals(end.toString(), requestParameters.get("end"));
	}
}
