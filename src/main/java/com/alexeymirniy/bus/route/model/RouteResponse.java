package com.alexeymirniy.bus.route.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteResponse {
    private int from;
    private int to;
    private boolean direct;
}