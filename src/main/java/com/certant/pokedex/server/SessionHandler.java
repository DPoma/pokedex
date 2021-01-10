package com.certant.pokedex.server;

import com.certant.pokedex.handlers.Sesion;

import spark.Filter;
import spark.Request;
import spark.Response;

public class SessionHandler {

	public static Filter allowed() {

		return new Filter() {
			
			@Override
			public void handle(Request req, Response res) {
		
			if (Sesion.sinIniciar() && !Router.isPublic(req.pathInfo()))
					res.redirect("/login");
			}
		};
	}

}
