package at.echo.web;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Produces({MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/echo")
public class EchoResource {

	@Inject
	private Echo echo;

	@GET
	@Path("{string}")
	public Response echo(@PathParam("string") String string) {
		return Response.ok(echo.echo(string)).build();

	}
}
