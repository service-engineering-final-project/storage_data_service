package introsde.storage.rest.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import introsde.health.soap.ws.*;


@Stateless
@LocalBean
@Path("/person")
public class PersonResource {
	@Context UriInfo uriInfo;	// allows to insert contextual objects (uriInfo) into the class
	@Context Request request;	// allows to insert contextual objects (request) into the class
	
	static People_Service service = null;
	static People people = null;
	
	public PersonResource() {
		service = new People_Service();
		people = service.getPeopleImplementationPort();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPeopleList() {
		try {
			return Response.ok(people.readPersonList()).build();
		} catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}