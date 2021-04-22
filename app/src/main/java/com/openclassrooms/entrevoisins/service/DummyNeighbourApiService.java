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
        List<Neighbour> FavNeighbours = new ArrayList<>();
        for (int i = 0; i< neighbours.size(); i++)
            if (neighbours.get(i).getIsFavoris()){
                FavNeighbours.add(neighbours.get(i));
            }
        return FavNeighbours;
    }

    /**
     *
     * @param neighbour the neighbour add on favoris
     */
    @Override
    public void addNeighbourOnFavoris(Neighbour neighbour) {
        neighbour.setFavoris(true);
    }

    /**
     *
     * @param neighbour the neighbour remove on favoris
     */
    @Override
    public void removeNeighbourOnFavoris(Neighbour neighbour) {
        neighbour.setFavoris(false);
    }

}

