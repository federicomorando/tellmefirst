
package it.polito.tellmefirst.web.rest.services;

import it.polito.tellmefirst.web.rest.interfaces.MapInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * User: Federico Cairo
 */

@Path("/getMap")
@Consumes("text/plain")
public class GetMap {

    static Log LOG = LogFactory.getLog(GetMap.class);
    private static MapInterface mapInterface = new MapInterface();

    private Response ok(String response) {
        return Response.ok().entity(response).header("Access-Control-Allow-Origin","*").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSON(@QueryParam("uri") String uri) {
        LOG.debug("[postJSON] - BEGIN");
        //no prod
        LOG.info("GetMap REST Service called for the resource: "+ uri);
        try {
            String response = mapInterface.getJSON(uri);
            LOG.debug("[postJSON] - END");
            return ok(response);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).header("TMF-error",e.getMessage()).build());
        }
    }
}


