package com.example.dgbc.controller;

import com.example.dgbc.dto.FlaskRequest;
import com.example.dgbc.dto.FlaskResponse;
import com.example.dgbc.service.FlaskApiClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/python")
public class FlaskController {
    private final FlaskApiClient flaskApiClient;

    public FlaskController(FlaskApiClient flaskApiClient) {
        this.flaskApiClient = flaskApiClient;
    }

    /**
     * 클라이언트 -> (POST) /api/python/upload
     * 1. 요청 Body를 PythonRequest로 받음
     * 2. flaskApiClient를 통해 Flask 서버의 /upload 호출
     * 3. Flask 응답을 그대로 리턴
     */
    @PostMapping("/upload")
    public ResponseEntity<FlaskResponse> predict(@RequestBody FlaskRequest request) {

        FlaskResponse response = flaskApiClient.callFlaskUpload(request);

        return ResponseEntity.ok(response);
    }
}
