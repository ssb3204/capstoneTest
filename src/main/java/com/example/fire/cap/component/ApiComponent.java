package com.example.fire.cap.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class ApiComponent {

    @Value("${api-key}")
    String API_KEY;

    private final RestTemplate restTemplate = new RestTemplate();

    public GetRainfallInfo requestApi(int pageNo, int numOfRows ){

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("apis.data.go.kr")
                .path("/6260000/BusanRainfalldepthInfoService/getRainfallInfo")
                .queryParam("serviceKey",API_KEY)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows",numOfRows)
                .queryParam("resultType", "json")
                .build();

        log.info("URI:{}",uriComponents.toString());

        ResponseEntity<Root> result = restTemplate.getForEntity(uriComponents.toUri(), Root.class);

        log.info("Status Code:{}",result.getStatusCode());
        log.info("body:{}",result.getBody());

        Root body = result.getBody();
        if (body != null && body.getGetRainfallInfo() != null) {
            return body.getGetRainfallInfo();
        } else {
            log.error("API response or getRainfallInfo is null");
            return null;
        }

    }
}
