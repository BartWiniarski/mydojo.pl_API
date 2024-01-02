package pl.mydojo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/status")
    @ResponseBody
    public String status() {

        return "I'm up and running!";
    }
}
