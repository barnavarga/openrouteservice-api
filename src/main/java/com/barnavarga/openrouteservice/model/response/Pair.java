package com.barnavarga.openrouteservice.model.response;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Pair<T1, T2>
{
	public final T1 one;
	public final T2 two;

	public Pair(T1 one, T2 two)
	{
		this.one = one;
		this.two = two;
	}
}
