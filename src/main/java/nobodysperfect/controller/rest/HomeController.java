package nobodysperfect.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class HomeController extends RestController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

}
