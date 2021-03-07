package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //controller 어노테이션 기입
public class HelloController {

    @GetMapping("hello") //webapp에서 /hello가 들어오면 이 메소드를 호출
    public String hello(Model model) {
                            //key(data), value(hello)
        model.addAttribute("data", "hello!!");
        // hello.html파일을 찾아 렌더링할 것
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name",name);
        return "hello-template";

    }

    @GetMapping("hello-string")
    //http 통신 프로토콜에서 body부에 데이터를 직접 넣어주겠다는 뜻(응답 body부)
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //helllo 입력한 문자로 그대로 전달
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        //객체가 오면, JsonConverter가 동작하여 json 형태로 반환 {key, value}
        return hello;
    }

    static class Hello {
        //private이기 때문에 method를 통해 접근
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
