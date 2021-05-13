package com.example.loginpage;

public class Monday {
   public String Subject;
   public String InstructorName;
   public String classType;
   public String classNo;
   public String From;
   public String to;
   public String key;

    public Monday (String subject, String instructorname, String classtype, String from, String to,String classno)
    {
        this.Subject = subject;
        this.classType =classtype;
        this.InstructorName = instructorname;
        this.From = from;
        this.classNo = classno;
        this.to = to;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public void setInstructorName(String instructorName) {
        InstructorName = instructorName;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
