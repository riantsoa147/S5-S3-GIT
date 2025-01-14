package mg.s5s3.controller;
import mg.s5s3.model.*;











import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GeneratorController {

    @GetMapping("/")
    public String sayHello() {
        return "index";
    }
}
