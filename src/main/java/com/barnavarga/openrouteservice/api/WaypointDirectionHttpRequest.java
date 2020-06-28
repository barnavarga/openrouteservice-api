package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.enums.RouteProfile;
import com.barnavarga.openrouteservice.model.request.WaypointDirectionBody;
import com.barnavarga.openrouteservice.model.response.WaypointDirectionResponse;

/**
 * Returns a route between two or more locations for a selected profile and its settings as JSON
 *
 * @author bvarga and created on 2020. 06. 28..
 */
public class WaypointDirectionHttpRequest extends AbstractPostHttpRequest<WaypointDirectionBody, WaypointDirectionResponse, WaypointDirectionHttpRequest>
{
	public WaypointDirectionHttpRequest(final RouteProfile profile)
	{
		super("/v2/directions/" + profile.getValue(), WaypointDirectionResponse.class);
	}

	@Override
	protected WaypointDirectionHttpRequest getThis()
	{
		return this;
	}
}
