package Spider.verical;

import com.alibaba.fastjson.JSON;
import entity.Category;
import mapper.AvnetMapper;
import mapper.VericalMapper;
import util.Session;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaveVerical {
    private static VericalMapper vercicalMapper;
    static {
        vercicalMapper = Session.getMapper(VericalMapper.class);
        //返回mapper接口的代理对象，
    }
    private Lock lock = new ReentrantLock();
    public static void save(Category c){

       // lock.lock();
        vercicalMapper.saveCategory(c);
        System.out.println(JSON.toJSONString(c));
       // lock.unlock();
    }
    public synchronized void saveList(List<Category> list){
        for (Category c:list){
            save(c);
        }
    }
}
