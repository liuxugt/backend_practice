package com.xu.database;

import com.xu.struct.Project;
import com.xu.struct.Report;
import com.xu.struct.Session;
import com.xu.struct.User;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


public class DataManager {

    SqlSessionFactory sqlSessionFactory;
    public DataManager(DataBaseSessionFactory factory){
        sqlSessionFactory = factory.getFactory();
    }

    public User createUser(String firstName, String lastName, String email){

    }

    public User getUser(int uid){

    }

    public User updateUser(int uid, String firstName, String lastName){

    }

    public User deleteUser(int uid){

    }

    public List<User> getAllUsers(){

    }

    public void clearDatabase(){

    }

    public Project createProject(int uid, String projectName){

    }

    public Project updateProject(int uid, int pid, String projectName){

    }

    public Project getProject(int uid, int pid){
    }

    public Project deleteProject(int uid, int pid){

    }

    public List<Project> getAllProjects(int uid){

    }

    public Session createSession(int uid, int pid, String startTime, String endTime, int counter){

    }

    public Session updateSession(int uid, int pid, int sid, String startTime, String endTime, int counter){

    }

    public Report getReport(int uid, int pid){

    }

    public List<String> getAllUserEmails(){

    }

    public List<String> getALlProjectNames(int uid){

    }

    public Session getSession(int uid, int pid, int sid){

    }
}
