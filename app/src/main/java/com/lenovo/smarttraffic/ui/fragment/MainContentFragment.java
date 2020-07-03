package com.lenovo.smarttraffic.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.lenovo.smarttraffic.Constant;
import com.lenovo.smarttraffic.R;



/**
 * @author Amoly
 * @date 2019/4/11.
 * description：主页面
 */
public class MainContentFragment extends BaseFragment {
    private static MainContentFragment instance = null;

    public static MainContentFragment getInstance() {
        if (instance == null) {
            instance = new MainContentFragment();
        }
        return instance;
    }



    @Override
    protected View getSuccessView() {
        TextView view = new TextView(getActivity());
        view.setText("主页面");
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
