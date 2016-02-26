package nobodysperfect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
class NobodysPerfectController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

}