package com.kiu.glbajajhackathon.pojo;

/**
 * Created by bamakant on 18/3/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class GetItemList {

            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("itemName")
            @Expose
            private String itemName;
            @SerializedName("itemQuantity")
            @Expose
            private String itemQuantity;
            @SerializedName("lng")
            @Expose
            private Double lng;
            @SerializedName("lat")
            @Expose
            private Double lat;
            @SerializedName("userid")
            @Expose
            private String userid;
            @SerializedName("mobile")
            @Expose
            private Integer mobile;
            @SerializedName("username")
            @Expose
            private String username;
            @SerializedName("address")
            @Expose
            private String address;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getItemQuantity() {
                return itemQuantity;
            }

            public void setItemQuantity(String itemQuantity) {
                this.itemQuantity = itemQuantity;
            }

            public Double getLng() {
                return lng;
            }

            public void setLng(Double lng) {
                this.lng = lng;
            }

            public Double getLat() {
                return lat;
            }

            public void setLat(Double lat) {
                this.lat = lat;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public Integer getMobile() {
                return mobile;
            }

            public void setMobile(Integer mobile) {
                this.mobile = mobile;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
}
