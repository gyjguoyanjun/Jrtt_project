package com.gyj.jrtt_project.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * data:2017/4/11
 * author:郭彦君(Administrator)
 * function:
 */

@Table(name = "childInfo", onCreated = "CREATE UNIQUE INDEX realative_unique ON user(imagePath, title,author_Name)")
public class ChildInfo {
    @Column(
            name = "ID",
            isId = true,
            autoGen = true
    )
    private int id;
    @Column(name = "imagePath", property = "NOT NULL")
    private String imagePath;
    @Column(name = "title", property = "NOT NULL")
    private String title;
    @Column(name = "author_Name", property = "NOT NULL")
    private String author_Name;

    public ChildInfo(String imagePath, String title, String author_Name) {
        this.imagePath = imagePath;
        this.title = title;
        this.author_Name = author_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_Name() {
        return author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        this.author_Name = author_Name;
    }

    @Override
    public String toString() {
        return "ChildInfo{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", title='" + title + '\'' +
                ", author_Name='" + author_Name + '\'' +
                '}';
    }
}
