package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Session {
    public static SqlSession SESSION;
    public static <T> T getMapper(Class<T> clazz){
        SqlSessionFactory sqlSessionFactory;
        String resource = "configuration.xml";
        T mapper;
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SESSION = sqlSessionFactory.openSession(true);//ture为自动提交事务
        mapper=SESSION.getMapper(clazz);
        return mapper;
    }
}
