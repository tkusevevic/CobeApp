package com.tkusevic.CobeApp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tkusevic.CobeApp.R;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class WorkerActivity extends AppCompatActivity {


    private final Data data = App.getData();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerAdapter navigationPagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        navigationPagerAdapter.addFragment(new ProductFragment());
        navigationPagerAdapter.addFragment(new WorkerFragment());
        navigationPagerAdapter.addFragment(new RecipiesFragment());

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(navigationPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void signOut(View view) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        this.startActivity(myIntent);
        finish();
    }


}
