package entity;

import org.apache.ibatis.annotations.Update;

public class Excel {
    private String url;
    private String domain="digikey.com";
    private int priority=0;
    private int stockUpdate=0;
    private int filterUpdate=0;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStockUpdate() {
        return stockUpdate;
    }

    public void setStockUpdate(int stockUpdate) {
        this.stockUpdate = stockUpdate;
    }

    public int getFilterUpdate() {
        return filterUpdate;
    }

    public void setFilterUpdate(int filterUpdate) {
        this.filterUpdate = filterUpdate;
    }
}
