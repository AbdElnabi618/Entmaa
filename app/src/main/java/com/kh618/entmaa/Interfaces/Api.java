package com.kh618.entmaa.Interfaces;

import com.google.gson.JsonObject;
import com.kh618.entmaa.MyClasses.BannersItem;
import com.kh618.entmaa.MyClasses.ListItem;
import com.kh618.entmaa.MyClasses.QuizeItem;
import com.kh618.entmaa.MyClasses.RepliedItem;
import com.kh618.entmaa.MyClasses.ShopDeitals;
import com.kh618.entmaa.MyClasses.RestaurantListItem;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {
    String BaseUrlLink ="https://e3gaz.net/entmaa/public/api/v1/";

    @GET("banners/ar")
    Call<BannersItem> getBannersAr();
    @GET("banners/en")
    Call<BannersItem> getBannersEn();

    @GET("categories/ar")
    Call<ListItem> getCategoriesAr();
    @GET("categories/en")
    Call<ListItem> getCategoriesEn();

    @GET
    Call<RestaurantListItem> getShopsData(@Url String url);

    @GET
    Call<ShopDeitals> getShopData(@Url String url);
    @FormUrlEncoded
    @POST("rating/ar")
    Call<JsonObject> postRateAr(@Field("rating") String rate,
                                @Field("user_id") int userId,
                                @Field("shop_id")int shopId,
                                @Field("comment")String comment);
    @FormUrlEncoded
    @POST("rating/en")
    Call<JsonObject> postRateEn(@Field("rating") String rate,
                          @Field("user_id") int userId,
                          @Field("shop_id")int shopId,
                          @Field("comment")String comment);
    @FormUrlEncoded
    @POST("post/en")
    Call<JsonObject> postQuizeEn(@Field("user_id") int userId,
                                @Field("post")String comment);

    @FormUrlEncoded
    @POST("answer/ar")
    Call<JsonObject> postAnswerAr(@Field("user_id") int userId,
                                  @Field("post_id") int post_id,
                                 @Field("answer")String comment);

    @FormUrlEncoded
    @POST("answer/en")
    Call<JsonObject> postAnswerEn(@Field("user_id") int userId,
                                  @Field("post_id") int post_id,
                                 @Field("answer")String comment);

    @FormUrlEncoded
    @POST("post/ar")
    Call<JsonObject> postQuizeAr(@Field("user_id") int userId,
                                 @Field("post")String comment);

    @GET("posts/ar")
    Call<QuizeItem> getPostsAr();

    @GET("posts/en")
    Call<QuizeItem> getPostsEn();

    @GET
    Call<RepliedItem> getAnswers(@Url String url);

}

