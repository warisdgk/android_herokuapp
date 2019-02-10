package com.apps.muhammadwaris.androidmvpherokuapp.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apps.muhammadwaris.androidmvpherokuapp.data.FullScreenProgress;
import com.apps.muhammadwaris.androidmvpherokuapp.data.NoInternet;
import com.apps.muhammadwaris.androidmvpherokuapp.data.Progress;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.apps.muhammadwaris.androidmvpherokuapp.R;
import com.apps.muhammadwaris.androidmvpherokuapp.data.NoItemFound;

import java.util.List;


public class BaseAdapter extends RecyclerView.Adapter {

    private List list;
    private Context context;
    private OnRetryConnectionListener onRetryConnectionListener;

    public interface OnRetryConnectionListener {
        void onRetryConnection();
    }

    public BaseAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
        try {
            this.onRetryConnectionListener = (OnRetryConnectionListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof NoConnectionViewHolder) {
                NoConnectionViewHolder noConnectionViewHolder = (NoConnectionViewHolder) holder;
                noConnectionViewHolder.imageViewReload.setOnClickListener(v -> {
                    if (onRetryConnectionListener != null) {
                        noConnectionViewHolder.progressBar.setVisibility(View.VISIBLE);
                        noConnectionViewHolder.imageViewReload.setVisibility(View.GONE);

                        onRetryConnectionListener.onRetryConnection();
                    }
                });
                if (noConnectionViewHolder.progressBar.getVisibility() == View.VISIBLE) {
                    noConnectionViewHolder.progressBar.setVisibility(View.GONE);
                    noConnectionViewHolder.imageViewReload.setVisibility(View.VISIBLE);
                }
            } else if (holder instanceof NoDataFoundViewHolder) {
                NoDataFoundViewHolder noDataFoundViewHolder = (NoDataFoundViewHolder) holder;
                NoItemFound noItemFound = (NoItemFound) list.get(position);
                if (noItemFound.getEmptyListIcon() != 0) {
                    noDataFoundViewHolder.imageViewEmptyIcon.setImageResource(noItemFound.getEmptyListIcon());
                }
                noDataFoundViewHolder.textViewEmptyMessage.setText(noItemFound.getEmptyMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class NoDataFoundViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewEmptyIcon;
        private TextView textViewEmptyMessage;

        public NoDataFoundViewHolder(View itemView) {
            super(itemView);

            imageViewEmptyIcon = itemView.findViewById(R.id.iv_empty_icon);
            textViewEmptyMessage = itemView.findViewById(R.id.tv_empty_message);
        }
    }

    public class NoConnectionViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewReload;
        private ProgressBar progressBar;

        public NoConnectionViewHolder(View itemView) {
            super(itemView);
            imageViewReload = itemView.findViewById(R.id.iv_reload);
            progressBar = itemView.findViewById(R.id.pb);
        }
    }

    public class BannerAdViewHolder extends RecyclerView.ViewHolder {
        public AdView adView;

        public BannerAdViewHolder(View itemView) {
            super(itemView);
            // adView = itemView.findViewById(R.id.adview);
        }
    }

    public void showBanner(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void addItems(List<Object> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addNoResultItem(int emptyIcon, String emptyMessage) {
        list.clear();
        list.add(new NoItemFound(emptyIcon, emptyMessage));
        notifyDataSetChanged();
    }

    public void addProgress() {
        list.add(new Progress());
        notifyDataSetChanged();
    }

    public void addFullScreenProgress() {
        list.add(new FullScreenProgress());
        notifyDataSetChanged();
    }

    public void removeProgress() {
        if (list != null && list.size() > 0)
            list.remove(list.size() - 1);
        notifyDataSetChanged();
    }

    public void addNoConnectionItem() {
        list.clear();
        list.add(new NoInternet());
        notifyDataSetChanged();
    }

    public void removeAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addItem(Object item) {
        list.add(item);
        notifyItemChanged(list.indexOf(item));
    }

}
