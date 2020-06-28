package com.barnavarga.openrouteservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bvarga and created on 2020. 06. 29..
 */
@Data
public class Route implements Serializable
{
	private Summary summary;
	private List<Segment> segments;
	@JsonProperty("bbox")
	private double[] boundingBox;
	private String geometry;
	@JsonProperty("wayPoints")
	private double[] wayPoints;
}
