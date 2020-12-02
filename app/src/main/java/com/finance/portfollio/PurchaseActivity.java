package com.finance.portfollio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.finance.portfollio.Fragments.BuyFragment;
import com.finance.portfollio.Fragments.SellFragment;
import com.finance.portfollio.ViewAdapters.PurchaseViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


// -Sevket - Purchase Activity is a full-fledged activity which enables the user to purchase Ask/Bid (Sell/Puy) stocks and FOREX (foreign exchange).
// -Sevket - Purchase Activity GUI can enable the user to switch between Buy and Sell Screens Dynamically
// TODO -Sevket - Sell screen should display current stocks of the user to be sold on the market.
// TODO -Sevket - Buy screen should be able to filter stocks by its sector.
// TODO -Sevket - Bonus User might add some stocks to his/her favorites.
public class PurchaseActivity extends AppCompatActivity {
    TabLayout purchase_tab_layout;
    ViewPager purchase_viewpager;
    PurchaseViewPagerAdapter purchaseViewPagerAdapter;
    ArrayList<String> titleList;
    ArrayList<Fragment> fragmentsList;
    BuyFragment buyFragment;
    SellFragment sellFragment;

    public void init(){
        // Changing status bar color
        Window window = PurchaseActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(PurchaseActivity.this, R.color.purchase_layout_tab_layout_background_color));
        // init fragments
        purchase_tab_layout = findViewById(R.id.purchase_tab_layout);
        purchase_viewpager = findViewById(R.id.purchase_viewpager);
        titleList = new ArrayList<>();
        titleList.add("Buy");
        titleList.add("Sell");
        buyFragment = new BuyFragment();
        sellFragment = new SellFragment();
        fragmentsList = new ArrayList<>();
        fragmentsList.add(buyFragment);
        fragmentsList.add(sellFragment);
        purchaseViewPagerAdapter = new PurchaseViewPagerAdapter(titleList, fragmentsList,
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        purchase_viewpager.setAdapter(purchaseViewPagerAdapter);
        purchase_tab_layout.setupWithViewPager(purchase_viewpager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        init();
        // Get process type (sell/buy) from user with buttons
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String process = extras.getString("process");
            if(process.equals("buy")){
                purchase_viewpager.setCurrentItem(0);
            } else if(process.equals("sell")){
                purchase_viewpager.setCurrentItem(1);
            }
        }

    }

}