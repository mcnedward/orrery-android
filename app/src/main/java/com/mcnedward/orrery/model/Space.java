package com.mcnedward.orrery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 15/12/15.
 */
public class Space {

    private List<Planet> mPlanets;

    public Space() {
        mPlanets = new ArrayList<>();
    }

    public void addPlanet(Planet planet) {
        mPlanets.add(planet);
    }

    public List<Planet> getPlanets() {
        return mPlanets;
    }

    public void udpatePlanet(Planet planet) {
    }

}
