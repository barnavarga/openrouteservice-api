package com.barnavarga.openrouteservice.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bvarga and created on 2020. 06. 29..
 */
@Data
public class Summary implements Serializable
{
	private double distance;
	private double duration;
}
