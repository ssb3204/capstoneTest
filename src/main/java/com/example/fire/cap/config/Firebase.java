package com.example.fire.cap.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
public class Firebase {

        @PostConstruct
        public void init(){
            try{

                FileInputStream serviceAccount =
                        new FileInputStream(ClassLoader.getSystemResource("firebaseKey.json").getPath());
                        //new FileInputStream("src/main/resources/firebaseKey.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}
