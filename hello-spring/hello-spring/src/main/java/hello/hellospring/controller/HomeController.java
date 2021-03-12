package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //localhost:8080/ 으로 들어왔을 때 호출
    public String home() {
        return "home"; //home.html 호출
    }
}
