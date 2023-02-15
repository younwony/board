package wony.dev.board.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "Hi, Wony World";
    }

    @GetMapping("/docs")
    public String docs(){
        return "html/docs/rest-test-api-guide";
    }
}
