package it.polito.tellmefirst.web.rest.services;

/**
 * Created by IntelliJ IDEA.
 * User: Federico Cairo
 */

import it.polito.tellmefirst.web.rest.interfaces.VideoInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/getVideo")
@Consumes("text/plain")
public class GetVideo {

    static Log LOG = LogFactory.getLog(GetVideo.class);
    private static VideoInterface videoInterface = new VideoInterface();

    private Response ok(String response) {
        return Response.ok().entity(response).header("Access-Control-Allow-Origin","*").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSON(@QueryParam("uri") String uri,
                            @QueryParam("label") String label) {
        LOG.debug("[postJSON] - BEGIN");
        //no prod
        LOG.info("GetVideo REST Service called for the resource: "+ uri);
        try {
            String response = videoInterface.getJSON(uri, label);
            LOG.debug("[postJSON] - END");
            return ok(response);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).header("TMF-error",e.getMessage()).build());
        }
    }


}
