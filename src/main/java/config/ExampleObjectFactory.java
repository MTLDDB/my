package config;

import org.apache.http.util.Args;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.sql.Types;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class ExampleObjectFactory extends DefaultObjectFactory {
    @Override
    public Object create(Class type) {
        return super.create(type);
    }


    public void setProperties(Properties properties) {
        Iterator iterator = properties.keySet().iterator();
            while (iterator.hasNext()){
                   String keyValue = String.valueOf(iterator.next());
                   System.out.println(properties.getProperty(keyValue));
                  }
        super.setProperties(properties);
    }

    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}