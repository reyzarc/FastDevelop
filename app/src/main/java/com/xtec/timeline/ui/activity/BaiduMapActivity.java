package com.xtec.timeline.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.xtec.timeline.R;
import com.xtec.timeline.utils.T;
import com.xtec.timeline.utils.Utils;
import com.xtec.timeline.widget.FastDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by 武昌丶鱼 on 2017/5/8.
 * Description:百度地图页面
 */

public class BaiduMapActivity extends BaseActivity{
    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.btn_enable_traffic)
    Button btnEnableTraffic;
    @BindView(R.id.btn_test)
    Button btnTest;
    private BaiduMap mBaiduMap;

    private LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true;
    public static final int PERMISSIONS_REQUEST_LOCATION = 103;

    /**
     * 最新一次的经纬度
     */
    public double mCurrentLantitude;
    public double mCurrentLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);
        ButterKnife.bind(this);
        mBaiduMap = mMapView.getMap();
        //检查是否有开启定位权限,如果未开启,则弹窗提示
        checkLocationPermission();

    }

    private void checkLocationPermission() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.requestEach(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.granted) {
                            startLocation();
                        } else if (permission.shouldShowRequestPermissionRationale) {//被拒绝的权限
                            showPermissionDialog("由于无法正常获取您的权限，好钱包无法正常使用，请开启权限", PERMISSIONS_REQUEST_LOCATION);
                        } else {
                            showSettingDialog("由于无法正常获取您的权限，好钱包无法正常使用，请开启权限：设置-应用-好钱包-权限",
                                    PERMISSIONS_REQUEST_LOCATION);//拒绝再次询问的权限  需要去设置中勾选
                        }
                    }
                });
    }

    private void showSettingDialog(String s, final int permissionsRequestLocation) {
        new FastDialog(this)
                .setTitle("警告")
                .setContent(s)
                .setPositiveButton("去设置", new FastDialog.OnClickListener() {
                    @Override
                    public void onClick(FastDialog dialog) {
                        Utils.startAppSetting(BaiduMapActivity.this,permissionsRequestLocation);
                    }
                })
                .setNegativeButton("取消", new FastDialog.OnClickListener() {
                    @Override
                    public void onClick(FastDialog dialog) {

                    }
                }).create().show();
    }

    private void showPermissionDialog(String s, final int permissionsRequestLocation) {
        new FastDialog(this)
                .setTitle("警告")
                .setContent(s)
                .setNegativeButton("取消", new FastDialog.OnClickListener() {
                    @Override
                    public void onClick(FastDialog dialog) {

                    }
                })
                .setPositiveButton("确定", new FastDialog.OnClickListener() {
                    @Override
                    public void onClick(FastDialog dialog) {
                            checkLocationPermission();
                    }
                }).create().show();

    }

    private void startLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        //注册定位的监听
        mLocClient.registerLocationListener(myListener);
        //
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setPriority(LocationClientOption.NetWorkFirst);//设置定位优先级 网络优先
        //判断是否需要定位当前地址
        option.setIsNeedAddress(true);
        //定位间隔时间
        option.setScanSpan(5000);
        //
        mLocClient.setLocOption(option);
        //开始定位
        mLocClient.start();
    }


    class  MyLocationListenner implements BDLocationListener {

        public void onReceiveLocation(BDLocation arg0) {
            // TODO Auto-generated method stub
            mCurrentLantitude = arg0.getLatitude();
            mCurrentLongitude = arg0.getLongitude();
            Log.e("reyzarc","kaaafaf---->"+arg0.getLatitude()+"=====>"+arg0.getLongitude());

            //吐司
            T.showShort(BaiduMapActivity.this, "经度" + arg0.getLongitude() + "纬度" + arg0.getLatitude() + "地址" + arg0.getAddrStr() + "街道" + arg0.getStreet() + "时间" + arg0.getTime());
            //定义Maker坐标点
            LatLng point = new LatLng(arg0.getLatitude(),arg0.getLongitude());
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_star);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option2 = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option2);


            // map view 销毁后不在处理新接收的位置
            if (arg0 == null || mMapView == null) {
                return;
            }
            //画出定位的区域
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(arg0.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    //方圆100米
                    .direction(100).latitude(arg0.getLatitude())
                    .longitude(arg0.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                //判断是否首次定位
                isFirstLoc = false;
                //定位当前所在位置
                LatLng ll = new LatLng(arg0.getLatitude(),arg0.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                //放大18倍
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mMapView = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    @OnClick({R.id.btn_enable_traffic, R.id.btn_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_enable_traffic://显示实时交通
                mBaiduMap.setTrafficEnabled(!mBaiduMap.isTrafficEnabled());
                break;
            case R.id.btn_test:
                break;
        }
    }





}
