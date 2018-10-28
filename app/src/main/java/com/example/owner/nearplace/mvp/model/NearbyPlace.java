
package com.example.owner.nearplace.mvp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NearbyPlace {

    @SerializedName("html_attributions")
    private List<Object> htmlAttributions;
    @SerializedName("next_page_token")
    private String nextPageToken;
    @Expose
    private List<Result> results;
    @Expose
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public List<Result> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {

        private List<Object> htmlAttributions;
        private String nextPageToken;
        private List<Result> results;
        private String status;

        public NearbyPlace.Builder withHtmlAttributions(List<Object> htmlAttributions) {
            this.htmlAttributions = htmlAttributions;
            return this;
        }

        public NearbyPlace.Builder withNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
            return this;
        }

        public NearbyPlace.Builder withResults(List<Result> results) {
            this.results = results;
            return this;
        }

        public NearbyPlace.Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public NearbyPlace build() {
            NearbyPlace nearbyPlace = new NearbyPlace();
            nearbyPlace.htmlAttributions = htmlAttributions;
            nearbyPlace.nextPageToken = nextPageToken;
            nearbyPlace.results = results;
            nearbyPlace.status = status;
            return nearbyPlace;
        }

    }

}
