package org.gitter.fwc.endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.gitter.fwc.exception.FwcServiceException;
import org.gitter.fwc.services.InstitutionService;
import org.gitter.fwc.services.InstitutionSoapService;
import org.gitter.fwc.transfer.FindInstitutionsRequest;
import org.gitter.fwc.transfer.FindInstitutionsResponse;
import org.gitter.fwc.transfer.Institution;

@Stateless
@WebService(name = "InstitutionService", serviceName = "soap-institution", targetNamespace = InstitutionSoapService.TARGET_NAMESPACE)
public class InstitutionSoapServiceImpl implements InstitutionSoapService {

	@Inject
	private InstitutionService service;

	@Override
	@WebMethod
	@WebResult(targetNamespace = InstitutionSoapService.TARGET_NAMESPACE, name = FindInstitutionsResponse.NAME)
	public FindInstitutionsResponse findInstitutions(
			@WebParam(targetNamespace = InstitutionSoapService.TARGET_NAMESPACE, name = FindInstitutionsRequest.NAME) FindInstitutionsRequest request)
			throws FwcServiceException {

		FindInstitutionsResponse response = new FindInstitutionsResponse();

		List<Institution> list = new ArrayList<Institution>();
		List<Map<String, String>> results = service.findInstitutions(request.getKeyword());
		for (Map<String, String> result : results) {
			Institution institution = new Institution();
			institution.setInstitutionData(result);
			list.add(institution);
		}

		response.setInstitutions(list);

		return response;
	}
}
