package org.gitter.fwc.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface InstitutionDAO {
    List<Map<String, String>> findByKeyword(String keyword);
}