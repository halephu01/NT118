package com.example.lab2_5;

import android.widget.CheckBox;

public class Dish {
    private String monAn;
    private int Thumbnail;
    private Boolean isPromotion;

    public Dish (String monan,int thumbnail, Boolean isPromotion){
        monAn = monan;
        Thumbnail = thumbnail;
        this.isPromotion=isPromotion;
    }
    public Dish (String monan,int thumbnail){
        monAn=monan;
        Thumbnail=thumbnail;
    }
    public Dish(){

    }
    public String getMonAn(){ return monAn;}
    public void setMonAn(String monan){ monAn=monan;}
    public int getThumbnail(){return Thumbnail;}
    public void setThumbnail(int thumbnail){Thumbnail=thumbnail;}
    public boolean isPromotion(){return isPromotion;}

    public void setPromotion(boolean promotion){isPromotion=promotion;}

}
