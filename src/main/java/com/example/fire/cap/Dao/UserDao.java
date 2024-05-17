package com.example.fire.cap.Dao;



import com.example.fire.cap.Entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
public class UserDao {

    public static final String COLLECTION_NAME = "User";

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        List<User> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(User.class));
        }
        return list;
    }

    public User getUserById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).whereEqualTo("id", id).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (!documents.isEmpty()) {
            QueryDocumentSnapshot document = documents.get(0); // 여기서는 첫 번째 문서만 사용합니다.
            return document.toObject(User.class);
        } else {
            return null;
        }
    }

    public static User getUserBysearch(String id) {
        // Firestore 인스턴스 가져오기
        Firestore db = FirestoreClient.getFirestore();

        // 해당 필드 값으로 검색
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);

        // 비동기적으로 문서 가져오기
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            // 문서 가져오기를 기다림
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // 문서가 존재하면 해당 문서의 데이터를 User 객체로 변환하여 반환
                return document.toObject(User.class);
            } else {
                // 문서가 존재하지 않으면 null 반환
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            // 에러 처리
            e.printStackTrace();
            return null;
        }
    }

    public static List<User> getUsersByField(String fieldName, String value) {
        List<User> userList = new ArrayList<>();

        // Firestore 인스턴스 가져오기
        Firestore db = FirestoreClient.getFirestore();

        // 입력 받은 필드명과 값으로 쿼리 생성
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                .whereEqualTo(fieldName, value)
                .get();

        try {
            // 쿼리 실행 및 결과 가져오기
            QuerySnapshot querySnapshot = future.get();

            // 결과 처리
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                // 각 문서를 User 객체로 변환하여 리스트에 추가
                userList.add(document.toObject(User.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            // 예외 처리
            e.printStackTrace();
        }

        return userList;
    }
}