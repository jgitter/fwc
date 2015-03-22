package org.gitter.fwc.endpoints;

import java.util.List;
import java.util.Map;

import org.gitter.fwc.exception.FwcServiceException;
import org.gitter.fwc.services.InstitutionRestController;
import org.gitter.fwc.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/institution")
public class InstitutionRestControllerImpl implements InstitutionRestController {

	@Autowired
	private InstitutionService service;

	@Override
	@RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> findInstitutions(
			@PathVariable("keyword") String keyword) throws FwcServiceException {
		return service.findByKeyword(keyword);
	}
}
