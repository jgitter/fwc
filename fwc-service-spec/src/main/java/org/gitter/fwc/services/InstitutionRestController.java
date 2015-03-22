package org.gitter.fwc.services;

import java.util.List;
import java.util.Map;

import org.gitter.fwc.exception.FwcServiceException;

public interface InstitutionRestController {

	public List<Map<String, String>> findInstitutions(String keyword) throws FwcServiceException;
}
