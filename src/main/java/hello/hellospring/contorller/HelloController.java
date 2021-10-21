package hello.hellospring.contorller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")//url을 get 했을 때 경
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // viewResolver가 resouces의 template package에서 해당 파일명을 찾는다.
    }
}
