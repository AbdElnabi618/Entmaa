package com.kh618.entmaa.Interfaces;

import com.kh618.entmaa.MyClasses.BannersItem;
import com.kh618.entmaa.MyClasses.ListItem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocalApi {
    String BaseUrlLink ="http://192.168.1.2/localApi/";

    @GET("banner.php")
    Call<BannersItem> getLocalBanners();
    @GET("banners/en")
    Call<BannersItem> getBannersEn();

    @GET("categorie.php")
    Call<ListItem> getLocalCategories();
    @GET("categories/en")
    Call<ListItem> getCategoriesEn();
}
