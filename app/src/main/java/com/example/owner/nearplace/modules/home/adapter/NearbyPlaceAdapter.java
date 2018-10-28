package com.example.owner.nearplace.modules.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.owner.nearplace.R;
import com.example.owner.nearplace.Utility;
import com.example.owner.nearplace.modules.home.MainActivity;
import com.example.owner.nearplace.mvp.model.NearbyPlaceInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NearbyPlaceAdapter extends RecyclerView.Adapter<NearbyPlaceAdapter.Holder> {


    private LayoutInflater mLayoutInflater;
    private List<NearbyPlaceInfo> nearbyPlaceInfos ;

    public NearbyPlaceAdapter(LayoutInflater inflater, ArrayList<NearbyPlaceInfo> nearbyPlaceInfos) {
        mLayoutInflater = inflater;
        this.nearbyPlaceInfos = nearbyPlaceInfos;

    }
    public void addNearbyPlaces(List<NearbyPlaceInfo> nearbyPlace) {
        nearbyPlaceInfos.addAll(nearbyPlace);
        notifyDataSetChanged();
    }

    public void clearNearbyPlace() {
        nearbyPlaceInfos.clear();
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(nearbyPlaceInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return nearbyPlaceInfos.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.icon)
        protected ImageView placeIcon;
        @BindView(R.id.place_name)
        protected TextView placeName;


        private Context mContext;
        private NearbyPlaceInfo nearbyPlaceInfo;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(NearbyPlaceInfo nearbyPlaceInfo) {
            if(nearbyPlaceInfo != null) {
                this.nearbyPlaceInfo = nearbyPlaceInfo;
                Picasso.get()
                        .load(nearbyPlaceInfo.getIcon())
                        .placeholder(R.drawable.ics_placeholder)
                        .error(R.drawable.ics_error)
                        .into(placeIcon);
                placeName.setText(nearbyPlaceInfo.getName());
            }

        }

        @Override
        public void onClick(View v) {
            if (onNearbyPlaceClickListener != null) {
                onNearbyPlaceClickListener.onClick(placeIcon, nearbyPlaceInfo, getAdapterPosition());
            }
        }
    }

    public void setOnNearbyPlaceClickListener(OnNearbyPlaceClickListener listener) {
        onNearbyPlaceClickListener = listener;
    }

    private OnNearbyPlaceClickListener onNearbyPlaceClickListener;

    public interface OnNearbyPlaceClickListener {

        void onClick(View v, NearbyPlaceInfo nearbyPlaceInfo, int position);
    }
}