
package com.example.owner.nearplace.mvp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Photo {

    @Expose
    private Long height;
    @SerializedName("html_attributions")
    private List<String> htmlAttributions;
    @SerializedName("photo_reference")
    private String photoReference;
    @Expose
    private Long width;

    public Long getHeight() {
        return height;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public Long getWidth() {
        return width;
    }

    public static class Builder {

        private Long height;
        private List<String> htmlAttributions;
        private String photoReference;
        private Long width;

        public Photo.Builder withHeight(Long height) {
            this.height = height;
            return this;
        }

        public Photo.Builder withHtmlAttributions(List<String> htmlAttributions) {
            this.htmlAttributions = htmlAttributions;
            return this;
        }

        public Photo.Builder withPhotoReference(String photoReference) {
            this.photoReference = photoReference;
            return this;
        }

        public Photo.Builder withWidth(Long width) {
            this.width = width;
            return this;
        }

        public Photo build() {
            Photo photo = new Photo();
            photo.height = height;
            photo.htmlAttributions = htmlAttributions;
            photo.photoReference = photoReference;
            photo.width = width;
            return photo;
        }

    }

}
