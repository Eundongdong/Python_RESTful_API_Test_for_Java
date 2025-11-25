package com.example.dgbc.controller;

import com.example.dgbc.service.PythonTestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class PythonTestController {

    private final PythonTestClient pythonTestClient;

    public PythonTestController(PythonTestClient pythonTestClient) {
        this.pythonTestClient = pythonTestClient;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> testUpload(@RequestPart("file") MultipartFile file) throws Exception {

        String result = pythonTestClient.uploadTest(
                file.getBytes(),
                file.getOriginalFilename()
        );

        return ResponseEntity.ok(result);
    }

}
