package com.tkusevic.CobeApp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.utils.DataUtils;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class WorkerActivity extends AppCompatActivity {
    private final Data data = App.getData();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        NavigationPagerAdapter navigationPagerAdapter = new NavigationPagerAdapter(getSupportFragmentManager());
        navigationPagerAdapter.addFragment(new ProductFragment());
        navigationPagerAdapter.addFragment(new RecipiesFragment());
        navigationPagerAdapter.addFragment(new WorkerFragment());
        // TODO: 18.01.2018. add other fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(navigationPagerAdapter);


    }

    public void signOut(View view) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            this.startActivity(myIntent);
            finish();
    }
}
