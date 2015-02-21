package org.gitter.fwc.ckan;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CkanResponseEntity {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResultWrapperEntity {
        @JsonProperty("resource_id") private String resourceId;
        @JsonProperty("fields") private List<Map<String, String>> fields;
        @JsonProperty("records") private List<Map<String, String>> records;

        public List<Map<String, String>> getFields() {
            return fields;
        }

        public List<Map<String, String>> getRecords() {
            return records;
        }

        public String getResourceId() {
            return resourceId;
        }

        public void setFields(List<Map<String, String>> fields) {
            this.fields = fields;
        }

        public void setRecords(List<Map<String, String>> records) {
            this.records = records;
        }

        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }
    }

    @JsonProperty("help") private String help;
    @JsonProperty("success") private boolean success;
    @JsonProperty("result") private ResultWrapperEntity result;

    public String getHelp() {
        return help;
    }

    public ResultWrapperEntity getResult() {
        return result;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public void setResult(ResultWrapperEntity result) {
        this.result = result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
