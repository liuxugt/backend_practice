package com.xu.database;

import com.xu.struct.Project;
import com.xu.struct.Session;
import com.xu.struct.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Mapper {

    int createUser(@Param("firstname") String firstName, @Param("lastname") String lastname, @Param("email") String email);
    void updateUser(@Param("id") int uid, @Param("firstname") String firstname, @Param("lastname") String lastname);
    void deleteUser(@Param("id") int uid);
    User getUser(@Param("id") int uid);
    List<User> getAllUsers();
    List<String> getAllEmails();
    User getUserByEmail(@Param("email") String email);

    int createProject(@Param("uid") int uid, @Param("name") String projectName);
    void updateProject(@Param("uid") int uid, @Param("pid") int pid, @Param("name") String projectName);
    void deleteProject(@Param("uid") int uid, @Param("pid") int pid);
    Project getProject(@Param("uid") int uid, @Param("pid") int pid);
    Project getProjectByName(@Param("uid") int uid, @Param("name") String name);
    List<Project> getAllProjects(@Param("uid") int uid );
    List<String> getAllProjectName(@Param("uid") int uid);

    int createSession(@Param("uid") int uid, @Param("pid") int pid, @Param("start_time")String startTime, @Param("end_time") String endTime, @Param("counter") int counter);
    void updateSession(@Param("uid") int uid, @Param("pid") int pid, @Param("sid") int sid, @Param("start_time")String startTime, @Param("end_time") String endTime, @Param("counter") int counter);
    Session getSession(@Param("uid") int uid, @Param("pid") int pid, @Param("sid") int sid);
    List<Session> getAllSessions(@Param("uid") int uid, @Param("pid") int pid);


    void deleteAllUsers();
}
