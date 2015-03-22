package org.gitter.fwc.transfer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://fwc.gitter.org/services/")
@XmlType(name = "findInstitutionsRequest")
public class FindInstitutionsRequest {
    public static final String NAME = "findInstitutionsRequest";

    private String keyword;

    @XmlElement(name = "keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
