package com.barnavarga.openrouteservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author bvarga and created on 2020. 06. 29..
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Step extends Summary implements Serializable
{
	private int type;
	private String instruction;
	private String name;
	@JsonProperty("way_points")
	private double[] wayPoints;
}
