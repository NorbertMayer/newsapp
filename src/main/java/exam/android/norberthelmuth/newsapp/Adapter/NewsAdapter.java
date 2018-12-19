package exam.android.norberthelmuth.newsapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import exam.android.norberthelmuth.newsapp.Model.Article;
import exam.android.norberthelmuth.newsapp.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<Article> articleArrayList;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    // in the constructor we need to add Context as second argument
    public NewsAdapter(List<Article> articleArrayList, Context context) {
        this.articleArrayList = articleArrayList;
        this.context = context;
    }
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        return new NewsAdapter.ViewHolder(view, context, articleArrayList);
    }
    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);
        if (viewHolder.thumbnail != null) {
            Picasso.get().load(articleModel.getUrlToImage()).fit().centerInside().into(viewHolder.thumbnail);
        }
        if(!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.excerpt_title.setText(articleModel.getTitle());
        }
        if(!TextUtils.isEmpty(articleModel.getDescription())) {
            viewHolder.excerpt_description.setText(articleModel.getDescription());
        }
        if(!TextUtils.isEmpty(articleModel.getAuthor())) {
            viewHolder.excerpt_author.setText("Author: " + articleModel.getAuthor());
        }
    }

    @Override
    public int getItemCount() {
        return this.articleArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private TextView excerpt_title;
        private TextView excerpt_description;
        private TextView excerpt_author;
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