from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route("/upload", methods=["POST"])
def upload():
    """
    예상 요청(JSON):
    {
      "FILE_NAME": "sample.txt"
    }
    """

    data = request.get_json()
    if not data:
        return jsonify({"status": "error", "message": "JSON Body 없음"}), 400

    print(data)
    file_name = data.get("file_name")
    print(file_name)
    if not file_name:
        return jsonify({"status": "error", "message": "FILE_NAME 필드 없음"}), 400


    file_id = file_name+"_testID"

    # 여기에서 file_name을 가지고 원하는 로직 수행
    # 예: DB 조회, 벡터 스토어 검색, 로그 저장 등
    # 지금은 테스트용으로 그대로 리턴만 하자.
    return jsonify({
        "status": "ok",
        "file_id": file_id
    }), 200

@app.route("/test", methods=["GET"])
def test():
    return {"status": "flask-ok"}

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)