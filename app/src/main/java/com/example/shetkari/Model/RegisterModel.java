package com.example.shetkari.Model;

public class RegisterModel {
    String customerName, customerMobileNumber ,customerMobileNumberTwo ,customerEmail ,state, country, addresss ,pincode, city,getUserImage;

    public RegisterModel() {
    }

    public RegisterModel(String customerName, String customerMobileNumber, String customerMobileNumberTwo, String customerEmail, String state, String country, String addresss, String pincode, String city, String getUserImage) {
        this.customerName = customerName;
        this.customerMobileNumber = customerMobileNumber;
        this.customerMobileNumberTwo = customerMobileNumberTwo;
        this.customerEmail = customerEmail;
        this.state = state;
        this.country = country;
        this.addresss = addresss;
        this.pincode = pincode;
        this.city = city;
        this.getUserImage = getUserImage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getCustomerMobileNumberTwo() {
        return customerMobileNumberTwo;
    }

    public void setCustomerMobileNumberTwo(String customerMobileNumberTwo) {
        this.customerMobileNumberTwo = customerMobileNumberTwo;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGetUserImage() {
        return getUserImage;
    }

    public void setGetUserImage(String getUserImage) {
        this.getUserImage = getUserImage;
    }
}
