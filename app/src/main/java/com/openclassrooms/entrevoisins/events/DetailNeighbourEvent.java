package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by pmeignen on 15/04/2021.
 */

public class DetailNeighbourEvent {

    public Neighbour mNeighbour;

        public DetailNeighbourEvent(Neighbour mNeighbour) {
            this.mNeighbour = mNeighbour;
        }
    }
