package com.tkusevic.CobeApp.ui;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class NavigationPagerAdapter extends FragmentPagerAdapter {
    Drawable myDrawable;
    String title;

    private List<Fragment> fragments = new ArrayList<>();

    public NavigationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                //myDrawable = getResources().getDrawable(R.drawable.img_section1);
                //title = getResources().getString(R.string.title_section1);
                return "PROIZVODI";
            case 1:
                return "RAČUNI";
            case 2:
                return "RADNICI";
        }
        SpannableStringBuilder sb = new SpannableStringBuilder("   " + title);
        try
        {
            myDrawable.setBounds(5, 5, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(myDrawable, DynamicDrawableSpan.ALIGN_BASELINE);
            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e)
        {
        }
        return null;
    }
}
