
package com.example.owner.nearplace.mvp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Northeast {

    @Expose
    private Double lat;
    @Expose
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public static class Builder {

        private Double lat;
        private Double lng;

        public Northeast.Builder withLat(Double lat) {
            this.lat = lat;
            return this;
        }

        public Northeast.Builder withLng(Double lng) {
            this.lng = lng;
            return this;
        }

        public Northeast build() {
            Northeast northeast = new Northeast();
            northeast.lat = lat;
            northeast.lng = lng;
            return northeast;
        }

    }

}
