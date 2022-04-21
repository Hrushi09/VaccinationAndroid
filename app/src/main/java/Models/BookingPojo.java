package com.vaccinationapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingPojo implements Parcelable {


    public BookingPojo(String firstname, String lastname, String age, String gender, String bookingdate, String bookingtime, String name, String address, String phone, String status, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.bookingdate = bookingdate;
        this.bookingtime = bookingtime;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.email = email;
    }

    protected BookingPojo(Parcel in) {
        firstname = in.readString();
        lastname = in.readString();
        age = in.readString();
        gender = in.readString();
        bookingdate = in.readString();
        bookingtime = in.readString();
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        status = in.readString();
        email = in.readString();
        key = in.readString();
    }

    public static final Creator<BookingPojo> CREATOR = new Creator<BookingPojo>() {
        @Override
        public BookingPojo createFromParcel(Parcel in) {
            return new BookingPojo(in);
        }

        @Override
        public BookingPojo[] newArray(int size) {
            return new BookingPojo[size];
        }
    };

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getBookingtime() {
        return bookingtime;
    }

    public void setBookingtime(String bookingtime) {
        this.bookingtime = bookingtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String firstname;
    private String lastname;
    private String age;
    private String gender;
    private String bookingdate;
    private String bookingtime;
    private String name;
    private String address;
    private String phone;
    private String status;
    private String email;
    private String key;

    public BookingPojo()
    {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstname);
        parcel.writeString(lastname);
        parcel.writeString(age);
        parcel.writeString(gender);
        parcel.writeString(bookingdate);
        parcel.writeString(bookingtime);
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(phone);
        parcel.writeString(status);
        parcel.writeString(email);
        parcel.writeString(key);
    }
}
