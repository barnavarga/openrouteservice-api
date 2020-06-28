package com.barnavarga.openrouteservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bvarga and created on 2020. 06. 29..
 */
@Data
public class Engine implements Serializable
{
	private String version;
	@JsonProperty("build_date")
	private String buildDate;
	@JsonProperty("graph_date")
	private String graphDate;
}
