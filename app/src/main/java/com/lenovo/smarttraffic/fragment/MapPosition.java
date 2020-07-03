package com.lenovo.smarttraffic.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.lenovo.smarttraffic.R;

public class MapPosition extends Fragment {
    private MapView mapview;
    private ImageView iv_one;
    private ImageView iv_second;
    private ImageView iv_third;
    private RelativeLayout rl;
    private AMap aMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_position, container, false);
        mapview = (MapView) view.findViewById(R.id.map);
        iv_one = (ImageView) view.findViewById(R.id.iv_one);
        iv_second = (ImageView) view.findViewById(R.id.iv_second);
        iv_third = (ImageView) view.findViewById(R.id.iv_third);
        rl = (RelativeLayout) view.findViewById(R.id.rl);
        aMap = mapview.getMap();
        iv_one.setOnClickListener(view1 -> {
            //      Toast.makeText(getContext(), "you clicked first_icon", Toast.LENGTH_SHORT).show();
            LatLng latLng = new LatLng(39.941853, 116.385307);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("什刹海");
            markerOptions.snippet("什刹海");
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_self)));
            markerOptions.draggable(false);
            aMap.addMarker(markerOptions);
    aMap.setInfoWindowAdapter(new AMap.ImageInfoWindowAdapter() {
        @Override
        public long getInfoWindowUpdateTime() {
            return 0;
        }
        View  infowindow=null;

        @Override
        public View getInfoWindow(Marker marker) {
            if (infowindow==null) {
                infowindow=LayoutInflater.from(getContext()).inflate(R.layout.map_id_view,null,false );
            }
            return  infowindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    });
        });

        iv_second.setOnClickListener(view1 ->{
            Toast.makeText(getContext(), "点击了第二个图标", Toast.LENGTH_SHORT) .show();
            onDestroy();
            ParkingFragment parkingFragment=new ParkingFragment();
                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,parkingFragment ).commit();
                }
               );
        mapview.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }
}
