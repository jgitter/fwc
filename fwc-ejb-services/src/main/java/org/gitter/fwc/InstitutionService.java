package org.gitter.fwc;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.gitter.fwc.dao.InstitutionDAO;

@RequestScoped
public class InstitutionService {

    @Inject
    private InstitutionDAO dao;

    public List<Map<String, String>> findInstitutions(String keyword) {
        return dao.findByKeyword(keyword);
    }

}
