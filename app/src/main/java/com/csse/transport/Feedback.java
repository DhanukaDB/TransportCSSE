package com.csse.transport;

public class Feedback {

    String email;
    String feedback;

    public Feedback(String email, String feedback) {
        this.email = email;
        this.feedback = feedback;
    }

    public Feedback() {
    }

    public String getEmail() {
        return email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
