package com.arm07.android.eshopkart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rashmi on 11/27/2017.
 */

public class User implements Parcelable {

    private String userName;
    private String userEmail;
    private String userPhone;
    private String userId;
    @SerializedName("appApiKey ")
    private String appApiKey;

    public User(String userName, String userEmail, String userPhone, String userId, String appApiKey) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userId = userId;
        this.appApiKey = appApiKey;
    }

    public static final Creator<User> CREATOR=new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(Parcel source) {
        userName=source.readString();
        userEmail=source.readString();
        userPhone=source.readString();
        userId=source.readString();
        appApiKey=source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userEmail);
        dest.writeString(userPhone);
        dest.writeString(userId);
        dest.writeString(appApiKey);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppApiKey() {
        return appApiKey;
    }

    public void setAppApiKey(String appApiKey) {
        this.appApiKey = appApiKey;
    }
}
