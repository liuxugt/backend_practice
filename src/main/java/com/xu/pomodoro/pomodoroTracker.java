package com.xu.pomodoro;

import com.xu.struct.*;
import com.xu.database.DataManager;
import com.xu.database.DataBaseSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.env.Environment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class pomodoroTracker {

    DataManager dataManager;

    public pomodoroTracker(Environment env){
        String mode = env.getProperty("mode");

        if(mode == null){
            throw new IllegalStateException("Operation mode must be specified in commandline");
        }
        else if(mode.equals("development") || mode.equals("production")){
            DataBaseSessionFactory dataBaseSessionFactory= new DataBaseSessionFactory(mode);
            SqlSessionFactory sqlSessionFactory = dataBaseSessionFactory.getFactory();
            dataManager = new DataManager(dataBaseSessionFactory);

            function_create_new_table();
        }
        else{
            throw new IllegalStateException("Operation mode must be 'development' or 'production'");
        }
    }


    //API functions
    public User createUser(User user){
        String newEmail = checkEmail(user.getEmail());
        if(newEmail == null){
            return null;
        }
        else{
            return dataManager.createUser(user.getFirst_name(), user.getLast_name(), newEmail);
        }
    }

    public User getUser(int uid){
        return dataManager.getUser(uid);

    }

    public List<User> getAllUser(){
        return dataManager.getAllUsers();
    }

    public User updateUser(int uid, User user){
        return dataManager.updateUser(uid, user.getFirst_name(), user.getLast_name());
    }

    public User deleteUser(int uid){
        User user = dataManager.getUser(uid);
        dataManager.deleteUser();
        return user;
    }

    public User deleteAllUser(){
        dataManager.clearDatabase();
    }

    public Project createProject(int uid, Project project){
        return this.dataManager.createProject(uid, project.getName());
    }

    public Project getProject(int uid, int pid){
        User user = dataManager.getUser(uid);
        if(user == null){
            return null;
        }

        return this.dataManager.getProject(uid, pid);
    }

    public List<Project> getAllProject(int uid){
        User user = dataManager.getUser(uid);
        if(user == null){
            return null;
        }
        List<Project> projects = dataManager.getAllProjects();
        if(projects == null){
            return new ArrayList<>();
        }

        return projects;
    }

    public Project updateProject(int uid, int pid, Project project){
        if(this.dataManager.getProject(uid, pid) == null){
            return null;
        }
        return this.dataManager.updateProject(uid, pid, project.getName());
    }

    public Project deleteProject(int uid, int pid){
        if(this.dataManager.getProject(uid, pid) == null){
            return null;
        }
        return this.dataManager.deleteProject(uid, pid);
    }

    public Session createSession(int uid, int pid, Session session){
        if(this.dataManager.getProject(uid, pid) == null){
            return null;
        }
        return this.dataManager.createSession(uid, pid, session);
    }

    public Session updateSession(int uid, int pid, int sid, Session session){
        return this.dataManager.updateSession(uid, pid, sid, session.getStart_time(), session.getEnd_time(), session.getCounter());
    }

    public Report getReport(int uid, int pid, String startTime, String endTime, boolean includeCompletedPomodoros, boolean includeTotalHoursWorkedOnProject){
        List<Session> sessions = this.dataManager.getAllSessions(uid, pid);
        List<ReportSession> result = new ArrayList<ReportSession>();
        double total_hours = 0;
        int completedPomodoro = 0;
        try{
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(startTime);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(endTime);

            for(Session session : sessions){
                if(session.getStart_time().isEmpty() || session.getEnd_time().isEmpty()){
                    continue;
                }
                Date start = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(session.getStart_time());
                Date end = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(session.getEnd_time());

                if((startDate.equals(start) || startDate.before(start)) && (endDate.equals(end) || endDate.after(end))){
                    double hours = (end.getTime() - start.getTime())/1000/3600;
                    total_hours += hours;
                    completedPomodoro += session.getCounter();
                    result.add(new ReportSession(session.getStart_time(), session.getEnd_time(), hours));
                }
            }
        } catch(ParseException e){
            throw new IllegalStateException("A exception in generating report");
        }

        Report report = new Report();
        report.setReport_session(result);
        if(includeCompletedPomodoros){
            report.setTotalReport(completedPomodoro);
        }
        if(includeTotalHoursWorkedOnProject){
            report.setWorkedHour(total_hours);
        }

        return report;
    }



    //helper functions used in API functions

    public boolean checkUserEmailConflict(String email){
        List<String> emails = dataManager.getAllUserEmails();
        if(emails.contains(email)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkProjectNameConflict(int uid, String name){
        List<String> names = dataManager.getALlProjectNames(uid);
        if(names.contains(name)){
            return true;
        }
        else{
            return false;
        }
    }

    public Session getSession(int uid, int pid, int sid){
        return dataManager.getSession(uid, pid, sid);
    }

    public String checkEmail(String email){
        String[] emails = email.split("@");
        if(emails.length != 2){
            return null;
        }
        String[] locals = emails[0].split("\\+");
        StringBuilder validLocal = new StringBuilder(locals[0]);
        int i = 0;
        while(i < validLocal.length()){
            if(validLocal.charAt(i) == '.'){
                validLocal.replace(i, i+1, "");
                i--;
            }
            i++;
        }
        String newEmail = validLocal + "@" + emails[1];
        emails = newEmail.split("@");
        if(emails.length != 2 || emails[0].length() == 0 || emails[1].length() == 0){
            return null;
        }
        if (!Character.isAlphabetic(emails[0].charAt(0)) && !Character.isDigit(emails[0].charAt(0))) {
            return null;
        }
        else{
            String[] domain = emails[1].split("\\.");
            if(domain.length != 2 || domain[0].length() == 0 || domain[1].length() == 0){
                return null;
            }
        }
        return newEmail;

    }

}
