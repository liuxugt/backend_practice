package com.xu.struct;

public class Session {
    String start_time;
    String end_time;
    int counter;
    int uid;
    int pid;

    public Session(){};

    public Session(String start_time, String end_time, int counter, int uid, int pid) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.counter = counter;
        this.uid = uid;
        this.pid = pid;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setUid(int uid) { this.uid = uid; }

    public void setPid(int pid) {this.pid = pid;}

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public int getCounter() {
        return counter;
    }

    public int getUid(){return uid;}
    public int getPid(){ return pid; }
}
