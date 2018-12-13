package exam.android.norberthelmuth.newsapp.Common;


import exam.android.norberthelmuth.newsapp.Model.NewsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenNewsMap {

    @GET("top-headlines")
    Call<NewsResult> getNewsBySource(@Query("country") String country,
                                     @Query("category") String category,
                                     @Query("apiKey") String apiKey);
}
