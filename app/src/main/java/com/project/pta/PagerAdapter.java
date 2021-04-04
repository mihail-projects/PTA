package com.project.pta;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new LocalFragment();
            case 1:
                return new RemoteFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
