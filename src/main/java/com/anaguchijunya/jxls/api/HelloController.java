package com.anaguchijunya.jxls.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by junyaanaguchi on 2016/07/16.
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "Hello MVC";
    }

}
