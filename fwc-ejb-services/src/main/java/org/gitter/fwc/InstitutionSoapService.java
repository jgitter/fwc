package org.gitter.fwc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.gitter.fwc.transfer.FindInstitutionsResponse;
import org.gitter.fwc.transfer.Institution;

@Stateless
@WebService(name = "InstitutionService", serviceName = "soap/institution", targetNamespace = "http://fwc.gitter.org/services/")
public class InstitutionSoapService {

    @Inject
    private InstitutionService service;

    @WebMethod
    @WebResult(targetNamespace = "http://fwc.gitter.org/services/", name = "findInstitutionsResponse")
    public FindInstitutionsResponse findInstitutions(
            @WebParam(name = "keyword") String keyword) {

        FindInstitutionsResponse response = new FindInstitutionsResponse();

        List<Institution> list = new ArrayList<Institution>();
        List<Map<String, String>> results = service.findInstitutions(keyword);
        for (Map<String, String> result : results) {
            Institution institution = new Institution();
            institution.setInstitutionData(result);
            list.add(institution);
        }

        response.setInstitutions(list);

        return response;
    }
}
