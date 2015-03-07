package org.gitter.fwc.services;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/institution")
@Stateless
public class InstitutionRestController {

    @Inject
    private InstitutionService service;

    @GET
    @Path("{keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> findInstitutions(@PathParam("keyword") String keyword) {
        return service.findInstitutions(keyword);
    }
}