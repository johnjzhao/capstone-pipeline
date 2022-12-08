package net.sunvalle.controller;

/*
 * Created by John Zhao on 12/08/2022
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomePageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage(){
        return "index";
    }
}
