package com.example.shetkari.Model;

public class CropModel {
    String CropImage, CropName, CropRate, Location, farmerName, farmerMobileNumber, farmerMobileNumberTwo, farmerEmail, clickHereFullViewInformation;

    public CropModel() {
    }

    public String getCropImage() {
        return CropImage;
    }

    public void setCropImage(String cropImage) {
        CropImage = cropImage;
    }

    public String getCropName() {
        return CropName;
    }

    public void setCropName(String cropName) {
        CropName = cropName;
    }

    public String getCropRate() {
        return CropRate;
    }

    public void setCropRate(String cropRate) {
        CropRate = cropRate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmerMobileNumber() {
        return farmerMobileNumber;
    }

    public void setFarmerMobileNumber(String farmerMobileNumber) {
        this.farmerMobileNumber = farmerMobileNumber;
    }

    public String getFarmerMobileNumberTwo() {
        return farmerMobileNumberTwo;
    }

    public void setFarmerMobileNumberTwo(String farmerMobileNumberTwo) {
        this.farmerMobileNumberTwo = farmerMobileNumberTwo;
    }

    public String getFarmerEmail() {
        return farmerEmail;
    }

    public void setFarmerEmail(String farmerEmail) {
        this.farmerEmail = farmerEmail;
    }

    public String getClickHereFullViewInformation() {
        return clickHereFullViewInformation;
    }

    public void setClickHereFullViewInformation(String clickHereFullViewInformation) {
        this.clickHereFullViewInformation = clickHereFullViewInformation;
    }
}
