
package com.example.owner.nearplace.mvp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Geometry {

    @Expose
    private Location location;
    @Expose
    private Viewport viewport;

    public Location getLocation() {
        return location;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public static class Builder {

        private Location location;
        private Viewport viewport;

        public Geometry.Builder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public Geometry.Builder withViewport(Viewport viewport) {
            this.viewport = viewport;
            return this;
        }

        public Geometry build() {
            Geometry geometry = new Geometry();
            geometry.location = location;
            geometry.viewport = viewport;
            return geometry;
        }

    }

}
