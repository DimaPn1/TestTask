package net.library.controllers;

import net.library.dao.CreateTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String home(){
        CreateTable.CreateAndFillingTable();
        return "home";
    }
}
