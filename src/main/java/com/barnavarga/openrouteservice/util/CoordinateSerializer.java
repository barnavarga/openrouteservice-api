package com.barnavarga.openrouteservice.util;

import com.barnavarga.openrouteservice.model.Coordinate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public class CoordinateSerializer extends StdSerializer<Coordinate>
{
	public CoordinateSerializer()
	{
		this(null);
	}

	public CoordinateSerializer(final Class<Coordinate> t)
	{
		super(t);
	}

	@Override
	public void serialize(final Coordinate value, final JsonGenerator gen, final SerializerProvider provider) throws IOException
	{
		final double[] doubles = {value.getLongitude(), value.getLatitude()};
		gen.writeArray(doubles, 0, doubles.length);
	}
}
