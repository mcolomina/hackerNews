package com.mcolomina.hackernews.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mcolomina.hackernews.R;
import com.mcolomina.hackernews.net.Story;
import com.mcolomina.hackernews.util.DateConverter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mcolomina.hackernews.home.HomeListAdapter.TYPE_LOAD;

class StoryHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.numComments)
    TextView numComments;
    @BindView(R.id.by)
    TextView by;

    private Story story;
    private Callback callback;
    private View view;


    @OnClick
    void onItemClick() {
        story.setSelected(SelectedType.SELECTED);
        callback.listItemClicked(story.getUrl());
    }

    public StoryHolder(View itemView, StoryHolder.Callback callback) {
        super(itemView);
        this.view = itemView;
        this.callback = callback;
        ButterKnife.bind(this, itemView);
    }

    void bindData(Story story) {
        if (story != null && !(story.getTypeItem() == TYPE_LOAD)) {
            view.setSelected(false);
            this.story = story;
            title.setText(this.story.getTitle());
            time.setText(DateConverter.unixToString(this.story.getTime())+ " |");
            score.setText(this.story.getScore() + " points " + " |");
            numComments.setText((this.story.getKids() != null ? String.valueOf(this.story.getKids().size()) : "0") + " comments");
            by.setText(this.story.getBy() != null ? "by " + this.story.getBy() + " |" : "sdc");
            if (story.getSelected().equals(SelectedType.SELECTED)) view.setSelected(true);
        }

    }

    public interface Callback {
        void listItemClicked(String url);
    }
}
