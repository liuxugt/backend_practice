package com.xu.pomodoro;

import com.xu.struct.*;

import java.util.List;

public class pomodoroTracker {

    //API functions
    public User createUser(User user){

    }
    public User getUser(int uid){

    }
    public List<User> getAllUser(){
    }

    public User updateUser(int uid, User user){

    }

    public User deleteUser(int uid){

    }

    public User deleteAllUser(){

    }

    public Project createProject(int uid, Project project){

    }

    public Project getProject(int uid, int pid){

    }

    public List<Project> getAllProject(int uid){

    }

    public Project updateProject(int uid, int pid, Project project){

    }

    public Project deleteProject(int uid, int pid){

    }

    public Session createSession(int uid, int pid, Session session){

    }

    public Session updateSession(int uid, int pid, int sid, Session session){

    }

    public Report getReport(int uid, int pid, boolean includeCompletedPomodoros, boolean includeTotalHoursWorkedOnProject){

    }



    //helper functions used in API functions

    public boolean checkUserEmailConflict(String email){

    }

    public boolean checkProjectNameConflict(int uid, String name){

    }

    public Session getSession(int uid, int pid, int sid)

}
