package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DetailNeighbourEvent {

    public Neighbour mNeighbour;

        public DetailNeighbourEvent(Neighbour mNeighbour) {
            this.mNeighbour = mNeighbour;
        }
    }
