package Spider.mouser;

import com.alibaba.fastjson.JSON;
import entity.Category;
import mapper.AvnetMapper;
import mapper.MouserMapper;
import util.Session;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaveMouser {
    private static MouserMapper mouserMapper;
    static {
        //返回mapper接口的代理对象，
    }
   // private Lock lock = new ReentrantLock();
    public static void save(Category c){
        mouserMapper = Session.getMapper(MouserMapper.class);
        // lock.lock();
        mouserMapper.saveCategory(c);
       // System.out.println(JSON.toJSONString(c));
        // lock.unlock();
    }
}
