package com.example.w22comp1011gctest1;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Student {

    //instance variables
    private int studentNum;
    private String firstName;
    private String lastName;
    private String telephone;
    private String address;
    private String province;
    private int avgGrade;
    private String major;


    //constructor
    public Student(int studentNum, String firstName, String lastName, String telephone, String address, String province, int avgGrade, String major) {
       setStudentNum(studentNum);
       setFirstName(firstName);
       setLastName(lastName);
       setTelephone(telephone);
       setAddress(address);
       setProvince(province);
       setAvgGrade(avgGrade);
       setMajor(major);
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        if(studentNum >= 200034000)
            this.studentNum = studentNum;
        else
            throw new IllegalArgumentException("Student number must be >= 200034000");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.length() > 1)
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("Firstname must be more than one character long");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.length() > 1)
            this.lastName = firstName;
        else
            throw new IllegalArgumentException("LastName must be more than one character long");

    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if(telephone.matches("\\(?[2-9][0-9][0-9]\\)?[-\\s.]?[2-9]\\d{2}[-\\s.]?[0-9]{4}"))
            this.telephone = telephone;
        else
            throw new IllegalArgumentException("Telephone number must be in the North American Pattern");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address.length() > 6)
            this.address = address;
        else
            throw new IllegalArgumentException("Address must be more than six character long");

    }

    public String getProvince() {
        return province;
    }

    public  static  List<String> getProvinceName(){
        List<String> provinceList = Arrays.asList("AB","BC","MB","NB","NL","NS","NT","NU","ON","PE","QC","SK","YT");
        Collections.sort(provinceList);
        return provinceList;
    }


    public void setProvince(String province) {
        List<String> validProvince = getProvinceName();
        if(validProvince.contains(province))
            this.province = province;
        else
            throw new IllegalArgumentException("Province name didn't match");
    }

    public int getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(int avgGrade) {
        if(avgGrade >= 0 && avgGrade <=100)
            this.avgGrade = avgGrade;
        else
            throw new IllegalArgumentException("Average grade must be 0-100 inclusive");
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if(major.length() > 5)
            this.major = major;
        else
            throw new IllegalArgumentException("Major must be more than five character long");

    }
}
