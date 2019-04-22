package com.example.demo;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.struct.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
public class ApplicationController {
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<?> getAllUsers(){
        List<user> result = function_getAllUsers();
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


        user result = function_getUser();
        if(result = null){
            return ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ResponseEntity<?> createUser(@RequestBody user newUser)
    {

        List<String> allEmails = function_getAllEmail();
        if(allEmails.contains(newUser.getEmail())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        user createdUser = function_createUser();
        if(createdUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userID}")
    public ResponseEntity<?> updateUser(@RequestBody user newUser,
                                        @PathVariable String userID)
    {
        int id;
        try {
            id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        user existed = function_getUser();
        if(existed == null){
            return ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(newUser.getEmail() == null || newUser.getEmail().isEmpty() ||
                newUser.getFirst_name() == null || newUser.getFirst_name().isEmpty() ||
                newUser.getLast_name() == null || newUser.getLast_name().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user returned = function_updateUser();
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

        user existed = function_getUser();
        if(existed == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user deleted = function_deleteUser();
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
        function_clearDatabase();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userID}/projects")
    public ResponseEntity<?> createProject(@PathVariable String userID,
                                           @RequestBody project newProject)
    {
        int id;
        try {
            id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(function_getAllProjectName().contains(newProject.getName())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        project created = function_createProject();
        if(created == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(project, HttpStatus.CREATED);
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

        List<project> result = function_getAllProject();
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

        project result = function_getProject();
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userID}/projects/{projectID}")
    public ResponseEntity<?> updateProject(@PathVariable("userID") String uid,
                                           @PathVariable("projectID") String pid,
                                           @RequestBody project newProject)
    {
        int uid, pid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
        } catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(function_getAllProjectName().contains(newProject.getName())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        project exist = function_getProject();
        if(exist == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        project updated = function_updateProject();
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

        project deleted = function_deleteProject();
        if(delete == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userID}/projects/{projectID}/sessions")
    public ResponseEntity<?> createSession(@PathVariable("userID") String userID,
                                           @PathVariable("projectID") String projID,
                                           @RequestBody session newSession)
    {
        int uid, pid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
        } catch (NumberFormatException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        session created = function_createSession();
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
                                           @RequestBody session newSession)
    {
        int uid, pid, sid;
        try{
            uid = Integer.parseInt(userID);
            pid = Integer.parseInt(projID);
            sid = Integer.parseInt(sessID);
        } catch (NumberFormatException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        session updated = function_updateSession();
        if(updated == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userID}/projects/{projectID}/report")
    public ResponseEntity<?> getReport(@PathVariable("userID") String uid,
                                       @PathVariable("projectID") String pid,
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
    }

}
