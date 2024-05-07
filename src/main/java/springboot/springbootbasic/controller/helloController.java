package springboot.springbootbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class helloController {

    @GetMapping("hello") // "hello" 라는 url연결 경로
    public String hello(Model model) {
        model.addAttribute("data","hello!!"); // 이름:data ,값:hello!!
        return "hello"; // return의 "hello" 의미는 hello.html 파일로 연결된다ㅔ 의미!
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // localhost:8080/hello-mvc?name=spring!(name = 파라미터 넘겨줌)
        model.addAttribute("name", name);
        return "hello-template";
    }
}

