package com.mcolomina.hackernews.net;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mcolomina.hackernews.home.SelectedType;

import java.util.List;

public class Story {

    private int typeItem;
    private SelectedType selected;

    @SerializedName("by")
    @Expose
    private String by;

    @SerializedName("descendants")
    @Expose
    private Integer descendants;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("kids")
    @Expose
    private List<Integer> kids = null;

    @SerializedName("score")
    @Expose
    private Integer score;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("url")
    @Expose
    private String url;


    public Story(int typeItem) {
        this.typeItem = typeItem;
    }

    public Story(int typeItem, Story story) {
        this.selected = SelectedType.NOT_SELECTED;
        this.typeItem = typeItem;
        this.by = story.getBy();
        this.descendants = story.getDescendants();
        this.id = story.getId();
        this.kids = story.getKids();
        this.score = story.getScore();
        this.title = story.getTitle();
        this.type = story.getType();
        this.url = story.getUrl();
        this.time = story.getTime();
    }


    public SelectedType getSelected() {
        return selected;
    }

    public void setSelected(SelectedType selected) {
        this.selected = selected;
    }

    public int getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(int typeItem) {
        this.typeItem = typeItem;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Integer getDescendants() {
        return descendants;
    }

    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}


