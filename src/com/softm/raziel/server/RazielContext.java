package com.softm.raziel.server;

import com.softm.raziel.client.AuthenticationChannel;
import com.softm.raziel.client.ContentChannel;

//TODO evaluate the need of a contex class to wire up channel to services
public class RazielContext {
	private AuthenticationChannel authenticationChannel;
	private ContentChannel contentChannel;
	private AuthenticationService autenticationService;
	private ContentService contentService;
	// TODO: wire up channel to service

}
