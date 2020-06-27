package com.barnavarga.openrouteservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
@Getter
@AllArgsConstructor
public enum RouteProfile
{
	CAR("driving-car"),
	HEAVY_GOODS_VEHICLE("driving-hgv"),
	CYCLING_REGULAR("cycling-regular"),
	CYCLING_ROAD("cycling-road"),
	CYCLING_MOUNTAIN("cycling-mountain"),
	CYCLING_ELECTRIC("cycling-electric"),
	FOOT_WALKING("foot-walking"),
	FOOT_HIKING("foot-hiking"),
	WHEELCHAIR("wheelchair");


	private final String value;
}
