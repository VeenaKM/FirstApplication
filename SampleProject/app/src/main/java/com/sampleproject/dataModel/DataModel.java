package com.sampleproject.dataModel;

import java.util.ArrayList;

/**
 * Created by apple on 17/04/18.
 */

public class DataModel {

    String title;
    int number;
    String date;
    ArrayList<ProductDataModel> product=new ArrayList<>();


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<ProductDataModel> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<ProductDataModel> product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
