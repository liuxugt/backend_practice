package com.xu.pomodoro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.xu.struct.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ApplicationController {
    @Autowired
    private pomodoroTracker ptt;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<?> getAllUsers(){
        List<User> result = ptt.getAllUser();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userID}")
    public ResponseEntity<?> getusers(@PathVariable String userID)
    {
        int id;
        try {
            id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User result = ptt.getUser(id);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ResponseEntity<?> createUser(@RequestBody User newUser)
    {
        System.out.println("test output");
        if(newUser.getEmail() == null || newUser.getEmail().isEmpty() ||
                newUser.getFirst_name() == null || newUser.getFirst_name().isEmpty() ||
                newUser.getLast_name() == null || newUser.getLast_name().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //if(!ptt.checkUserEmailConflict(newUser.getEmail())){
        //    return new ResponseEntity<>(HttpStatus.CONFLICT);
        //}

        System.out.println("test output");
        User createdUser = ptt.createUser(newUser);
        if(createdUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userID}")
    public ResponseEntity<?> updateUser(@RequestBody User newUser,
                                        @PathVariable String userID)
    {
        int id;
        try {
            id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User existed = ptt.getUser(id);
        if(existed == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(newUser.getFirst_name() == null || newUser.getFirst_name().isEmpty() ||
                newUser.getLast_name() == null || newUser.getLast_name().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User returned = ptt.updateUser(id, newUser);
        if(returned == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(returned, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable String userID)
    {
        int id;
        try {
            id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User existed = ptt.getUser(id);
        if(existed == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User deleted = ptt.deleteUser(id);
        if(deleted == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users")
    public ResponseEntity<?> deleteAllUsers()
    {
        ptt.deleteAllUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userID}/projects")
    public ResponseEntity<?> createProject(@PathVariable String userID,
                                           @RequestBody Project newProject)
    {
        int id;
        try {
            id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(ptt.checkProjectNameConflict(id, newProject.getName())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Project created = ptt.createProject(id, newProject);
        if(created == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userID}/projects")
    public ResponseEntity<?> getAllProject(@PathVariable String userID)
    {
        int id;
        try {
            id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<Project> result = ptt.getAllProject(id);
        if(result == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userID}/projects/{projectID}")
    public ResponseEntity<?> getProject(@PathVariable("userID") String userID,
                                        @PathVariable("projectID") String projID)
    {
        int uid, pid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
        } catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("here");
        Project result = ptt.getProject(uid, pid);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userID}/projects/{projectID}")
    public ResponseEntity<?> updateProject(@PathVariable("userID") String userID,
                                           @PathVariable("projectID") String projID,
                                           @RequestBody Project newProject)
    {
        int uid, pid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
        } catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println(newProject.getName());
        if(ptt.checkProjectNameConflict(uid, newProject.getName())){
            return new ResponseEntity<>("duplicate name", HttpStatus.CONFLICT);
        }

        Project exist = ptt.getProject(uid, pid);
        if(exist == null){
            System.out.println("not found");
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }

        Project updated = ptt.updateProject(uid, pid, newProject);
        if(updated == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userID}/projects/{projectID}")
    public ResponseEntity<?> deleteProject(@PathVariable("userID") String userID,
                                           @PathVariable("projectID") String projID)
    {
        int uid, pid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
        } catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Project deleted = ptt.deleteProject(uid, pid);
        if(deleted == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userID}/projects/{projectID}/sessions")
    public ResponseEntity<?> createSession(@PathVariable("userID") String userID,
                                           @PathVariable("projectID") String projID,
                                           @RequestBody Session newSession)
    {
        int uid, pid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
        } catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Session created = ptt.createSession(uid, pid, newSession);
        if(created == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userID}/projects/{projectID}/sessions/{sessionID}")
    public ResponseEntity<?> updateSession(@PathVariable("userID") String userID,
                                           @PathVariable("projectID") String projID,
                                           @PathVariable("sessionID") String sessID,
                                           @RequestBody Session newSession)
    {
        int uid, pid, sid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
            sid = Integer.parseInt(sessID);
        } catch (NumberFormatException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Session exist = ptt.getSession(uid, pid, sid);
        if(exist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Session updated = ptt.updateSession(uid, pid, sid, newSession);
        if(updated == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userID}/projects/{projectID}/report")
    public ResponseEntity<?> getReport(@PathVariable("userID") String userID,
                                       @PathVariable("projectID") String projID,
                                       @RequestParam("from") String from,
                                       @RequestParam("to") String to,
                                       @RequestParam(value="includeCompletedPomodoros", required = false, defaultValue = "false") Boolean includeCompletedPomodoros,
                                       @RequestParam(value="includeTotalHoursWorkedOnProject", required = false, defaultValue = "false") Boolean includeTotalHoursWorkedOnProject)
    {
        int uid, pid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
        } catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(from == null || to == null || from.isEmpty() || to.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            Date start = new SimpleDateFormat().parse(from);
            Date end = new SimpleDateFormat().parse(to);
            if(start.after(end)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (ParseException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Project found = ptt.getProject(uid, pid);

        if(found == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Report report = ptt.getReport(uid, pid, from, to, includeCompletedPomodoros, includeTotalHoursWorkedOnProject);

        return new ResponseEntity<>(report, HttpStatus.OK);
    }

}
