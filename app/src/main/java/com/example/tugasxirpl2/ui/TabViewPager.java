package com.example.tugasxirpl2.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.tugasxirpl2.R;
import com.example.tugasxirpl2.adapter.ViewPagerAdapter;
import com.example.tugasxirpl2.fragment.FragmentHome;
import com.example.tugasxirpl2.fragment.FragmentSecond;
import com.example.tugasxirpl2.fragment.FragmentThird;

public class TabViewPager extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabviewpager);
        toolbar = findViewById(R.id.toolbartab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tugas Tab ViewPager");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout)findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentHome(),"Home");
        adapter.AddFragment(new FragmentSecond(),"Second");
        adapter.AddFragment(new FragmentThird(),"Third");

        //setup adapter
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
