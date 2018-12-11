package exam.android.norberthelmuth.newsapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import exam.android.norberthelmuth.newsapp.BBCNewsFragment;
import exam.android.norberthelmuth.newsapp.Model.Article;
import exam.android.norberthelmuth.newsapp.R;

public class BBCNewsAdapter extends RecyclerView.Adapter<BBCNewsAdapter.ViewHolder> {
    private List<Article> articleArrayList;
    private Context context;
    private OnItemClickListener mListener;
    private Article articleModel;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    // in the constructor we need to add Context as second argument
    public BBCNewsAdapter(List<Article> articleArrayList, Context context) {
        this.articleArrayList = articleArrayList;
        this.context = context;
    }
    @Override
    public BBCNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        return new BBCNewsAdapter.ViewHolder(view, context, articleArrayList);
    }
    @Override
    public void onBindViewHolder(BBCNewsAdapter.ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);
        if (viewHolder.thumbnail != null) {
            Picasso.get().load(articleModel.getUrlToImage().toString()).fit().centerInside().into(viewHolder.thumbnail);
        }
        if(!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.excerpt_title.setText(articleModel.getTitle());
        }
        if(!TextUtils.isEmpty(articleModel.getDescription())) {
            viewHolder.excerpt_description.setText(articleModel.getDescription());
        }
        if(!TextUtils.isEmpty(articleModel.getUrl())) {
            viewHolder.excerpt_author.setText(articleModel.getAuthor());
        }
        if(!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.excerpt_url.setText(articleModel.getUrl());
        }
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnail;
        private TextView excerpt_title;
        private TextView excerpt_description;
        private TextView excerpt_author;
        private TextView excerpt_url;
        private Context context;
        private List<Article> articles;

        ViewHolder(View view, Context context, List<Article> articleArrayList) {
            super(view);

            this.context = context;
            this.articles = articleArrayList;
            // when the user click any of the cards will invoke OnClickListener method
            thumbnail = view.findViewById(R.id.thumbnail);
            excerpt_title = view.findViewById(R.id.excerpt_title);
            excerpt_description = view.findViewById(R.id.excerpt_description);
            excerpt_author = view.findViewById(R.id.excerpt_author);
            excerpt_url = view.findViewById(R.id.excerpt_url);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener != null) {
                        int position = getAdapterPosition();
                        mListener.onItemClick(position);
                    }
                }
            });
            }
        }
    }