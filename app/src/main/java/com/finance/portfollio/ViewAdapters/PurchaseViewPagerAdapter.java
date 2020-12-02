package com.finance.portfollio.ViewAdapters;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;

public class PurchaseViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<String> titleList;
    ArrayList<Fragment> fragmentsList;

    public PurchaseViewPagerAdapter(ArrayList<String> titleList,
                                    ArrayList<Fragment> fragmentsList,
                                    @NonNull FragmentManager fragmentManager, int behavior) {
        super(fragmentManager, behavior);
        this.titleList = titleList;
        this.fragmentsList = fragmentsList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}