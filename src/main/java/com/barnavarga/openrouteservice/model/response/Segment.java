package com.barnavarga.openrouteservice.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author bvarga and created on 2020. 06. 29..
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Segment extends Summary implements Serializable
{
	private List<Step> steps;
}
