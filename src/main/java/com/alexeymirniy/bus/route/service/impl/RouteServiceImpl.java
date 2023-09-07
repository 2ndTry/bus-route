package com.alexeymirniy.bus.route.service.impl;

import com.alexeymirniy.bus.route.service.RouteService;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@Service
public class RouteServiceImpl implements RouteService {

    @Override
    public boolean isRoutePresent(int x, int y) {

        Map<Integer, List<Integer>> routeAndStops = new HashMap<>();

        try (Stream<String> inputDataStream = Files.lines(Path.of("src/main/resources/data/route.txt"))) {

            inputDataStream.parallel().forEach(line -> {
                List<Integer> stops = new ArrayList<>();
                Arrays.stream(line.split(" ")).skip(1).forEach(stop -> stops.add(Integer.parseInt(stop)));
                routeAndStops.put(Integer.parseInt(line.split(" ")[0]), stops);
            });

        } catch (Exception e) {
            System.out.println("Exception in isRoutePresent method. Exception:\n" + e.getMessage());
        }

        return checkRoute(x, y, routeAndStops);
    }

    private boolean checkRoute(int x, int y, Map<Integer, List<Integer>> routeAndStops) {

        boolean result = false;

        for (Map.Entry<Integer, List<Integer>> entry : routeAndStops.entrySet()) {

            if (entry.getValue().contains(x) && entry.getValue().contains(y)) {
                int indexX = entry.getValue().indexOf(x);
                int indexY = entry.getValue().indexOf(y);
                if (indexX < indexY) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}