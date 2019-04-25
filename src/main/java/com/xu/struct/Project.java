package com.xu.struct;

public class Project {
    String name;
    int num_session;
    int uid;
    int id;

    public project(){
        name = "";
        num_session = 0;
        uid = 0;
        id = 0;
    }

    public project(String name, int uid){
        this.name = name;
        this.uid = uid;
        num_session = 0;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setNum_session(int num_session){
        this.num_session = num_session;
    }

    public void setUid(int uid){
        this.uid = uid;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getNum_session() {
        return num_session;
    }

    public int getUid() {
        return uid;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
