package org.gitter.fwc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gitter.fwc.transfer.FindInstitutionsRequest;
import org.gitter.fwc.transfer.FindInstitutionsResponse;
import org.gitter.fwc.transfer.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class InstitutionSoapService {
    private InstitutionService service;

    @Autowired
    public InstitutionSoapService(InstitutionService service) {
        this.service = service;
    }

    @PayloadRoot(localPart = "findInstitutionsRequest",
            namespace = "http://fwc.gitter.org/services/")
    public @ResponsePayload FindInstitutionsResponse findInstitutions(
            @RequestPayload FindInstitutionsRequest request) {
        FindInstitutionsResponse response = new FindInstitutionsResponse();

        List<Institution> list = new ArrayList<Institution>();
        List<Map<String, String>> results = service.findByKeyword(request.getKeyword());
        for (Map<String, String> result : results) {
            Institution institution = new Institution();
            institution.setInstitutionData(result);
            list.add(institution);
        }

        response.setInstitutions(list);

        return response;
    }
}
