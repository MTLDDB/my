package util;


import entity.SolrBean;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

public class QuerySolr {
//    @Autowired
//    private SolrClient solrClient;
//    public void querySolr() throws Exception{
//
//        // HttpSolrServer solrServer = new HttpSolrServer(SOLR_URL+"test");//http://127.0.0.1:8081/amall3-solr/collection1
//
//        SolrQuery query = new SolrQuery();
//        //下面设置solr查询参数
//        query.set("q", "*:*");// 参数q  查询所有
//        // query.set("id","100");//相关查询，比如某条数据某个字段含有周、星、驰三个字  将会查询出来 ，这个作用适用于联想查询
//
//        //参数fq, 给query增加过滤查询条件
//        // query.addFilterQuery("id:[0 TO 9]");//id为0-4
//
//        //给query增加布尔过滤条件
//        //query.addFilterQuery("description:演员");  //description字段中含有“演员”两字的数据
//
//        //参数df,给query设置默认搜索域
//        //query.set("df", "description");
//
//        //参数sort,设置返回结果的排序规则
//        query.setSort("id",SolrQuery.ORDER.desc);
//
//        //设置分页参数
//        query.setStart(0);
//        query.setRows(10);//每一页多少值
//
//        //参数hl,设置高亮
//        query.setHighlight(true);
//        //设置高亮的字段
//        // query.addHighlightField("name");
//        //设置高亮的样式
//        query.setHighlightSimplePre("<font color='red'>");
//        query.setHighlightSimplePost("</font>");
//
//        //获取查询结果
//        QueryResponse response = solrServer.query(query);
//        //两种结果获取：得到文档集合或者实体对象
//
//        //查询得到文档的集合
//        SolrDocumentList solrDocumentList = response.getResults();
//        System.out.println("通过文档集合获取查询的结果");
//        System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());
//        //遍历列表
//        for (SolrDocument doc : solrDocumentList) {
//            System.out.println("id:"+doc.get("id")+"  name:"+doc.get("name")+"    description:"+doc.get("description"));
//        }
//
//        //得到实体对象
////        List<Person> tmpLists = response.getBeans(Person.class);
////        if(tmpLists!=null && tmpLists.size()>0){
////            System.out.println("通过文档集合获取查询的结果");
////            for(Person per:tmpLists){
////                System.out.println("id:"+per.getId()+"   name:"+per.getName()+"    description:"+per.getDescription());
////            }
////        }
//    }

    HttpSolrClient solrClient;
{
    solrClient=new HttpSolrClient("http://localhost:8983/solr/new_core");
}
    public SolrBean querySolr(String id){
        SolrQuery query = new SolrQuery();
        String str="id:"+id;//+webmpn.replace("[","%5B").replace("]","%5D");
        query.set("q",str);// 参数q  查询所有
        QueryResponse response = null;
        try {
            response = solrClient.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SolrDocumentList solrDocumentList = response.getResults();
        SolrBean solrBean=new SolrBean();
        if(solrDocumentList.size()==0){
            System.out.println("error:"+id);
            return null;
        }
        SolrDocument doc =solrDocumentList.get(0);
        solrBean.setId(id);
        solrBean.setWebmpn(doc.get("webmpn").toString());
        System.out.println(solrBean.getWebmpn());
        solrBean.setPrice(doc.get("price").toString());
        solrBean.setAttr(doc.get("attr").toString());
        return solrBean;
    }
}
