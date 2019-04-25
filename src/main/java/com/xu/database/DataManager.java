package com.xu.database;

import com.xu.struct.Project;
import com.xu.struct.Report;
import com.xu.struct.Session;
import com.xu.struct.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


public class DataManager {

    SqlSessionFactory sqlSessionFactory;
    public DataManager(DataBaseSessionFactory factory){
        sqlSessionFactory = factory.getFactory();
    }

    public User createUser(String firstName, String lastName, String email){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            mapper.createUser(firstName,lastName,email);
            return mapper.getUserByEmail(email);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public User getUser(int uid){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            return mapper.getUser(uid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public User updateUser(int uid, String firstName, String lastName){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            mapper.updateUser(uid, firstName,lastName);
            return mapper.getUser(uid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public User deleteUser(int uid){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            User user = mapper.getUser(uid);
            mapper.deleteUser(uid);
            return user;
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public List<User> getAllUsers(){


        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            return mapper.getAllUsers();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public void clearDatabase(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            mapper.deleteAllUsers();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public Project createProject(int uid, String projectName){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            mapper.createProject(uid, projectName);
            return mapper.getProjectByName(uid, projectName);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public Project updateProject(int uid, int pid, String projectName){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            mapper.updateProject(uid, pid, projectName);
            return mapper.getProject(uid, pid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public Project getProject(int uid, int pid){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            return mapper.getProject(uid, pid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public Project deleteProject(int uid, int pid){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            Project project = mapper.getProject(uid, pid);
            mapper.deleteProject(uid, pid);
            return project;
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

    }

    public List<Project> getAllProjects(int uid){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            return mapper.getAllProjects(uid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

    }

    public Session createSession(int uid, int pid, String startTime, String endTime, int counter){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            Session newSession = new Session(startTime, endTime, counter, uid, pid);
            mapper.createSession(uid, pid, startTime, endTime, counter);
            return newSession;
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

    }

    public Session updateSession(int uid, int pid, int sid, String startTime, String endTime, int counter){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            mapper.updateSession(uid, pid, sid, startTime, endTime, counter);
            return mapper.getSession(uid, pid, sid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

    }

    /*
    public Report getReport(int uid, int pid, String startTime, String endTime, Boolean if){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);

        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
    */

    public List<String> getAllUserEmails(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            return mapper.getAllEmails();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

    }

    public List<String> getALlProjectNames(int uid){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            return mapper.getAllProjectName(uid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public Session getSession(int uid, int pid, int sid){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            return mapper.getSession(uid, pid, sid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    public List<Session> getAllSessions(int uid, int pid){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            return mapper.getAllSessions(uid, pid);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
