package com.ucc.portal.uccapp.models;

import java.util.Locale;

public class StudentViewModel {
    private StudentProfile mStudentProfile;
    private StudentLab mStudentLab;

    public StudentViewModel(){

    }
    public String getRegistrationNumber(){
        return mStudentProfile.getRegistrationNumber();
    }
    public String getFirstName(){
        return mStudentProfile.getFirstName().toLowerCase().toUpperCase().charAt(0) + mStudentProfile.getFirstName().substring(1).toLowerCase(Locale.ROOT);
    }
    public String getMiddleName(){
        return mStudentProfile.getMiddleName().toLowerCase().toUpperCase().charAt(0) + mStudentProfile.getFirstName().substring(1).toLowerCase(Locale.ROOT);
    }
    public String getLastName(){
        return mStudentProfile.getLastName().toLowerCase().toUpperCase().charAt(0) + mStudentProfile.getFirstName().substring(1).toLowerCase(Locale.ROOT);
    }
    public String getFullName(){
        return mStudentProfile.getFirstName() + " " + mStudentProfile.getMiddleName()+ " " + mStudentProfile.getLastName();
    }
    public String getProgramme(){
        return mStudentProfile.getProgramme();
    }
    public String getMajor(){
        return mStudentProfile.getMajor();
    }
    public String getGender(){
        return mStudentProfile.getGender();
    }
    public String getDateOfBirth(){
        return mStudentProfile.getDOB();
    }
    public String getLevel(){
        return mStudentProfile.getLevel();
    }
    public String getHall(){
        return mStudentProfile.getHall();
    }
    public String getEmail(){
        return mStudentProfile.getEmail();
    }
    public  String getRoomNo(){
        return mStudentProfile.getRoomNo();
    }
    public String getAddress(){
        return mStudentProfile.getAddress();
    }
    public String getGPSAddress(){
        return mStudentProfile.getGPSAddress();
    }
    public String getCellPhone(){
        return mStudentProfile.getCellPhone();
    }
    public String getHomePhone(){
        return mStudentProfile.getHomePhone();
    }
    public String getHomeAddress(){
        return mStudentProfile.getHomeAddress();
    }
    public String getPostalAddress(){
        return mStudentProfile.getPostalAddress();
    }
    public String getPostalTown(){
        return mStudentProfile.getPostalTown();
    }
    public String getPlaceOfBirth(){
        return mStudentProfile.getPlaceOfBirth();
    }
    public String getHomeTown(){
        return mStudentProfile.getHomeTown();
    }
    public StudentProfile getStudentProfile(){
        return mStudentProfile;
    }
    public void setStudentProfile(StudentProfile studentProfile){
        mStudentProfile = studentProfile;
    }
}
