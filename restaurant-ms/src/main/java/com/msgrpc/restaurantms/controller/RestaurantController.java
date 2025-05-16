package com.msgrpc.restaurantms.controller;

import com.msgrpc.restaurantms.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping(value = "/{restaurantId}/products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getProducts(@PathVariable Long restaurantId) {

        SseEmitter emitter = new SseEmitter();
        emitter.onTimeout(emitter::complete);
        restaurantService.getProducts(restaurantId, emitter);

        return emitter;
    }


}
