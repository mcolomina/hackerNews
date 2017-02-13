package com.mcolomina.hackernews.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

class LoadHolder extends RecyclerView.ViewHolder {
    public LoadHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
