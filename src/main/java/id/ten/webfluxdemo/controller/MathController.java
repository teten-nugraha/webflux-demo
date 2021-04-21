package id.ten.webfluxdemo.controller;

import id.ten.webfluxdemo.dto.Response;
import id.ten.webfluxdemo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("math")
public class MathController {

    @Autowired
    private MathService mathService;

    @GetMapping("/square/{input}")
    @ResponseStatus(HttpStatus.OK)
    public Response findSquare(@PathVariable int input) {
        return mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    @ResponseStatus(HttpStatus.OK)
    public List<Response> table(@PathVariable int input) {
        return mathService.multiplicationTable(input);
    }
}

