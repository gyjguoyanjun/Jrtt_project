package com.gyj.jrtt_project.beans;

/**
 * data:2017/4/18
 * author:郭彦君(Administrator)
 * function:
 */
public class SQLiteTitle {
    private String id;
    private String name;
    private String uri;
    private String style;

    public SQLiteTitle(String name, String uri, String style) {
        this.name = name;
        this.uri = uri;
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
