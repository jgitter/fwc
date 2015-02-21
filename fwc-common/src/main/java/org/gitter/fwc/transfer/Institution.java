package org.gitter.fwc.transfer;

import java.util.Map;

import javax.xml.bind.annotation.XmlElementWrapper;

public class Institution {
    private Map<String, String> institutionData;

    @XmlElementWrapper(name = "institution")
    public Map<String, String> getInstitutionData() {
        return institutionData;
    }

    public void setInstitutionData(Map<String, String> institutionData) {
        this.institutionData = institutionData;
    }
}
