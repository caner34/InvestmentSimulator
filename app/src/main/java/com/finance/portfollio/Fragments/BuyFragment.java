package com.finance.portfollio.Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.finance.portfollio.CustomViews.CustomToast;
import com.finance.portfollio.R;
import com.finance.portfollio.utils.DatabaseHelper;
import com.finance.portfollio.utils.DatabaseUtils;

// TODO -Sevket - Buy screen should be able to filter stocks by its sector.

public class BuyFragment extends Fragment implements View.OnClickListener {
    private Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    String stock_code;

    TextView tv_buy_frag_asset_category;
    TextView tv_buy_frag_stock_code;
    EditText edt_buy_amount_stock_code;
    Button buy_btn_buy_frag;

    public void init(View view) {
        tv_buy_frag_asset_category = view.findViewById(R.id.tv_buy_frag_asset_category);
        tv_buy_frag_stock_code = view.findViewById(R.id.tv_buy_frag_stock_code);
        edt_buy_amount_stock_code = view.findViewById(R.id.edt_buy_amount_stock_code);
        buy_btn_buy_frag = view.findViewById(R.id.buy_btn_buy_frag);
        buy_btn_buy_frag.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.purchase_buy_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init(view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            stock_code = bundle.getString("selected_stock_code");
        } else {
            Log.e("E", "bundle null BuyFragment");
            if(savedInstanceState != null){
                stock_code = savedInstanceState.getString("selected_stock_code");
            }else {
                Log.e("E", "savedInstanceState null BuyFragment");
            }
        }
        if(stock_code == null){
            Log.e("E", "stock code null BuyFragment");
        } else if(sqLiteDatabase == null){
            Log.e("E", "sqLiteDatabase null BuyFragment");
        } else {
            tv_buy_frag_asset_category.setText(DatabaseUtils.RetrieveAssetCategoryByAssetCode(stock_code, sqLiteDatabase));
            tv_buy_frag_stock_code.setText(stock_code);
        }
    }

    @Override
    public void onClick(View v) {
        int view_id = v.getId();
        if(view_id == R.id.buy_btn_buy_frag){
            Toast.makeText(context, "Buy", Toast.LENGTH_LONG).show();
            CustomToast customToast = new CustomToast(getActivity() , context, "custom toast");
        }
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
