package nl.yacht.lakesideresort.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class HomeController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String home(){
        return "index";
    }

    @RequestMapping(value="/guest", method= RequestMethod.GET)
    public String guest(){
        return "guest";
    }

    @RequestMapping(value="/room", method= RequestMethod.GET)
    public String room(){
        return "room";
    }

    @RequestMapping(value="/booking", method= RequestMethod.GET)
    public String booking(){
        return "booking";
    }
}
