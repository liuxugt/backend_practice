package com.xu.struct;

public class ReportSession {
    String start_time;
    String end_time;
    double workHours;

    public ReportSession(String start_time, String end_time, double workHours) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.workHours = workHours;
    }

    public ReportSession() {
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }
}
