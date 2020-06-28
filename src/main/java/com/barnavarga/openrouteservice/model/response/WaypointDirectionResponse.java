package com.barnavarga.openrouteservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bvarga and created on 2020. 06. 29..
 */
@Data
public class WaypointDirectionResponse implements Serializable
{
	private List<Route> routes;
	@JsonProperty("bbox")
	private double[] boundingBox;
	private Metadata metadata;
}
