package com.lenovo.smarttraffic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

public class RouteStationFragment extends Fragment {

    private DrivePath mDrivePath;
    private DriveRouteResult mDriveRouteResult;
    private ListView mDriveSegmentList;
    private  DriveSegmentListAdapter mDriveSegmentListAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        mDrivePath = bundle.getParcelable("drive_path");
        View view = inflater.inflate(R.layout.route_station, container, false);
        mDriveSegmentList=view.findViewById(R.id.route_station_listview);
        mDriveSegmentListAdapter =new DriveSegmentListAdapter(getContext(),mDrivePath.getSteps() );
        mDriveSegmentList.setAdapter(mDriveSegmentListAdapter);



        getIntentData();
        return view;
    }



    private void getIntentData() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    /**
     * 驾车路线详情页Adapter
     */
    private class DriveSegmentListAdapter  extends BaseAdapter {
        private Context context;
        private List<DriveStep>  mItemList=new ArrayList<>();

        public DriveSegmentListAdapter(Context context, List<DriveStep> list) {
            this.context = context;
           mItemList.add(new DriveStep());
            for (DriveStep driveStep:list) {
            mItemList.add(driveStep);
            }
            mItemList.add(new DriveStep());
        }

        @Override
        public int getCount() {
            return mItemList.size();
        }

        @Override
        public Object getItem(int i) {
            return mItemList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
          ViewHolder viewHolder;
          DriveStep item=mItemList.get(position);
            if (view==null) {
                view=LayoutInflater.from(context).inflate(R.layout.item_bus_segment,null );
                viewHolder=new ViewHolder();
                viewHolder.driveDirIcon=view.findViewById(R.id.bus_dir_icon);
                viewHolder.driveLineName=view.findViewById(R.id.bus_line_name);
                viewHolder.driveDirUp=view.findViewById(R.id.bus_dir_icon_up);
                viewHolder.driveDirDown=view.findViewById(R.id.bus_dir_icon_down);
                viewHolder.splitLine=view.findViewById(R.id.bus_seg_split_line);
                view.setTag(viewHolder);

            }else {
                viewHolder= (ViewHolder) view.getTag();
            }
            if (position == 0) {
                viewHolder.driveDirIcon.setImageResource(R.drawable.dir_start);
                viewHolder.driveLineName.setText("出发");
                viewHolder.driveDirUp.setVisibility(View.GONE);
                viewHolder.driveDirDown.setVisibility(View.VISIBLE);
                viewHolder.splitLine.setVisibility(View.GONE);
                return view;
            } else if (position == mItemList.size() - 1) {
                viewHolder.driveDirIcon.setImageResource(R.drawable.dir_end);
                viewHolder.driveLineName.setText("到达终点");
                viewHolder.driveDirUp.setVisibility(View.VISIBLE);
                viewHolder.driveDirDown.setVisibility(View.GONE);
                viewHolder.splitLine.setVisibility(View.VISIBLE);
                return view;
            } else {
                String actionName = item.getAction();
                int resID = AMapUtil.getDriveActionID(actionName);
                viewHolder.driveDirIcon.setImageResource(resID);
                viewHolder.driveLineName.setText(item.getInstruction());
                viewHolder.driveDirUp.setVisibility(View.VISIBLE);
                viewHolder.driveDirDown.setVisibility(View.VISIBLE);
                viewHolder.splitLine.setVisibility(View.VISIBLE);
                return view;
            }
        }

        class ViewHolder{
            TextView driveLineName;
            ImageView driveDirIcon;
            ImageView driveDirUp;
            ImageView driveDirDown;
            ImageView splitLine;
        }
    }
}
