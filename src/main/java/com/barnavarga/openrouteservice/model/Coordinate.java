package com.barnavarga.openrouteservice.model;

import com.barnavarga.openrouteservice.util.CoordinateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author bvarga and created on 2020. 06. 27..
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = CoordinateSerializer.class)
public class Coordinate implements Serializable
{
	private double latitude;
	private double longitude;

	private Coordinate(final Coordinate coordinate)
	{
		this.latitude = coordinate.getLatitude();
		this.longitude = coordinate.getLongitude();
	}

	private Coordinate(final double latitude, final double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString()
	{
		return longitude + "," + latitude;
	}

	public static Coordinate valueOf(final double latitude, final double longitude)
	{
		return new Coordinate(latitude, longitude);
	}
}
