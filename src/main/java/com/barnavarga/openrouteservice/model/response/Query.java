package com.barnavarga.openrouteservice.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bvarga and created on 2020. 06. 29..
 */
@Data
public class Query implements Serializable
{
	private String profile;
	private String format;
}
