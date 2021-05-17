package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     *
     * @param mPosition position of fragment
     */
    @Override
    public Fragment getItem(int mPosition) {
        return NeighbourFragment.newInstance(mPosition);
    }

    /**
     * get the number of pages
     *
     * @return number of pages
     */
    @Override
    public int getCount() {
        return 2;
    }
}