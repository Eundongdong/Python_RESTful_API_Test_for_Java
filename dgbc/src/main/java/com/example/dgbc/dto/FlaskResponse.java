package com.example.dgbc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlaskResponse {

    @JsonProperty("file_id")
    private String fileId;

    @JsonProperty("status")
    private String status;
    public FlaskResponse() {
    }

    public FlaskResponse(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
