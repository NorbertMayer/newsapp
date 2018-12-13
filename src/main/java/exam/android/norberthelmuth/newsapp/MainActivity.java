package exam.android.norberthelmuth.newsapp;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import exam.android.norberthelmuth.newsapp.Adapter.ViewPagerAdapter;
import exam.android.norberthelmuth.newsapp.Common.IOpenNewsMap;
import exam.android.norberthelmuth.newsapp.Retrofit.RetrofitClient;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.main_content);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(MODE_SCROLLABLE);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        List<String> categories = Arrays.asList(
                "business", "entertainment", "general", "health", "science", "sports", "technology");

        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String country = tm.getNetworkCountryIso();

        for (String category :
                categories) {
            String title = category.substring(0, 1).toUpperCase() + category.substring(1);
            adapter.addFragment(BBCNewsFragment.getInstance(country, category), title);
        }

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.bbc_news:
                getRO();
                return true;
            case R.id.bbc_sport:
                getGB();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // esti absolut geniu jur

    public String getRO() {
        return "RO";
    }

    public String getGB() {
        return "GB";
    }
}
