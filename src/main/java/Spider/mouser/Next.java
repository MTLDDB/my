package Spider.mouser;

import com.alibaba.fastjson.JSON;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Next {
    private static final Logger logger = LoggerFactory.getLogger(Next.class);
    public static ThreadPoolExecutor threadPoolExecutor;
    public static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
    private static boolean isrunning = false;
    private static Next next;
    boolean flag=true;
    public static Next start() {
        isrunning = true;
        threadPoolExecutor = new ThreadPoolExecutor(20, 40, 5, TimeUnit.SECONDS, queue);
        next = new Next();
        logger.info("nexter模块初始化完成");
        return next;
    }

    public static Next get() {
        return next;
    }

    public boolean stop() {
        threadPoolExecutor.shutdown();
        int shutNum = 0;
        try {
            while (!threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS)) {//等待未执行完的线程继续执行
                logger.info("thread pool is shutting");
                if (++shutNum > 10)
                    threadPoolExecutor.shutdownNow();//所有的线程执行完毕，关闭线程池
            }
        } catch (InterruptedException e) {
            threadPoolExecutor.shutdownNow();
            logger.error("未处理错误！", e);
        }
        return threadPoolExecutor.isShutdown();//返回线程池的状态
    }

    public void getDoc(String url, List<String> list) {
        Document document;
        Future<Document> getNext = threadPoolExecutor.submit(new FetchThrad(url));//任务加入到爬取线程池
        try {
            document = getNext.get(45, TimeUnit.SECONDS);//等待计算完成，然后检索其结果,45内未返回结果，结果为null

            //System.out.println(document);
            Element temp=document.select("div[id=distilIdentificationBlock]").first();
            if (document != null&&temp==null){
                Parser.get().doparse(document, list, url);
            }else {
                if(flag){
                    getDoc(url,list);
                    flag=false;
                }

            }

        } catch (TimeoutException e) {
            getNext.cancel(true);//尝试取消执行此任务。
            logger.info("next任务超时 [{}]");
        } catch (InterruptedException e) {
            getNext.cancel(true);
            logger.info("next任务失败 InterruptedException [{}]", e);
        } catch (ExecutionException e) {
            getNext.cancel(true);
            logger.info("next任务失败 ExecutionException [{}]", e);
        } catch (Exception e) {
            getNext.cancel(true);
            logger.info("next任务失败 未知错误！ [{}]", e);
        }
    }
//    public  Map<String,List<String>> getMap(String key,List<String> value){
//        Map<String,List<String>> listMap=null;
//       // System.out.println(JSON.toJSONString(map));
//        Future<Map<String,List<String>>> getNext = threadPoolExecutor.submit(new ThreadMouser(key,value));//任务加入到爬取线程池
//        try {
//            listMap = getNext.get(45, TimeUnit.SECONDS);//等待计算完成，然后检索其结果,45内未返回结果，结果为null
//        } catch (TimeoutException e) {
//            getNext.cancel(true);//尝试取消执行此任务。
//            logger.info("next任务超时 [{}]");
//        } catch (InterruptedException e) {
//            getNext.cancel(true);
//            logger.info("next任务失败 InterruptedException [{}]", e);
//        } catch (ExecutionException e) {
//            getNext.cancel(true);
//            logger.info("next任务失败 ExecutionException [{}]",e);
//        } catch (Exception e) {
//            getNext.cancel(true);
//            logger.info("next任务失败 未知错误！ [{}]",e);
//        }
//        return listMap;
//    }

}
