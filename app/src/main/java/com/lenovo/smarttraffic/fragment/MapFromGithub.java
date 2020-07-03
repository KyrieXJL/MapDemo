package com.lenovo.smarttraffic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.lenovo.smarttraffic.R;

public class MapFromGithub extends Fragment {
    private MapView map;
    private AMap aMap;
    private ImageView iv_first;
    private ImageView iv_second;
    private ImageView iv_third;
    private RelativeLayout mContainerLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_github, container, false);
        map = (MapView) view.findViewById(R.id.map);
        mContainerLayout = (RelativeLayout) view.findViewById(R.id.rl);
        iv_first = (ImageView) view.findViewById(R.id.iv_first);
        iv_second = (ImageView) view.findViewById(R.id.iv_second);
        iv_third = (ImageView) view.findViewById(R.id.iv_third);
        if (aMap == null) {
            aMap = map.getMap();
        }
        LatLng latLng=new LatLng(39.941853,116.385307  );
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
        map.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }
    //方法必须重写
    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }
    //方法必须重写
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }
    //方法必须重写
    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }
    }

