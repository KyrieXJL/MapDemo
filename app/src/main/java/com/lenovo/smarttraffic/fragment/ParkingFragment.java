package com.lenovo.smarttraffic.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.lenovo.smarttraffic.R;
import java.util.ArrayList;
import java.util.List;
public class ParkingFragment extends Fragment implements AMap.InfoWindowAdapter {
    private MapView map_mapview;
    private ListView map_list;
    private AMap aMap;
    private UiSettings uiSettings;
    private LatLngBounds.Builder builder = new LatLngBounds.Builder();//存放所有点的经纬度
    View id_window = null;
    private  List<Parking> parkings;
    private  ListViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.author_view, container, false);
        map_mapview = (MapView) view.findViewById(R.id.map_mapview);
        map_list = (ListView) view.findViewById(R.id.map_list);
        if (aMap == null) {
            aMap = map_mapview.getMap();
            uiSettings = aMap.getUiSettings();
            //设置地图属性
            setMapAttribute();
        }
        for (int i = 0; i < 5; i++) {
            LatLng latLng = new LatLng(39.9 + Math.random(), 116.3 + Math.random());
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(latLng);
            markerOption.title("");
            markerOption.snippet("");
            //自定义点标记的icon图标为drawable文件下图片
            switch (i) {
                case 0:
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_one)));
                    break;
                case 1:
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_second)));
                    break;
                case 2:
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_thrid)));
                    break;
                case 3:
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_forth)));
                    break;
                case 4:
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_self)));
                    break;
                case 5:
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_self)));
                    break;
                default:
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_self)));
                    break;
            }
            markerOption.draggable(false);
            aMap.addMarker(markerOption);
            //将所有marker经纬度include到boundsBuilder中
            builder.include(latLng);
        }
        //更新地图状态
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 10));
        map_mapview.onCreate(savedInstanceState);

        parkings=new ArrayList<>();
        adapter=new ListViewAdapter(getContext(),R.layout.park_child ,parkings );


        Parking parking=new Parking(1,"丽豪园小区停车场","空车位32个,停车费5元/小时,北京市西城区大石桥胡同55号,后门丽豪园小区  xxxm");
        Parking parking1=new Parking(2,"新街口东街地上停车场","空车位32个,停车费5元/小时,西城区新街口东街  xxxm");
        Parking parking2=new Parking(3,"新街口东街地上停车场","空车位32个,停车费5元/小时,西城区新街口东街");

        Parking parking3=new Parking(4,"新街口东街地上停车场","空车位32个,停车费5元/小时,西城区新街口东街  xxxm");

        parkings.add(parking);
        parkings.add(parking1);
        parkings.add(parking2);
        parkings.add(parking3);
        map_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }

    /**
     * 设置地图属性
     */
    private void setMapAttribute() {
        //设置自定义信息窗口
        aMap.setInfoWindowAdapter(this);
        //设置地图点击的事件监听
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map_mapview.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        map_mapview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map_mapview.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        map_mapview.onSaveInstanceState(outState);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        if (id_window == null) {
            id_window = LayoutInflater.from(getContext()).inflate(R.layout.amap_info_window, null);
        }
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


    class ListViewAdapter extends ArrayAdapter<Parking> {

        public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Parking> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;
            Parking parking = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.park_child, parent, false);
                viewHolder=new ViewHolder();
                viewHolder.id=convertView.findViewById(R.id.park_id_txt);
                viewHolder.where=convertView.findViewById(R.id.park_where_txt);
                viewHolder.money =convertView.findViewById(R.id.park_money_txt);
                viewHolder.iv_right=convertView.findViewById(R.id.iv_right);
                viewHolder.bg=convertView.findViewById(R.id.bg);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.id.setText(parking.getId()+"");
            viewHolder.where.setText(parking.getWhere());
            viewHolder.money.setText(parking.getMoney());
           viewHolder.iv_right.setOnClickListener(view -> {
               Toast.makeText(getContext(), "you clicked iv_right", Toast.LENGTH_SHORT).show();
               onDestroy();
               FragmentManager fragmentManager=getFragmentManager();
               fragmentManager.beginTransaction().replace(R.id.container,new RouteFragment() ).commit();
           });
           switch (position){
               case 2:
                   viewHolder.bg.setBackgroundColor(Color.parseColor("#FF646464"));
                   break;
               case 3:
                   viewHolder.bg.setBackgroundColor(Color.parseColor("#FF646464"));
                   break;
           }
            return convertView;
        }

        class ViewHolder {
            TextView id, where, money;
            ImageView iv_right;
            LinearLayout bg;
        }
    }

    private class Parking {
        private int id;
        private String where;
        private String money;

        public Parking() {
        }

        public Parking(int id, String where, String money) {
            this.id = id;
            this.where = where;
            this.money = money;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWhere() {
            return where;
        }

        public void setWhere(String where) {
            this.where = where;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}

