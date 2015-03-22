package org.gitter.fwc.services;

import org.gitter.fwc.exception.FwcServiceException;
import org.gitter.fwc.transfer.FindInstitutionsRequest;
import org.gitter.fwc.transfer.FindInstitutionsResponse;

public interface InstitutionSoapService {

	public static final String TARGET_NAMESPACE = "http://fwc.gitter.org/services/";

	public FindInstitutionsResponse findInstitutions(FindInstitutionsRequest request)
			throws FwcServiceException;

}