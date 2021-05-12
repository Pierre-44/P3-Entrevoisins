
package com.openclassrooms.entrevoisins.neighbour_list;

import android.view.View;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    private static final int ITEMS_COUNT = 12;
    private static final int FAVORITES_ITEMS_COUNT = 2;
    private List<Neighbour> neighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    private static final int NEIGHBOUR_TESTED_1 = 0;
    private static final int NEIGHBOUR_TESTED_2 = 2;
    private static final int NEIGHBOUR_TESTED_3 = 3;
    private static final int NEIGHBOUR_TESTED_4 = 4;

    public ActivityScenario mActivity;
    NeighbourApiService service;

    @Rule
    public ActivityScenarioRule mActivityRule = new ActivityScenarioRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getScenario();
        assertThat(mActivity, notNullValue());
        service = DI.getNeighbourApiService();
        neighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    }

    @After
    public void initData(){
        neighbours.get(NEIGHBOUR_TESTED_1).setFavoris(false);
        neighbours.get(NEIGHBOUR_TESTED_2).setFavoris(false);
        neighbours.get(NEIGHBOUR_TESTED_3).setFavoris(false);
        neighbours.get(NEIGHBOUR_TESTED_4).setFavoris(false);
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 0
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click an item, the neighbour details page is displayed
     */
    @Test
    public void myNeighbourList_clickOnNeighbour_shouldDisplayNeighbourDetails() {
        //Given : Choose a neighbour in the list to see his/her details
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .check(matches(isDisplayed()));
        //When : Perform a click on the item
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(NEIGHBOUR_TESTED_1, click()));
        //Then : The neighbour details page displays
        onView(withId(R.id.activity_neighbour_detail))
                .check(matches(isDisplayed()));
    }

    /**
     * When we click on neighbour , the NeighbourDetailActivity display correct neighbour name
     */
    @Test
    public void myNeighbourClicked_shouldDisplayNeighbourNameOnDetails() {
        // Given : Choose a neighbour in the list to see his/her details
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(RecyclerViewActions.actionOnItemAtPosition(NEIGHBOUR_TESTED_1, click()));
        // When : Neighbour details activity is displayed
        onView(withId(R.id.activity_neighbour_detail))
                .check(matches(isDisplayed()));
        // Then : Check that the name of the TextView on the Detail page matches the name of the neighbor
        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar_detail)))).check(matches(withText(service.getNeighbours().get(NEIGHBOUR_TESTED_1).getName())));
    }

    /**
     * When favorite list is displayed its only shows favorite neighbours
     */
    @Test
    public void myFavoriteNeighboursList_containsOnlyFavorites() {
        // Given : set 2 neighbours as favorites
        neighbours.get(2).setFavoris(true);
        neighbours.get(3).setFavoris(true);
        // When : we display favorite list with swipe left
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(swipeLeft());
        // The number of elements is only 2 and they contain the 2 favorite neighbours names
        onView(getViewByContentDescription(NeighbourFragment.FAVORITES_NEIGHBOURS_FRAGMENT_LIST))
                .check(withItemCount(FAVORITES_ITEMS_COUNT))
                .check(matches(hasDescendant(withText(service.getNeighbours().get(NEIGHBOUR_TESTED_2).getName()))))
                .check(matches(hasDescendant(withText(service.getNeighbours().get(NEIGHBOUR_TESTED_3).getName()))));
    }

    /**
     * When we add 2 neighbour on favorite by clicking on fav button on details page, and swipe to favorite , the 2 favorites neighbours are shown
     */
    @Test
    public void myClickAndAddToFavoriteTwoNeighbours_swipeLeftOnListNeighbourActivity_displayedTheTwoNeighbourOnFavoriteList() {
        // Given : perform click on the neighbor of position 0 then click on the fav button then click on back
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(RecyclerViewActions.actionOnItemAtPosition(NEIGHBOUR_TESTED_2, click()));
        onView(withId(R.id.button_favoris)).perform(click());
        pressBack();
        // Given : perform click on the neighbor of position 1 then click on the fav button then click on back
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(RecyclerViewActions.actionOnItemAtPosition(NEIGHBOUR_TESTED_3, click()));
        onView(withId(R.id.button_favoris)).perform(click());
        pressBack();
        // When : perform Swipe left to go to favorites
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(swipeLeft());
        // Then : check that the number of favorites in the list is 2
        onView(getViewByContentDescription(NeighbourFragment.FAVORITES_NEIGHBOURS_FRAGMENT_LIST))
                .check(withItemCount(2));
    }

    /**
     * When we add 2 neighbour on favorite by clicking on fav button on details page, and swipe to favorite , the correct favorite neighbours are shown
     */
    @Test
    public void myClickAgainOnFavoriteButton_RemoveFavoriteOfList() {
        // Given : perform click on the neighbor then click on the fav button then click on back
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(RecyclerViewActions.actionOnItemAtPosition(NEIGHBOUR_TESTED_4, click()));
        onView(withId(R.id.button_favoris)).perform(click());
        pressBack();
        // When : perform Swipe left to go to favorites
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST))
                .perform(swipeLeft());
        // When : Check the favorite list contain 1 favorite
        onView(getViewByContentDescription(NeighbourFragment.FAVORITES_NEIGHBOURS_FRAGMENT_LIST))
                .check(withItemCount(1));
        // When : Click again on favorite button
        onView(getViewByContentDescription(NeighbourFragment.FAVORITES_NEIGHBOURS_FRAGMENT_LIST))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.button_favoris)).perform(click());
        pressBack();
        swipeLeft();
        // Then : check that the favorites are 0
        onView(getViewByContentDescription(NeighbourFragment.FAVORITES_NEIGHBOURS_FRAGMENT_LIST))
                .check(withItemCount(0));
    }

    /**
     * When we click on fab button on neighbour list , the addNeighbourActivity be open
     */
    @Test
    public void myNeighboursList_FabAction_shouldOpenActivity_add_neighbour() {
        // Given : We Are On Neighbour list
        onView(getViewByContentDescription(NeighbourFragment.NEIGHBOURS_FRAGMENT_LIST));
        // When : Click Neighbour Fab Button
        onView(withId(R.id.add_neighbour)).perform(click());
        // Then : Activity_add_neighbour to be open
        onView(withId(R.id.activity_add_neighbour))
                .check(matches(isDisplayed()));
    }

    private static Matcher<View> getViewByContentDescription(int contentDescription) {
        return allOf(withId(R.id.fragment_list_neighbours), withContentDescription(String.valueOf(contentDescription)));
    }
}