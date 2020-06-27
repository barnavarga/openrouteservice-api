package com.barnavarga.openrouteservice.api;

import com.barnavarga.openrouteservice.model.BasicDirectionResponse;
import com.barnavarga.openrouteservice.model.Coordinate;
import com.barnavarga.openrouteservice.model.enums.RouteProfile;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public class BasicDirectionHttpRequest extends AbstractGetHttpRequest<BasicDirectionResponse>
{
	public BasicDirectionHttpRequest(final RouteProfile profile)
	{
		super("/v2/directions/" + profile.getValue(), BasicDirectionResponse.class);
	}

	public BasicDirectionHttpRequest start(final Coordinate start)
	{
		this.addRequestParam("start", start.toString());
		return this;
	}

	public BasicDirectionHttpRequest end(final Coordinate end)
	{
		this.addRequestParam("end", end.toString());
		return this;
	}
}
