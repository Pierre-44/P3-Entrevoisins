package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     * @param neighbour to delete
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour to create
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighboursFavoris(){
        List<Neighbour> favNeighbours = new ArrayList<>();
        for (int i = 0; i< neighbours.size(); i++)
            if (neighbours.get(i).getIsFavoris()){
                favNeighbours.add(neighbours.get(i));
            }
        return favNeighbours;
    }

    /**
     *
     * @param neighbour the neighbour add on favoris
     */
    @Override
    public void addNeighbourOnFavoris(Neighbour neighbour) {
        int index = neighbours.indexOf(neighbour);
        neighbours.get(index).setFavoris(true);
    }


    /**
     *
     * @param neighbour the neighbour remove on favoris
     */
    @Override
    public void removeNeighbourOnFavoris(Neighbour neighbour) {
        int index = neighbours.indexOf(neighbour);
        neighbours.get(index).setFavoris(false);
    }

}

