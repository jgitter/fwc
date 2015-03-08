package org.gitter.fwc.services.endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gitter.fwc.services.InstitutionService;
import org.gitter.fwc.services.InstitutionSoapService;
import org.gitter.fwc.transfer.FindInstitutionsRequest;
import org.gitter.fwc.transfer.FindInstitutionsResponse;
import org.gitter.fwc.transfer.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class InstitutionSoapServiceImpl implements InstitutionSoapService {
    private InstitutionService service;

    @Autowired
    public InstitutionSoapServiceImpl(InstitutionService service) {
        this.service = service;
    }

    @Override
    @PayloadRoot(localPart = FindInstitutionsRequest.NAME,
            namespace = InstitutionSoapService.TARGET_NAMESPACE)
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
