package com.example.dgbc.service;

import com.example.dgbc.dto.FlaskRequest;
import com.example.dgbc.dto.FlaskResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class FlaskApiClient {

    private final RestClient restClient;
    private final String flaskUploadPath;

    public FlaskApiClient(
            @Value("${python.api.base-url}") String baseUrl,
            @Value("${python.api.upload-path:/upload}") String flaskUploadPath
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.flaskUploadPath = flaskUploadPath;
    }

    public FlaskResponse callFlaskUpload(FlaskRequest request) {

        try {
            // Flask에서 FlaskResponse와 동일한 형태의 JSON을 반환한다고 가정
            return restClient
                    .post()
                    .uri(flaskUploadPath)
                    .contentType(MediaType.APPLICATION_JSON) // JSON으로 보낸다
                    .body(request)
                    .retrieve()
                    .body(FlaskResponse.class);
        } catch (RestClientException e) {
            // return new ThrowException(MsgConst.XXXX) ; 호출 실패


            FlaskResponse res = new FlaskResponse();
            res.setStatus("error");
            res.setFileId(null);
            return res;
        }
    }
}