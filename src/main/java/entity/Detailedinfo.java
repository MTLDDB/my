package entity;


public class Detailedinfo {

  private String objectid;
  private String url;
  // imageurl json存储
  private String img;
  private String webmpn;
  private String domain;
  private int stock;
  private int factory_stock;
  private String mpn;
  //man href
  private String manhref;
  private String mfr;
  private String descript;
  private String leadtime="";
  //detalied decription
  private String detailed="";
  //pdf
  private String pdfs="";
  // packaging
  private String packaging="";
  //Categories
  private String first_classification="";
  private String secondary_classification="";
  private String threelevel_classification="";
  private String moq;//最小起订量
  private String spq;//标准包装

  private String attr_json;
  private String price_json;

  public String getPrice_json() {
    return price_json;
  }

  public void setPrice_json(String price_json) {
    this.price_json = price_json;
  }

  public String getSpq() {
    return spq;
  }

  public void setSpq(String spq) {
    this.spq = spq;
  }

  public String getAttr_json() {
        return attr_json;
    }

    public void setAttr_json(String attr_json) {
        this.attr_json = attr_json;
    }

    public String getMoq() {
    return moq;
  }

  public void setMoq(String moq) {
    this.moq = moq;
  }
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getManhref() {
    return manhref;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public void setManhref(String manhref) {
    this.manhref = manhref;
  }

  public String getFirst_classification() {
    return first_classification;
  }

  public void setFirst_classification(String first_classification) {
    this.first_classification = first_classification;
  }

  public String getPackaging() {
    return packaging;
  }

  public void setPackaging(String packaging) {
    this.packaging = packaging;
  }

  public String getPdfs() {
    return pdfs;
  }

  public void setPdfs(String pdfs) {
    this.pdfs = pdfs;
  }

  public String getSecondary_classification() {
    return secondary_classification;
  }

  public void setSecondary_classification(String secondary_classification) {
    this.secondary_classification = secondary_classification;
  }

  public String getThreelevel_classification() {
    return threelevel_classification;
  }

  public void setThreelevel_classification(String threelevel_classification) {
    this.threelevel_classification = threelevel_classification;
  }

  public String getObjectid() {
    return objectid;
  }

  public void setObjectid(String objectid) {
    this.objectid = objectid;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getWebmpn() {
    return webmpn;
  }

  public void setWebmpn(String webmpn) {
    this.webmpn = webmpn;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }


  public int getFactory_stock() { return factory_stock;
  }

  public void setFactory_stock(int factory_stock) { this.factory_stock = factory_stock;
  }


  public String getMpn() {
    return mpn;
  }

  public void setMpn(String mpn) {
    this.mpn = mpn;
  }

  public String getMfr() {

    return mfr;
  }

  public void setMfr(String mfr) {
    this.mfr = mfr;
  }

  public String getDescript() {
    return descript;
  }

  public void setDescript(String descript) {
    this.descript = descript;
  }

  public String getLeadtime() {
    return leadtime;
  }

  public void setLeadtime(String leadtime) {
    this.leadtime = leadtime;
  }


  public String getDetailed() {
    return detailed;
  }

  public void setDetailed(String detailed) {
    this.detailed = detailed;
  }

}
