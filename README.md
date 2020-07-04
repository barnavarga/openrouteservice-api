openrouteservice-api
======================================

Summary
-------
Java client implementation against the openrouteservice api.\
You can read more information about the **openrouteservice** api services here: https://openrouteservice.org/


Details
-------
Implemented api services:
* **Directions** - returns a route between two or more locations for a selected profile with customizable additional settings and instructions.

How to use
-------
* **Directions...**
   * ...between strictly two locations and returning `FeatureCollection` (https://geojson.org/):
	```
	final BasicDirectionHttpRequest basicDirectionHttpRequest = new BasicDirectionHttpRequest(RouteProfile.CAR)
			.start(Coordinate.valueOf(46.6900557, 17.2991067))
			.end(Coordinate.valueOf(46.6840769, 17.305384));
	final FeatureCollection response = new OpenRouteServiceClient("{{apiKey}}").execute(basicDirectionHttpRequest);
	```
   * ...between two or more locations: 
	```
	final WaypointDirectionBody requestBody = WaypointDirectionBody.builder().coordinates(Arrays.asList(
		Coordinate.valueOf(46.692986, 17.294576),
			Coordinate.valueOf(46.6900557, 17.2991067),
			Coordinate.valueOf(46.6840769, 17.305384))).build();
	final WaypointDirectionHttpRequest httpRequest = new WaypointDirectionHttpRequest(RouteProfile.CAR).setRequestBody(requestBody);
	final WaypointDirectionResponse response = new OpenRouteServiceClient("{{apiKey}}").execute(httpRequest);
	```

Technologies
-------
* Java 1.8

Maven
-------
```
<dependency>
  <groupId>com.barnavarga</groupId>
  <artifactId>openrouteservice-api</artifactId>
  <version>0.0.2</version>
</dependency>
```

Installation
-------
```
mvn install
```
