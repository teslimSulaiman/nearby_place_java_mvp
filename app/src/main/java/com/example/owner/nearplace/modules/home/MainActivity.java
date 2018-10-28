package com.example.owner.nearplace.modules.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.example.owner.nearplace.BuildConfig;
import com.example.owner.nearplace.R;
import com.example.owner.nearplace.Utility;
import com.example.owner.nearplace.base.BaseActivity;
import com.example.owner.nearplace.di.components.DaggerNearbyPlaceComponent;
import com.example.owner.nearplace.di.module.NearbyPlaceModule;
import com.example.owner.nearplace.modules.detail.DetailActivity;
import com.example.owner.nearplace.modules.home.adapter.NearbyPlaceAdapter;
import com.example.owner.nearplace.mvp.model.Location;
import com.example.owner.nearplace.mvp.model.NearbyPlaceInfo;
import com.example.owner.nearplace.mvp.presenter.NearbyPlacePresenter;
import com.example.owner.nearplace.mvp.view.MainView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class MainActivity extends BaseActivity implements MainView {

    @Inject
    protected NearbyPlacePresenter mPresenter;
    private NearbyPlaceAdapter nearbyPlaceAdapter;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    // integer for permissions results request
    private static final int ALL_PERMISSIONS_RESULT = 1011;


    protected FusedLocationProviderClient mFusedLocationClient;

   @BindView(R.id.place_list)
    protected RecyclerView nearbyPlaceRecyclerView;

    private ArrayList<NearbyPlaceInfo> nearbyPlaceInfos = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstantState, Intent intent){

        addRequiredPermissions();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        super.onViewReady(savedInstantState, intent);
        initializeList();
        getLocation();

    }

    private void addRequiredPermissions() {
        // we add permissions we need to request location of the users
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionsToRequest = permissionsToRequest(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(
                        new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Utility.checkPlayServices(this,this)) {
            finish();
            onShowToast(getString(R.string.add_permission));
        }
    }

    private  boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }
    private   ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case ALL_PERMISSIONS_RESULT:
                for (String perm : permissionsToRequest) {
                    if (!hasPermission(perm)) {
                        permissionsRejected.add(perm);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            new AlertDialog.Builder(MainActivity.this).
                                    setMessage("These permissions are mandatory to get your location. You need to allow them.").
                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.
                                                        toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    }).setNegativeButton("Cancel", null).create().show();

                            return;
                        }else{
                            getLocation();

                        }
                    }
                } else {
                    getLocation();
                }

                break;
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<android.location.Location>() {
                    @Override
                    public void onSuccess(android.location.Location location) {
                        // Got last known location. In some rare situations, this can be null.
                        if (location != null) {

                            if(Utility.isNetworkAvailable(getApplicationContext())){
                                mPresenter.getNearbyPlaces(location.getLatitude()+","+location.getLongitude(),Utility.RADIUS, BuildConfig.PLACE_API_KEY);
                            }else {
                                onShowToast(getString(R.string.no_internet_connection));
                            }

                        }
                    }
                });
    }

    private void initializeList() {
        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        nearbyPlaceRecyclerView.setItemAnimator(animator);

        nearbyPlaceRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        nearbyPlaceRecyclerView.setLayoutManager(layoutManager);
        nearbyPlaceAdapter = new NearbyPlaceAdapter(getLayoutInflater(),nearbyPlaceInfos);
        nearbyPlaceAdapter.setOnNearbyPlaceClickListener(nearbyPlaceClickListener);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(nearbyPlaceAdapter);
        alphaAdapter.setDuration(1000);
        nearbyPlaceRecyclerView.setAdapter(alphaAdapter);

    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerNearbyPlaceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .nearbyPlaceModule(new NearbyPlaceModule(this))

                .build().inject(this);
    }

    @Override
    public void onNearbyPlacesLoaded(List<NearbyPlaceInfo> nearbyPlaceInfoList) {

        nearbyPlaceAdapter.addNearbyPlaces(nearbyPlaceInfoList);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClearItems() {
        nearbyPlaceAdapter.clearNearbyPlace();
    }

    @Override
    public void onLoadMore(String message) {

    }

    private NearbyPlaceAdapter.OnNearbyPlaceClickListener nearbyPlaceClickListener = new NearbyPlaceAdapter.OnNearbyPlaceClickListener() {
        @Override
        public void onClick(View v, NearbyPlaceInfo nearbyPlaceInfo, int position) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.NEARBY_LOCATION, nearbyPlaceInfo);
            Toast.makeText(MainActivity.this, nearbyPlaceInfo.getName(), Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }
    };
}
