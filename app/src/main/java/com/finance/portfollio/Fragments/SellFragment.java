package com.finance.portfollio.Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.finance.portfollio.R;
import com.finance.portfollio.utils.DatabaseHelper;

// TODO -Sevket - Sell screen should display current stocks of the user to be sold on the market.

public class SellFragment extends Fragment implements View.OnClickListener {
    private Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.purchase_sell_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        int view_id = v.getId();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
        this.sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    //Hide Keyboard
    public void HideKeyboard() {
        // Changing status bar color
        Window window = getActivity().getWindow();
        window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }
}
