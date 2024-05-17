package com.example.fire.cap.Dao;


import com.example.fire.cap.component.Item;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class RainRepository {

    private static final  String COLLECTION_NAME = "rain";
    Firestore db = FirestoreClient.getFirestore();

    public void addData(Item item)  {

        Map<String, Object> docData = new HashMap<>();
        docData.put("accRain",item.getAccRain());
        docData.put("accRainDt",item.getAccRainDt());
        docData.put("lastRainDt",item.getLastRainDt());
        docData.put("clientId",item.getClientId());
        docData.put("level6",item.getLevel6());
        docData.put("level12",item.getLevel6());
        docData.put("clientName",item.getClientName());

        //docData.put("timeDay",item.getTimeDay());

        // 지금은 아이디가 랜덤
        // rain -> documents(측정 위치) -> timeDay(문서 ID)

        try {
            ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME).document(item.getTimeDay()).collection("data").document(item.getClientName()).set(docData);
            log.info("Added document with ID:{}",future.get().getUpdateTime());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
