package com.barnavarga.openrouteservice.model.request;

import com.barnavarga.openrouteservice.model.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaypointDirectionBody implements Serializable
{
	private List<Coordinate> coordinates;
}
