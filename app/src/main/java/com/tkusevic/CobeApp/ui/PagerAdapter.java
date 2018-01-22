package com.tkusevic.CobeApp.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments = new ArrayList<>();

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProductFragment tab1 = new ProductFragment();
                return tab1;
            case 1:
                WorkerFragment tab2 = new WorkerFragment();
                return tab2;
            case 2:
                RecipiesFragment tab3 = new RecipiesFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "PROIZVODI";
            case 1:
                return "RADNICI";
            case 2:
                return "RACUNI";

        }
        return null;
    }
}
