package com.lenovo.smarttraffic.fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_DEFAULT;

public class RouteFragment extends Fragment implements RouteSearch.OnRouteSearchListener {

    private MapView route_map;
  private  TextView more_Info_btn;
    private AMap aMap;
    private RouteSearch routeSearch;
    private  LatLng  startLatLng;
    private  LatLng  endLatLng;
    private DriveRouteResult mDriveRouteResult;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.route_view, container, false);
        routeSearch=new RouteSearch(getContext());
        more_Info_btn=view.findViewById(R.id.more_Info_btn);
        route_map = (MapView) view.findViewById(R.id.route_map);
        routeSearch.setRouteSearchListener(this);
        if (aMap==null) {
            aMap=route_map.getMap();
        }
        route_map.onCreate(savedInstanceState);
        startLatLng=new LatLng(39.942295, 116.335891 );
        endLatLng=new LatLng(39.995576, 116.481288);
       // setfromandtoMarker();
        LatLonPoint startPoint=new LatLonPoint(startLatLng.latitude,startLatLng.longitude );
        LatLonPoint endPoint=new LatLonPoint(endLatLng.latitude,endLatLng.longitude );
        RouteSearch.FromAndTo fromAndTo=new RouteSearch.FromAndTo(startPoint,endPoint);
        RouteSearch.DriveRouteQuery query=new RouteSearch.DriveRouteQuery(fromAndTo, DRIVING_SINGLE_DEFAULT,null,null,"");
        routeSearch.calculateDriveRouteAsyn(query);
        return view;
    }
 /*   private void setfromandtoMarker() {
        aMap.addMarker(new MarkerOptions()
                .position(startLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.start)));
        aMap.addMarker(new MarkerOptions()
                .position(endLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.end)));
    }*/
    /**
     * 实现不同路线查询的回调方法
     * */
    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
    }
    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int errorCode) {
        aMap.clear();// 清理地图上的所有覆盖物
        mDriveRouteResult = driveRouteResult;
    //    aMap.addPolyline(new PolylineOptions().color(Color.parseColor("#0000FF ")));


        final DrivePath drivePath = mDriveRouteResult.getPaths().get(0);
        DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(getContext(), aMap, drivePath, mDriveRouteResult.getStartPos(), mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();

       /**
         * 途径地点
         **/
        more_Info_btn.setOnClickListener( view1 -> {
            this.onDestroy();
            FragmentManager fragmentManager=getFragmentManager();
            RouteStationFragment routeStationFragment=new RouteStationFragment();
            Bundle bundle=new Bundle();
            bundle.putParcelable("drive_path",drivePath );
            fragmentManager.beginTransaction().replace(R.id.container,routeStationFragment).commit();
            routeStationFragment.setArguments(bundle);
        });
    }
    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
    }
    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        route_map.onResume();
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        route_map.onPause();
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        route_map.onSaveInstanceState(outState);
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        route_map.onDestroy();
    }
}
