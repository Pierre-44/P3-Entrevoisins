package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    // TODO : addNeighbourOnFavoris : method to modified favoris parameter from false to true
    /**
     * Add neighbour on favoris.
     * @param neighbour the neighbour
     */
    void addNeighbourOnFavoris(Neighbour neighbour);

    // TODO : removeNeighbourOnFavoris : method to modified favoris parameter from true to false
    /**
     * Remove neighbour on favoris.
     * @param neighbour the neighbour
     */
    void removeNeighbourOnFavoris(Neighbour neighbour);

    // TODO :  getNeighboursFavoris : method to get all of favoris list


    List<Neighbour> getNeighboursFavoris();


}







