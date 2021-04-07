package com.getset.pickmeapp.routing.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.getset.pickmeapp.routing.jsprit.JSpritUtil;
import com.getset.pickmeapp.routing.model.AlgorithmInput;

@Path("/route")
public class RoutingService {
	
	@POST
	@Path("/optimize")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response optimizeProblem(AlgorithmInput input) {
		
		System.out.println(input);
		
		if(input == null) 
			return Response.status(400).build();
		else
			return Response.status(200).entity(JSpritUtil.resolveProblem(input)).build();
	}

}
