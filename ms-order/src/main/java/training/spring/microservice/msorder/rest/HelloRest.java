package training.spring.microservice.msorder.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.spring.microservice.msorder.rest.models.Person;
import training.spring.microservice.msorder.rest.models.Response;

@RestController
@RequestMapping("/greetings")
public class HelloRest {

    @GetMapping("/hello1/{name}/{surname}")
    public String hello1(@PathVariable String name,
                         @PathVariable String surname) {
        return "Hello 1 " + name + " " + surname;
    }

    @GetMapping("/hello2/{abc}")
    public String hello2(@PathVariable("abc") String name) {
        return "Hello 2 " + name;
    }

    // /greetings/hello3?abc=osman&xyz=yaycioglu
    @GetMapping("/hello3")
    public String hello3(@RequestParam("abc") String name,
                         @RequestParam("xyz") String surname) {
        return "Hello 3 " + name + " " + surname;
    }

    // /greetings/hello4/osman?surname=yaycioglu
    @GetMapping("/hello4/{name}")
    public String hello4(@PathVariable String name,
                         @RequestParam String surname) {
        return "Hello 4 " + name + " " + surname;
    }

    // /greetings/hello5/osman;surname=yaycioglu
    @GetMapping("/hello5/{name}")
    public String hello5(@PathVariable String name,
                         @MatrixVariable String surname) {
        return "Hello 5 " + name + " " + surname;
    }

    @PostMapping("/hello6")
    public String hello6(@RequestBody Person person) {
        return "Hello 6 : " + person;
    }

    // DO NOT USE
    @PostMapping("/hello7")
    public Response<String> hello7(@RequestBody Person person) {
        return new Response<>();
    }

    // DO NOT USE
    @PostMapping("/hello8/{op}")
    public ResponseEntity<?> hello8(@PathVariable String op,
                                    HttpServletRequest hsr) {
        switch (op) {
            case "add": {
                String abcLoc = hsr.getParameter("abc");
            }
            break;
        }
        return ResponseEntity.status(200)
                             .body("xyz");
    }

}
