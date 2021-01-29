package com.discov.dataorm;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Distance {

	@Id
	private Short routeID;

	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this. source = source;
	}

	private String destination;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	private String route;

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Distance() {
	}
}