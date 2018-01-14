package pl.szczygielski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szczygielski.domain.Movie;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index.html";
    }
}
