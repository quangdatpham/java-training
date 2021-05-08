package _10_collections._09_sets_challenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static Map<HeavenlyBody.Key, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {

        // 1. The planets and moons that we added in the previous video should appear in
        // the solarSystem collection and in the sets of moons for the appropriate planets.
        //

        // 2. a.equals(b) must return the same result as b.equals(a) - equals is symmetric.
        //
        HeavenlyBody pluto = new DwarfPlanet("Pluto", 842);
        HeavenlyBody earth1 = new Planet("Earth", 365);
        HeavenlyBody earth2 = new Planet("Earth", 365);
        System.out.println(earth1.equals(earth2));
        System.out.println(earth2.equals(earth1));
        System.out.println(earth1.equals(pluto));
        System.out.println(pluto.equals(earth1));

        // 3. Attempting to add a duplicate to a Set must result in no change to the set (so
        // the original value is not replaced by the new one).
        //
        System.out.println();
        HeavenlyBody anotherPluto = new DwarfPlanet("Pluto", 900);
        planets.add(pluto);
        planets.add(anotherPluto);

        // 4. Attempting to add a duplicate to a Map results in the original being replaced
        // by the new object.
        //
        System.out.println();
        solarSystem.put(pluto.getKey(), pluto);
        System.out.println(solarSystem.containsKey(anotherPluto.getKey()));
        solarSystem.put(anotherPluto.getKey(), anotherPluto);
        System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto", HeavenlyBody.BodyTypes.PLANET)));
        System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto", HeavenlyBody.BodyTypes.DWARF_PLANET)));

        // 5. Two bodies with the same name but different designations can be added to the same set.
        //
        // 6. Two bodies with the same name but different designations can be added to the same map,
        // and can be retrieved from the map.
        HeavenlyBody uranus = new Planet("Uranus", 30660);
        solarSystem.put(uranus.getKey(), uranus);
        planets.add(uranus);


        System.out.println();
        System.out.println("The planets contains:");
        for (HeavenlyBody planet : planets) {
            System.out.println(planet);
        }

        System.out.println();
        System.out.println("The solar system contains:");
        for (HeavenlyBody heavenlyBody : solarSystem.values()) {
            System.out.println(heavenlyBody);
        }
    }
}
