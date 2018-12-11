package exam.android.norberthelmuth.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

import exam.android.norberthelmuth.newsapp.Adapter.BBCNewsAdapter;
import exam.android.norberthelmuth.newsapp.Common.Common;
import exam.android.norberthelmuth.newsapp.Common.IOpenNewsMap;
import exam.android.norberthelmuth.newsapp.Model.Article;
import exam.android.norberthelmuth.newsapp.Model.NewsResult;
import exam.android.norberthelmuth.newsapp.Retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BBCNewsFragment extends Fragment implements BBCNewsAdapter.OnItemClickListener {

    static BBCNewsFragment instance;
    private RecyclerView recyclerView;
    private View rootView;
    private Context context;
    List<Article> articleList = new ArrayList<>();
    Article articleItem;

    public static BBCNewsFragment getInstance() {
        if (instance == null)
            instance = new BBCNewsFragment();
        return instance;
    }

    public BBCNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recycler_news_view_fragment, container, false);

        getData();

        return view;
    }

    public void getData() {
        final IOpenNewsMap iOpenNewsMap = RetrofitClient.getClient().create(IOpenNewsMap.class);
        Call<NewsResult> call = iOpenNewsMap.getNewsBySource("bbc-sport", Common.APP_ID);

        call.enqueue(new Callback<NewsResult>() {

            @Override
            public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
                    if  (response.body().getStatus().equals("ok")) {
                        BBCNewsFragment.this.articleList = response.body().getArticles();
                        if (BBCNewsFragment.this.articleList.size() > 0) {
                            final BBCNewsAdapter bbcNewsAdapter = new BBCNewsAdapter(BBCNewsFragment.this.articleList, context);
                            recyclerView = getView().findViewById(R.id.recycler_view);

                            LinearLayoutManager mLayout = new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(mLayout);
                            mLayout.setOrientation(LinearLayoutManager.VERTICAL);
                            mLayout.findViewByPosition(getId());

                            recyclerView.setAdapter(bbcNewsAdapter);
                            bbcNewsAdapter.setOnItemClickListener(BBCNewsFragment.this);
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
