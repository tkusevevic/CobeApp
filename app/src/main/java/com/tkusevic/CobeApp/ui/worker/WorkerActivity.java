package com.tkusevic.CobeApp.ui.worker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.constants.AppConstants;
import com.tkusevic.CobeApp.ui.login.LoginActivity;
import com.tkusevic.CobeApp.ui.worker.pager.PagerAdapter;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class WorkerActivity extends AppCompatActivity implements View.OnClickListener {

    TabLayout tabLayout;
    PagerAdapter navigationPagerAdapter;
    ViewPager viewPager;
    Button signOut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        initUI();
        initTabs();
        initAdapter();
        initViewPager();
        initListeners();
    }

    private void initListeners() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        signOut.setOnClickListener(this);
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(navigationPagerAdapter);
    }

    private void initAdapter() {
        navigationPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        navigationPagerAdapter.addFragment(new ProductFragment());
        navigationPagerAdapter.addFragment(new WorkerFragment());
        navigationPagerAdapter.addFragment(new BillsFragment());
    }

    private void initUI() {
        signOut = (Button) findViewById(R.id.signOut);
    }

    private void initTabs() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(AppConstants.TAB1));
        tabLayout.addTab(tabLayout.newTab().setText(AppConstants.TAB2));
        tabLayout.addTab(tabLayout.newTab().setText(AppConstants.TAB3));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signOut) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

