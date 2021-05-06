
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static final int ITEMS_COUNT = 12;
    private static List<Neighbour> neighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    private static final int NEIGHBOUR_TESTED = 3;
    private static final Neighbour neighbourTested = neighbours.get(NEIGHBOUR_TESTED);

    private ActivityScenario mActivity;

    @Rule
    public ActivityScenarioRule mActivityRule = new ActivityScenarioRule(ListNeighbourActivity.class);


    @Before
    public void setUp() {
        mActivity = mActivityRule.getScenario();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(ViewMatchers.withId(R.id.fragment_list_neighbours), withContentDescription(String.valueOf(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.fragment_list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.fragment_list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.fragment_list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }
    /**
     * When we click an item, the neighbour details page is displayed
     */
    @Test
    public void myNeighbourList_clickOnNeighbour_shouldDisplayNeighbourDetails() {
        //Given : Choose a neighbour in the list to see his/her details
        onView(withText(R.string.tab_neighbour_title))
                .perform(click());
        //When : Perform a click on the item
        onView(allOf(withId(R.id.fragment_list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(NEIGHBOUR_TESTED,click()));
        //Then : The neighbour details page displays
        onView(withId(R.id.fragment_list_neighbours))
                .check(matches(isDisplayed()));
    }

    /**
     * When favorite list is displayed its only shows favorite neighbours
     */
    @Test
    public void myFavoriteNeighboursList_containsOnlyFavorites() {
        // Given : set 2 neighbours as favorites
        neighbours.get(2).setFavoris(true);
        neighbours.get(3).setFavoris(true);
        // When : we display favorite list
        onView(withText(R.string.tab_favorites_title))
                .perform(click());
        // The number of elements is only 2 and they contain the 2 favorite neighbours names
       //onView((R.id.fragment_list_neighbours.FRAG_POS).get(0))
       //        .check(matches(withText(neighbours.get(0).getName())));
       //onView((R.id.fragment_list_neighbours.FRAG_POS).get(1))
       //        .check(matches(withText(neighbours.get(1).getName())));
    }

    /**
     * When we click on neighbour , the NeighbourDetailActivity be open
     */
    @Test
    public void myNeighbourClicked_shouldDisplayNeighbourNameOnDetails() {
        // Given : Choose a neighbour in the list to see his/her details

        // When : Perform a click on the item

        // Then : Check that the name of the TextView on the Detail page matches the name of the neighbor
    }


    /**
     * When we click on fab button on neighbour list , the addNeighbourActivity be open
     */
    @Test
    public void myNeighboursList_FabAction_shouldOpenActivity_add_neighbour() {
        // Given : Click Neighbour Fab Button

        // When : we Are On Neighbour list

        // Then : Activity_add_neighbour to be open

    }

    /**
     * When
     */
    @Test
    public void myClickAndAddToFavoriteTwoNeighboursAndBack_swipeRightOnListNeighbourActivity_displayedTheTwoNeighbourOnFavoriteList() {
        // Given : favorites list clear and check that list is empty

        // Given : perform click on the neighbor of position 0 then click on the fav button then click on back

        // Given : perform click on the neighbor of position 1 then click on the fav button then click on back

        // When : perform Swipe left

        // Then : check that the number of favorites in the list is 2

        // Then : check that the favorites in the list are objects 1 and 2

    }

    /**
     * When we save a new neighbor, it is added to the list
     */
    @Test
    public void myCompletedNeighborAddActivity_clickOnSaveButton_shouldAddNeighbourToList() {
        // Given : We complete Neighbor_Add_Activity form

        //When : perform click save Button

        //Then : Neighbor Add To List an be 13
    }
}


