package com.example.basicfunctions.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MetaData {
    String id;
    String title;
    String author;
    String publishTime;
    int type;
    String cover;
    String[] covers;

    @JsonGetter("id")
    public String getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonGetter("title")
    public String getTitle() {
        return title;
    }

    @JsonSetter("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("author")
    public String getAuthor() {
        return author;
    }

    @JsonSetter("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonGetter("publishTime")
    public String getPublishTime() {
        return publishTime;
    }

    @JsonSetter("publishTime")
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @JsonGetter("type")
    public int getType() {
        return type;
    }

    @JsonSetter("type")
    public void setType(int type) {
        this.type = type;
    }

    @JsonGetter("cover")
    public String getCover() {
        return cover;
    }

    @JsonSetter("cover")
    public void setCover(String cover) {
        this.cover = cover;
    }

    @JsonGetter("covers")
    public String[] getCovers() {
        return covers;
    }

    @JsonSetter("covers")
    public void setCovers(String[] covers) {
        this.covers = covers;
    }
}
