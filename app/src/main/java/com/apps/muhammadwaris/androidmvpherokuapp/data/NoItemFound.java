package com.apps.muhammadwaris.androidmvpherokuapp.data;


public class NoItemFound {
    private int emptyListIcon;
    private String emptyMessage;

    public NoItemFound(int emptyListIcon, String emptyMessage) {
        this.emptyListIcon = emptyListIcon;
        this.emptyMessage = emptyMessage;
    }

    public int getEmptyListIcon() {
        return emptyListIcon;
    }

    public String getEmptyMessage() {
        return emptyMessage;
    }
}
