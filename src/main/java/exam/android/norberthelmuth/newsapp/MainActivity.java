package exam.android.norberthelmuth.newsapp;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import exam.android.norberthelmuth.newsapp.Adapter.ViewPagerAdapter;


import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }
            }, SPLASH_TIME_OUT);

//        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.main_content);
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        //remove the arrow and title from the ActionBar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//
//        viewPager = (ViewPager)findViewById(R.id.view_pager);
//        setupViewPager(viewPager);
//        tabLayout = (TabLayout)findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(MODE_SCROLLABLE);

    }

//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//
//        // create a List with news category
//        List<String> categories = Arrays.asList(
//                "business", "entertainment", "general", "health", "science", "sports", "technology");
//
//        // get info about device
//        //TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
//        //String country = tm.getNetworkCountryIso();
//
//        // Get the country code
//        String country =  Locale.getDefault().getCountry();
//
//        for (String category :
//                categories) {
//            String title = category.substring(0, 1).toUpperCase() + category.substring(1);
//            if (country != null) {
//                adapter.addFragment(NewsFragment.getInstance(country, category), title);
//            } else {
//                adapter.addFragment(NewsFragment.getInstance("GB", category), title);
//            }
//        }
//        // dynamic fragment
//        viewPager.setAdapter(adapter);
//    }
}
