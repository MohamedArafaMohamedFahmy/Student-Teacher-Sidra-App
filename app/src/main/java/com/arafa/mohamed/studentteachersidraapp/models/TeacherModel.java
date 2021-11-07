package com.arafa.mohamed.studentteachersidraapp.models;

public class TeacherModel {

    private String codeTeacher, nameTeacher, phoneNumber, dateEnrollment ;

    public TeacherModel() {
    }

    public TeacherModel(String codeTeacher, String nameTeacher, String phoneNumber, String dateEnrollment) {
        this.codeTeacher = codeTeacher;
        this.nameTeacher = nameTeacher;
        this.phoneNumber = phoneNumber;
        this.dateEnrollment = dateEnrollment;
    }

    public String getCodeTeacher() {
        return codeTeacher;
    }

    public void setCodeTeacher(String codeTeacher) {
        this.codeTeacher = codeTeacher;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateEnrollment() {
        return dateEnrollment;
    }

    public void setDateEnrollment(String dateEnrollment) {
        this.dateEnrollment = dateEnrollment;
    }
}
