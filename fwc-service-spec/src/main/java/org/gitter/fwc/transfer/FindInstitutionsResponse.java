package org.gitter.fwc.transfer;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://fwc.gitter.org/services/")
@XmlType(name = "institutionList")
public class FindInstitutionsResponse {
    public static final String NAME = "institutionList";

    private List<Institution> institutions;

    @XmlElement(name = "institution")
    public List<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }
}
