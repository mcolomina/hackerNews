package com.mcolomina.hackernews.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mcolomina.hackernews.R;
import com.mcolomina.hackernews.net.Story;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_NEWS = 0;
    public static final int TYPE_LOAD = 1;

    private List<Story> items;
    private LayoutInflater inflater;
    private StoryHolder.Callback callback;

    public HomeListAdapter(Context context, List<Story> stories, StoryHolder.Callback callback) {
        items = stories;
        inflater = LayoutInflater.from(context);
        this.callback = callback;
    }

    public List<Story> getItems() {
        return items;
    }


    @Override
    public int getItemViewType(int position) {
        return items.get(position).getTypeItem();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_NEWS) {
            viewHolder = new StoryHolder(inflater.inflate(R.layout.list_item, parent, false),callback);
        } else if (viewType == TYPE_LOAD) {
            viewHolder = new LoadHolder(inflater.inflate(R.layout.list_item_load, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StoryHolder) {
            Story story = getItems().get(position);
            ((StoryHolder) holder).bindData(story);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<Story> dataList, boolean isRefreshed) {
        if (!isRefreshed) {
            removeLoadingItem();
        } else {
            items.clear();
        }
        if (!dataList.isEmpty()) {
            items.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    public void removeLoadingItem() {
        int lastElementIndex = items.size() - 1;
        if (items.get(lastElementIndex).getTypeItem() == TYPE_LOAD) {
            items.remove(lastElementIndex);
        }
        notifyDataSetChanged();
    }

    public void addLoadingItem() {
        items.add(new Story(TYPE_LOAD));
        notifyItemRangeInserted(0, items.size());
        notifyDataSetChanged();
    }

}

