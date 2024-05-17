package com.example.fire.cap.component;


import com.example.fire.cap.Dao.RainRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ApiComponentTestTest {


    @Autowired
    ApiComponent apiComponent;

    @Autowired
    RainRepository rainRepository;

    @Test
    @DisplayName("API 호출 테스트")
    public void requestApiTest() {
        GetRainfallInfo getRainfallInfo = apiComponent.requestApi(1, 25);
        log.info("getRainfallInfo : {}", getRainfallInfo.getBody());
    }



    @Test
    @DisplayName("값 추가하는 테스트")
    public void addDataTest() {
        GetRainfallInfo getRainfallInfo = apiComponent.requestApi(1, 25);
        for (Item item : getRainfallInfo.getBody().getItems().getItem()) {
            log.info("item : {}", item.toString());
            rainRepository.addData(item);
        }
    }
}

