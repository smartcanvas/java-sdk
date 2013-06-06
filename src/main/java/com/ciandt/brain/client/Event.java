package com.ciandt.brain.client;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;

/**
 * Represents an event
 * 
 * @author fabio
 */
public class Event extends GenericData {

	@Key
	private String action;

	@Key
	private Object data;

	//occurredOn
	//occurredAt
	
	public Event(String eventCollection, Object eventData) {
		super();
		this.action = eventCollection;
		this.data = eventData;
	}

}