package nl.yacht.lakesideresort.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/")
public class HomeController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String home(Map<String, Object> model){
        return "index";
    }

    @RequestMapping(value="/guest", method= RequestMethod.GET)
    public String guest(Map<String, Object> model){
        return "guest";
    }

    @RequestMapping(value="/room", method= RequestMethod.GET)
    public String room(Map<String, Object> model){
        return "room";
    }

    @RequestMapping(value="/booking", method= RequestMethod.GET)
    public String booking(){
        return "booking";
    }

    @RequestMapping(value="/overview", method= RequestMethod.GET)
    public String bookingOverview(){
        return "bookingOverview";
    }
}
