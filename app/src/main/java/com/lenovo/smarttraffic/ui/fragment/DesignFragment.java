package com.lenovo.smarttraffic.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.lenovo.smarttraffic.Constant;


/**
 * @author Amoly
 * @date 2019/4/11.
 * description：设计页面
 */
public class DesignFragment extends BaseFragment {
    private static DesignFragment instance = null;

    public static DesignFragment getInstance() {
        if (instance == null) {
            instance = new DesignFragment();
        }
        return instance;
    }


    @Override
    protected View getSuccessView() {
        TextView view = new TextView(getActivity());
        view.setText("创意设计");
        view.setTextColor(Color.RED);
        view.setTextSize(50);
        return view;
    }

    @Override
    protected Object requestData() {
        return Constant.STATE_SUCCEED;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        if (instance != null) {
            instance = null;
        }
        super.onDestroy();
    }

}
