package com.seanweng.drama.dataBean;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 戲劇table
 */
@Entity(tableName = "drama_table")
public class Drama implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "dramaId")
    @SerializedName("drama_id")
    private int dramaId;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    @ColumnInfo(name = "totalViews")
    @SerializedName("total_views")
    private int totalViews;
    @ColumnInfo(name = "createdAt")
    @SerializedName("created_at")
    private String createdAt;
    @ColumnInfo(name = "thumb")
    @SerializedName("thumb")
    private String thumb;
    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    private float rating;

    public int getDramaId() {
        return dramaId;
    }

    public void setDramaId(int dramaId) {
        this.dramaId = dramaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dramaId);
        dest.writeString(this.name);
        dest.writeInt(this.totalViews);
        dest.writeString(this.createdAt);
        dest.writeString(this.thumb);
        dest.writeFloat(this.rating);
    }

    public Drama() {
    }

    protected Drama(Parcel in) {
        this.dramaId = in.readInt();
        this.name = in.readString();
        this.totalViews = in.readInt();
        this.createdAt = in.readString();
        this.thumb = in.readString();
        this.rating = in.readFloat();
    }

    public static final Parcelable.Creator<Drama> CREATOR = new Parcelable.Creator<Drama>() {
        @Override
        public Drama createFromParcel(Parcel source) {
            return new Drama(source);
        }

        @Override
        public Drama[] newArray(int size) {
            return new Drama[size];
        }
    };

    @Override
    public String toString() {
        return "Drama{" +
                "dramaId=" + dramaId +
                ", name='" + name + '\'' +
                ", totalViews=" + totalViews +
                ", createdAt='" + createdAt + '\'' +
                ", thumb='" + thumb + '\'' +
                ", rating=" + rating +
                '}';
    }
}