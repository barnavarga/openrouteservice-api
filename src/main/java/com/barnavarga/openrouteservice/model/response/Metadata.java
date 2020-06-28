package com.barnavarga.openrouteservice.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bvarga and created on 2020. 06. 29..
 */
@Data
public class Metadata implements Serializable
{
	private String attribution;
	private String service;
	private long timestamp;
	private Query query;
	private Engine engine;
}
