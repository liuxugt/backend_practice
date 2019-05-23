package com.xu.struct;

public class Project {
    String name;
    int user_id;
    int id;

    public Project(){
        name = "";
        user_id = 0;
        id = 0;
    }

    public Project(String name, int uid){
        this.name = name;
        this.user_id = uid;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUid(int uid){
        this.user_id = uid;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getUid() {
        return user_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
