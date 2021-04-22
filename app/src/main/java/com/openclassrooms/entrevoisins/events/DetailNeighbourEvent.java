package com.openclassrooms.entrevoisins.events;

import androidx.fragment.app.Fragment;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DetailNeighbourEvent extends Fragment {

    public Neighbour mNeighbour;

        public DetailNeighbourEvent(Neighbour mNeighbour) {
            this.mNeighbour = mNeighbour;
        }
    }
