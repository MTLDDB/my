package Spider.avnet;

import com.alibaba.fastjson.JSON;
import entity.Category;
import mapper.AvnetMapper;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import util.Session;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaveAvnet {
    private static AvnetMapper avnetMapper;
    static {
        //返回mapper接口的代理对象，
    }
    private Lock lock = new ReentrantLock();
    public  void save(Category c){
        avnetMapper = Session.getMapper(AvnetMapper.class);
       // lock.lock();
        avnetMapper.saveCategory(c);
        System.out.println(JSON.toJSONString(c));
       // lock.unlock();
    }
    public synchronized void saveList(List<Category> list){
        for (Category c:list){
            save(c);
        }
    }
}
