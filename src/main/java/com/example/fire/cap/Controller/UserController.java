package com.example.fire.cap.Controller;

import com.example.fire.cap.Entity.User;
import com.example.fire.cap.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/exam/svc/v1")
public class UserController {

        private final UserService userService;

        @GetMapping("/user")
        public ResponseEntity<Object> getUsers() throws ExecutionException, InterruptedException {
            List<User> list = userService.getUsers();
            return ResponseEntity.ok().body(list);
        }

        @GetMapping("/users")
        public String getUsers(Model model) throws ExecutionException, InterruptedException{
            List<User> users = userService.getUsers();
            model.addAttribute("list", users);
            return "userTable"; // userTable.html 템플릿을 렌더링합니다.
        }

        @GetMapping("/search")
         public ResponseEntity<Object> getUserBysearch(String id) throws ExecutionException, InterruptedException {

            String userId = "2";

            User user = userService.getUserById(userId);
            //User user = userService.getUserById(id);
            if (user != null) {
                // 사용자 정보 출력
                System.out.println("사용자 이름: " + user.getName());
                System.out.println("사용자 이메일: " + user.getEmail());
                // 필요한 경우 기타 사용자 속성을 출력
            } else {
                System.out.println("해당 ID의 사용자를 찾을 수 없습니다.");
            }
            return ResponseEntity.ok().body(user);
        }


    }
