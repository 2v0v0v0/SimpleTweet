package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Media {
    Long id;
    String type;
    String mediaUrl;

    // empty constructor needed by the Parceler library
    public Media() { }

    public static Media fromJSON (JSONObject jsonObject) throws JSONException {
        Media media = new Media();
        media.type = jsonObject.getString("type");
        media.mediaUrl = jsonObject.getString("media_url_https");
        return media;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                '}';
    }

}
