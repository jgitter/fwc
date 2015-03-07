package org.gitter.fwc.services;

import java.util.List;
import java.util.Map;

public interface InstitutionRestController {

    public List<Map<String, String>> findInstitutions(String keyword);
}
