package Spider.mouser;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Parser {
    private static final Logger logger=  LoggerFactory.getLogger(Parser.class);
    public static ThreadPoolExecutor threadPoolExecutor=null;
    public static BlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>();
    public static Parser parser;
    private static boolean isrunning = false;
    private static int poolSize;
    private static int aliveTime;

    public static Parser start() {
        isrunning=true;
        poolSize= 20;
        threadPoolExecutor = new ThreadPoolExecutor(20, 40, 5, TimeUnit.SECONDS,queue);
        parser=new Parser();
        logger.info("Parser模块初始化完成");
        return  parser;
    }
    public static Parser get() {
        return parser;
    }

    public boolean stop() {
        isrunning=false;
        threadPoolExecutor.shutdown();
        try {
            while (!threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                logger.info("Fetcher thread pool is shutting");
            }
        } catch (InterruptedException e) {
            threadPoolExecutor.shutdownNow();
            logger.error("Parser模块退出时出错，强制退出！", e);
        }
        return threadPoolExecutor.isShutdown();
    }
    public void doparse(Document doc, List<String> list,String url) {
        threadPoolExecutor.execute(new ThreadMouser(doc,list,url));
    }
}
