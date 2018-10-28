package com.example.owner.nearplace.modules.detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.owner.nearplace.R;
import com.example.owner.nearplace.Utility;
import com.example.owner.nearplace.base.BaseActivity;
import com.example.owner.nearplace.mvp.model.NearbyPlaceInfo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.Unbinder;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.place_name)
    TextView placeName;
    @BindView(R.id.place_address)
    TextView placeAddress;
    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.button)
    Button button;

    public static final String NEARBY_LOCATION = "nearby_location";

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        final NearbyPlaceInfo nearbyPlaceInfo = (NearbyPlaceInfo) intent.getSerializableExtra(DetailActivity.NEARBY_LOCATION);
        setTitle("Place Detail");

        placeName.setText(nearbyPlaceInfo.getName());
        placeAddress.setText(nearbyPlaceInfo.getVicinity());

        Picasso.get()
                .load(Utility.buildPlacePhotoUrl(nearbyPlaceInfo.getPhoto()))
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(photo);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String lat = String.valueOf(nearbyPlaceInfo.getLatitude());
                String log = String.valueOf(nearbyPlaceInfo.getLongitude());
                // Do something in response to button click
                Uri gmmIntentUri = Uri.parse("geo:"+lat+","+log);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

    }

}
