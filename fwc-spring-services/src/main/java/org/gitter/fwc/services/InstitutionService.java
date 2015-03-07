package org.gitter.fwc.services;

import java.util.List;
import java.util.Map;

import org.gitter.fwc.dao.InstitutionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstitutionService {

    @Autowired
    private InstitutionDAO dao;

    public List<Map<String, String>> findByKeyword(String keyword) {
        return dao.findByKeyword(keyword);
    }

    public void setDao(InstitutionDAO dao) {
        this.dao = dao;
    }
}
