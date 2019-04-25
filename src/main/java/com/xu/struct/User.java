package com.xu.struct;

public class User {
    String first_name;
    String last_name;
    String email;
    int id = 0;

    user(){
        first_name = "";
        last_name = "";
        id = 0;
        email = "";
    }
    user(String first_name, String last_name, String email){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
    public String getFirst_name(){
        return first_name;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getEmail(){
        return email;
    }

    public int getId() {
        return id;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }
    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id){
        this.id = id;
    }
}
