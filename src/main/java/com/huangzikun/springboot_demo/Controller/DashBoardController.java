package com.huangzikun.springboot_demo.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class DashBoardController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Object index() {
        return "Hello World";
    }


}
