package com.arafa.mohamed.studentteachersidraapp.models;

public class RatingModel {

    private String review, preservation, audience, absence,total, notes;

    public RatingModel() {
    }

    public RatingModel(String review, String preservation, String audience, String absence, String total, String notes) {
        this.review = review;
        this.preservation = preservation;
        this.audience = audience;
        this.absence = absence;
        this.total = total;
        this.notes = notes;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPreservation() {
        return preservation;
    }

    public void setPreservation(String preservation) {
        this.preservation = preservation;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getAbsence() {
        return absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
