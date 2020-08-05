package com.example.myshuangchuang.JavaBean;

public class ImageBean {

    private String name;
    private String imageUrl;
    /*图像类 包含图像数据*/
    public ImageBean(){

    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}


