package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "https://i.pravatar.cc/500?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 01","www.facebook.com/Caroline",  "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(2, "Jack", "https://i.pravatar.cc/500?u=a042581f4e29026704e", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 02","www.facebook.com/jack",  "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(3, "Chloé", "https://i.pravatar.cc/500?u=a042581f4e29026704f", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 03","www.facebook.com/Chloé",  "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(4, "Vincent", "https://i.pravatar.cc/500?u=a042581f4e29026704a", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 04", "www.facebook.com/Vincent", "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(5, "Elodie", "https://i.pravatar.cc/500?u=a042581f4e29026704b", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 05", "www.facebook.com/Elodie", "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(6, "Sylvain", "https://i.pravatar.cc/500?u=a042581f4e29026704c", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 06","www.facebook.com/Sylvain",  "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(7, "Laetitia", "https://i.pravatar.cc/500?u=a042581f4e29026703d", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 07","www.facebook.com/Laetitia",  "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(8, "Dan", "https://i.pravatar.cc/500?u=a042581f4e29026703b", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 08","www.facebook.com/Dan",   "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(9, "Joseph", "https://i.pravatar.cc/500?u=a042581f4e29026704g", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 09", "www.facebook.com/Joseph",  "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(10, "Emma", "https://i.pravatar.cc/500?u=a042581f4e29026706d", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 10", "www.facebook.com/Emma",  "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(11, "Patrick", "https://i.pravatar.cc/500?u=a042581f4e29026702d", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 11","www.facebook.com/Patrick",   "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false),
            new Neighbour(12, "Ludovic", "https://i.pravatar.cc/500?u=a042581f3e39026702d", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 12", "www.facebook.com/Ludovic",  "Bonjour ! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner ! J'aime les jeux de cartes tels la belote et le tarot..",false)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
