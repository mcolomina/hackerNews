package com.mcolomina.hackernews.home;

public enum SelectedType {
    NOT_SELECTED(0),
    SELECTED(1);

    int type;

    SelectedType(int type) {
        this.type = type;
    }
}
