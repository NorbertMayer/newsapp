package exam.android.norberthelmuth.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import exam.android.norberthelmuth.newsapp.Adapter.NewsAdapter;
import exam.android.norberthelmuth.newsapp.Common.Common;
import exam.android.norberthelmuth.newsapp.Common.IOpenNewsMap;
import exam.android.norberthelmuth.newsapp.Model.Article;
import exam.android.norberthelmuth.newsapp.Model.NewsResult;
import exam.android.norberthelmuth.newsapp.Retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements NewsAdapter.OnItemClickListener {

    static NewsFragment instance;
    private RecyclerView recyclerView;
    private View rootView;
    private Context context;

    List<Article> articleList = new ArrayList<>();
    Article articleItem;
    MainActivity mainActivity;
    MenuItem item;

    public static NewsFragment getInstance(String country, String category) {

            NewsFragment instance = new NewsFragment();
            Bundle args = new Bundle();
            args.putString("country", country);
            args.putString("category", category);
            instance.setArguments(args);


        return instance;
    }

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recycler_news_view_fragment, container, false);

        Bundle args = this.getArguments();
        String country = args.getString("country");
        String category = args.getString("category");

        getData(country, category);

        return view;
    }

    public void getData(String country, String category) {
        final IOpenNewsMap iOpenNewsMap = RetrofitClient.getClient().create(IOpenNewsMap.class);

        Call<NewsResult> call = iOpenNewsMap.getNewsBySource(country, category, Common.APP_ID);

        call.enqueue(new Callback<NewsResult>() {

            @Override
            public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
                    if  (response.body().getStatus().equals("ok")) {
                        NewsFragment.this.articleList = response.body().getArticles();
                        if (NewsFragment.this.articleList.size() > 0) {
                            final NewsAdapter newsAdapter = new NewsAdapter(NewsFragment.this.articleList, context);
                            recyclerView = getView().findViewById(R.id.recycler_view);

                            LinearLayoutManager mLayout = new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(mLayout);
                            mLayout.setOrientation(LinearLayoutManager.VERTICAL);
                            mLayout.findViewByPosition(getId());

                            recyclerView.setAdapter(newsAdapter);
                            newsAdapter.setOnItemClickListener(NewsFragment.this);
                        }
                    }
            }

            @Override
            public void onFailure(Call<NewsResult> call, Throwable t) {
                Log.e("out", t.toString());
            }
        });
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getActivity(), WebActivity.class);
        Article clickedItem = articleList.get(position);
        intent.putExtra("article_url", clickedItem.getUrl());

        startActivity(intent);
    }
}
