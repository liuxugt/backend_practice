package com.xu.database;

import com.xu.struct.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class DataBaseSessionFactory {
    SqlSessionFactory factory;

    public DataBaseSessionFactory(String env){
        Reader reader;
        try{
            reader = Resources.getResourceAsReader("configuration.xml");
            factory = new SqlSessionFactoryBuilder().build(reader, env);
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
