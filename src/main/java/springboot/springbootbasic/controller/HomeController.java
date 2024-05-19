package springboot.springbootbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // localhost8080 들어오면 "/"가 호출이 된다.
    public String home() {
        return "home"; // home.html이 실행이 됨
    }

}
