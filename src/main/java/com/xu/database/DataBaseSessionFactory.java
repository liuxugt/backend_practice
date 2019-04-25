package com.xu.database;

import com.xu.struct.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DataBaseSessionFactory {
    SqlSessionFactory factory;

    public DataBaseSessionFactory(String environment){
        InputStream input;
        try{
            input = Resources.getResourceAsStream("mapper.xml");
            factory = new SqlSessionFactoryBuilder().build(input, environment);
        } catch (FileNotFoundException filenotfoundException){
            filenotfoundException.printStackTrace();

        }catch(IOException ioException)  {
            ioException.printStackTrace();
        }
    }

    public SqlSessionFactory getFactory(){
        return factory;
    }
}
