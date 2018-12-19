package io.github.kjm015.kylenewer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This controller is in place in case we want to implement a home page or MVC functionality
 * sometime in the future. Right now, it doesn't really do much.
 *
 * @author kjm015
 * @since 7/26/2018
 */
@RestController
public class KyleController {

    /**
     * Tell the world hello! This is currently the placeholder home page.
     *
     * @return a pleasant greeting
     * @since 7/26/2018
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String sayHello() {
        return "Hello, world!";
    }

}
