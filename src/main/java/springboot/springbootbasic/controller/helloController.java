package springboot.springbootbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helloController {

    @GetMapping("hello") // "hello" 라는 url연결 경로
    public String hello(Model model) {
        model.addAttribute("data","hello!!"); // 이름:data ,값:hello!!
        return "hello"; // return의 "hello" 의미는 hello.html 파일로 연결된다는 의미!
    }
}
