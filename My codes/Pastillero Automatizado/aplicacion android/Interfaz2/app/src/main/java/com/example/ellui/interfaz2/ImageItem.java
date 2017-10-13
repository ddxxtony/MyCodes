package com.example.ellui.interfaz2;

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap image;
    private String title;
    private int id;
    private String name;


    public ImageItem(Bitmap image, String title,int id) {
        super();
        this.image = image;
        this.title = title;
        this.id=id;
    }
    public ImageItem(Bitmap image, String title,int id,String n) {
        super();
        this.image = image;
        this.title = title;
        this.id=id;
        this.name=n;
    }
    public ImageItem() {

    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
