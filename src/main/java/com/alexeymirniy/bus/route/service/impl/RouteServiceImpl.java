package com.alexeymirniy.bus.route.service.impl;

import com.alexeymirniy.bus.route.service.RouteService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

@Service
public class RouteServiceImpl implements RouteService {

    @Override
    public boolean isRoutePresent(int x, int y) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data/route.txt"));
            String line = reader.readLine();

            while (line != null) {

                int indexBegin = line.indexOf(" ");
                int[] stops = Arrays
                        .stream(line.substring(indexBegin + 1).split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                if (isRoutePresent(x, y, stops)) {
                    reader.close();
                    return true;
                }
                line = reader.readLine();
            }
            reader.close();
            return false;
        } catch (Exception e) {
            System.out.println("Exception in isRoutePresent method. Exception:\n" + e.getMessage());
        }
        return false;
    }

    public boolean isRoutePresent(int x, int y, int[] stops) {

        boolean resultX = false;
        boolean resultY = false;
        boolean isYAfterX = false;

        for (int i : stops) {
            if (i == x) {
                resultX = true;
                isYAfterX = false;
            }
            if (i == y) {
                resultY = true;
                isYAfterX = true;
            }
            if (resultX && resultY && isYAfterX) return true;
        }
        return false;
    }
}