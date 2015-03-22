package org.gitter.fwc.transfer;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Institution {
    private Map<String, String> institutionData;

    @XmlElement
    public String getAddress() {
        return new StringBuilder().append(institutionData.get("ADDR")).append(" ")
                .append(institutionData.get("CITY")).append(", ")
                .append(institutionData.get("STABBR")).append(" ")
                .append(institutionData.get("ZIP")).toString();
    }

    @XmlTransient
    public Map<String, String> getInstitutionData() {
        return institutionData;
    }

    @XmlElement
    public String getInstitutionName() {
        return institutionData.get("INSTNM");
    }

    public void setInstitutionData(Map<String, String> institutionData) {
        this.institutionData = institutionData;
    }
}
