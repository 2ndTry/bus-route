package com.alexeymirniy.bus.route.controller;

import com.alexeymirniy.bus.route.model.RouteResponse;
import com.alexeymirniy.bus.route.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RouteRestController {

    private final RouteService routeService;

    @PostMapping(value = "/api/direct")
    public RouteResponse checkRouteByStops(@RequestParam(value = "from") int x, @RequestParam(value = "to") int y) {

        boolean direct = routeService.isRoutePresent(x, y);

        return RouteResponse.builder()
                .from(x)
                .to(y)
                .direct(direct)
                .build();
    }
}