package id.ten.webfluxdemo.controller;

import id.ten.webfluxdemo.dto.MultiplyRequestDto;
import id.ten.webfluxdemo.dto.Response;
import id.ten.webfluxdemo.exception.InputValidationException;
import id.ten.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("reactive/math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("/square/{input}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Response> findSquare(@PathVariable int input) {
        if(input < 10 || input > 20)
            throw new InputValidationException(input);
        return mathService.findSquare(input);
    }

    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Response> table(@PathVariable int input) {
        return mathService.multiplicationTable(input);
    }

    @GetMapping(value = "table/{input}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Response> table2(@PathVariable int input) {
        return mathService.multiplicationTable(input);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> dto){
        return mathService.multiply(dto);
    }
}
