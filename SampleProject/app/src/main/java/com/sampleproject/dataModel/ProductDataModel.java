package com.sampleproject.dataModel;

/**
 * Created by apple on 17/04/18.
 */

public class ProductDataModel {

    String productName;
    String details;
    String productType;

    public String getDetails() {
        return details;
    }

    public String getProductName() {
        return productName;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
