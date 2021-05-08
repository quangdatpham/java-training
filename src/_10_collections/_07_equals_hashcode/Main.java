package _10_collections._07_equals_hashcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {
        HeavenlyBody mercury1 = new HeavenlyBody("Mercury", 88);
        solarSystem.put(mercury1.getName(), mercury1);
        planets.add(mercury1);

        HeavenlyBody mercury2 = new HeavenlyBody("Mercury", 90);
        solarSystem.put(mercury2.getName(), mercury2);
        planets.add(mercury2);

        for (HeavenlyBody planet : planets) {
            System.out.println(planet.getName() + " " + planet.getOrbitalPeriod());
        }

        System.out.println(mercury1.equals(mercury2));
    }
}
