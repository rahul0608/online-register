package com.example.rahulverma.mobilebasedattendencesystem;

/**
 * Created by Rahulverma on 09/09/17.
 */

public class studentdetails {
    String enroll_no;
    String name;
    boolean selected;

    public studentdetails(String enroll_no, String name,Boolean selected)
    {
        this.enroll_no=enroll_no;
        this.name=name;
        this.selected=selected;
        ;


    }


    public  String getName() {
        return this.name;
    }

    public boolean isselected() {
        return false;
    }
    public void setselected(boolean selected) {
        this.selected = selected;
    }



    public String getEnroll_no() {
        return this.enroll_no;
    }
}
