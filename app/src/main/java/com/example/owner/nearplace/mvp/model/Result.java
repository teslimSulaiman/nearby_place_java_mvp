
package com.example.owner.nearplace.mvp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Result {

    @Expose
    private Geometry geometry;
    @Expose
    private String icon;
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private List<Photo> photos;
    @SerializedName("place_id")
    private String placeId;
    @Expose
    private String reference;
    @Expose
    private String scope;
    @Expose
    private List<String> types;
    @Expose
    private String vicinity;

    public Geometry getGeometry() {
        return geometry;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getReference() {
        return reference;
    }

    public String getScope() {
        return scope;
    }

    public List<String> getTypes() {
        return types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public static class Builder {

        private Geometry geometry;
        private String icon;
        private String id;
        private String name;
        private List<Photo> photos;
        private String placeId;
        private String reference;
        private String scope;
        private List<String> types;
        private String vicinity;

        public Result.Builder withGeometry(Geometry geometry) {
            this.geometry = geometry;
            return this;
        }

        public Result.Builder withIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Result.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Result.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Result.Builder withPhotos(List<Photo> photos) {
            this.photos = photos;
            return this;
        }

        public Result.Builder withPlaceId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public Result.Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Result.Builder withScope(String scope) {
            this.scope = scope;
            return this;
        }

        public Result.Builder withTypes(List<String> types) {
            this.types = types;
            return this;
        }

        public Result.Builder withVicinity(String vicinity) {
            this.vicinity = vicinity;
            return this;
        }

        public Result build() {
            Result result = new Result();
            result.geometry = geometry;
            result.icon = icon;
            result.id = id;
            result.name = name;
            result.photos = photos;
            result.placeId = placeId;
            result.reference = reference;
            result.scope = scope;
            result.types = types;
            result.vicinity = vicinity;
            return result;
        }

    }

}
