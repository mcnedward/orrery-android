package com.mcnedward.orrery.model;

import android.graphics.Point;

import com.mcnedward.orrery.util.SpaceState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by edward on 15/12/15.
 */
public class Space {

    private List<Planet> planets;
    private SpaceState state;

    public Space() {
        planets = new ArrayList<>();
        state = SpaceState.CREATE;
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public void refresh() {
        planets = new ArrayList<>();
    }

    public void tryDeletePlanet(Point touch) {
        Iterator<Planet> iterator = planets.iterator();
        while (iterator.hasNext()) {
            Planet planet = iterator.next();
            if (planet.bounds().contains(touch.x, touch.y)) {
                iterator.remove();
            }
        }
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setState(SpaceState state) {
        this.state = state;
    }

    public SpaceState getState() {
        return state;
    }

}
