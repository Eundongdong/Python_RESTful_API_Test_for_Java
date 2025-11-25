package com.example.dgbc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlaskRequest {
    // 필요한 input 값 추가될 가능성 있음


    @JsonProperty("file_name")   // JSON 키 이름
    private String fileName;

    public FlaskRequest(){
    }

    public FlaskRequest(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }
}
