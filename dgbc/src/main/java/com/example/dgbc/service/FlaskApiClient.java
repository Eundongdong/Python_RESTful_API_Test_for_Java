package com.example.dgbc.service;

import com.example.dgbc.dto.FlaskRequest;
import com.example.dgbc.dto.FlaskResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
@Service
public class FlaskApiClient {

//    private final RestClient restClient; // java 17
    private final String flaskUploadPath;
    private RestTemplate restTemplate;
    private String baseUrl;
    public FlaskApiClient(
            @Value("${python.api.base-url}") String baseUrl,
            @Value("${python.api.upload-path:/upload}") String flaskUploadPath
    ) {

        /* java 17 */
//        this.restClient = RestClient.builder()
//                .baseUrl(baseUrl)
//                .build();
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;

        this.flaskUploadPath = flaskUploadPath;
    }

    public FlaskResponse callFlaskUpload(FlaskRequest request) {

        try {

            //TODO 1. 파일 지정된 경로에 올리기 FTP
            // String lsFileMngNo = formObject.getFILE_MNG_NO();
            // FTP로 upload
            // String lsFileUploadPath = "..."
            // File file = new File() ...


            //2. Flask 호출
            // Flask에서 FlaskResponse와 동일한 형태의 JSON을 반환한다고 가정
            /* java 1.6 */
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<FlaskRequest> entity = new HttpEntity<FlaskRequest>(request, headers);

            return restTemplate.postForObject(
                    baseUrl + flaskUploadPath,
                    entity,
                    FlaskResponse.class
            );

            /* java 17 */
//            FlaskResponse response = restClient
//                    .post()
//                    .uri(flaskUploadPath)
//                    .contentType(MediaType.APPLICATION_JSON) // JSON으로 보낸다
//                    .body(request)
//                    .retrieve()
//                    .body(FlaskResponse.class);



            //TODO 3. 전달받은 file_id 파일관리원장 AFTL_NAME에 넣기
            // if(응답이 정상이라면){  //ex : response :200
            // 파일관리원장VO inputVO= new 파일관리원장
            // inputVO.setFILE_MNG_NO(lsFileMngNo);
            // inputVO.setFILE_MNG_SRNO(BigDecimal.ONE);
            // inputVO.setAFTL_NAME(response.getFileId());
            // DBSession... (파일관리원장_U_000)  // 파일아이디 update

//             return response;
        } catch (RestClientException e) {
            // return new ThrowException(MsgConst.XXXX) ; 호출 실패


            FlaskResponse res = new FlaskResponse();
            res.setStatus("error");
            res.setFileId(null);
            return res;
        }
    }
}