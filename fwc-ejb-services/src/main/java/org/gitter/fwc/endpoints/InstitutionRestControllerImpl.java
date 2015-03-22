package org.gitter.fwc.endpoints;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gitter.fwc.exception.FwcServiceException;
import org.gitter.fwc.services.InstitutionRestController;
import org.gitter.fwc.services.InstitutionService;

@Path("/institution")
@Stateless
public class InstitutionRestControllerImpl implements InstitutionRestController {

	@Inject
	private InstitutionService service;

	@Override
	@GET
	@Path("{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String, String>> findInstitutions(@PathParam("keyword") String keyword)
			throws FwcServiceException {
		return service.findInstitutions(keyword);
	}
}