package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService serviceApi;

    @Before
    public void setup() {
        serviceApi = DI.getNewInstanceApiService();
    }

    // Check that the getNeighbors method give the expected list of Neighbors
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = serviceApi.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    // Check that the deleteNeighbor method removes neighbor from the list of neighbors
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = serviceApi.getNeighbours().get(0);
        serviceApi.deleteNeighbour(neighbourToDelete);
        assertFalse(serviceApi.getNeighbours().contains(neighbourToDelete));
    }

    // Check that the createNeighbor method creates a new neighbor
    @Test
    public void createNeighboursWithSuccess() {
        int nbNeighbours = serviceApi.getNeighbours().size();
        Neighbour neighbourToAdd = serviceApi.getNeighbours().get(0);
        serviceApi.createNeighbour(neighbourToAdd);
        assertEquals(serviceApi.getNeighbours().size(), nbNeighbours + 1);
    }

    // Check that the addNeighbourOnFavoris method give the list of favorite Neighbors
    @Test
    public void getFavoriteNeighboursWithSuccess() {
        serviceApi.addNeighbourOnFavoris(serviceApi.getNeighbours().get(0));
        serviceApi.addNeighbourOnFavoris(serviceApi.getNeighbours().get(1));
        serviceApi.addNeighbourOnFavoris(serviceApi.getNeighbours().get(2));
        assertEquals(3, serviceApi.getNeighboursFavoris().size());
    }

    // Check that the addNeighbourOnFavoris method give the expected list of favorite Neighbors
    @Test
    public void addNeighbourOnFavorisWithSuccess() {

        serviceApi.addNeighbourOnFavoris(serviceApi.getNeighbours().get(0));
        serviceApi.addNeighbourOnFavoris(serviceApi.getNeighbours().get(1));
        List<Neighbour> expectedFavoriteNeighbours = serviceApi.getNeighbours().subList(0, 2);
        assertTrue(serviceApi.getNeighboursFavoris().containsAll(expectedFavoriteNeighbours));
    }

    // Check that the addNeighbourOnFavoris method give the expected object of favorite Neighbour
    @Test
    public void removeFavoriteNeighbourWithSuccess() {
        serviceApi.addNeighbourOnFavoris(serviceApi.getNeighbours().get(0));
        assertTrue(serviceApi.getNeighboursFavoris().contains(serviceApi.getNeighbours().get(0)));
        serviceApi.removeNeighbourOnFavoris(serviceApi.getNeighbours().get(0));
        assertFalse(serviceApi.getNeighboursFavoris().contains(serviceApi.getNeighbours().get(0)));
    }
}
