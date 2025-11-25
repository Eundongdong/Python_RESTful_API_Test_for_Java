## Java Server에서 Python 통신 - RESTful API Flask 활용


Java 서버 호출 

- Java Controller 
  ```
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
  ```


Python Flask
```

@app.route("/upload", methods=["POST"])
def upload():
    """
    예상 요청(JSON):
    {
      "FILE_NAME": "/경로/sample.txt"
    }
    """

    data = request.get_json()
    if not data:
        return jsonify({"status": "error", "message": "JSON Body 없음"}), 400

    
    file_name = data.get("file_name")
    # 대량파일업로드 함수 호출
    # fnFileUpload(file_name)
    if not file_name:
        return jsonify({"status": "error", "message": "FILE_NAME 필드 없음"}), 400


    file_id = file_name+"_testID"

    return jsonify({
        "status": "ok",
        "file_id": file_id
    }), 200
```



### 실제 테스트 확인

통신 확인
<img width="662" height="315" alt="image" src="https://github.com/user-attachments/assets/f2462f8d-02ca-4c8d-a36e-8ddde13ec804" />



### TODO
- [ ] iframework 상에서 HTTP 호출 방법 동일한지 확인 필요 
