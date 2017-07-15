package net.ncue.martn.MartDataCrawler.model;

/**
 * Created by dsyoon on 2017. 7. 8..
 */
public class Product {
    private String brandName = "";
    private String imageURL = "";
    private String productName = "";
    private String discountPrice = "";
    private String price = "";
    private String description = "";

    public Product() {}
    public Product(String brandName, String imageURL, String productName, String discountPrice, String price, String description) {
        this.brandName = brandName;
        this.imageURL = imageURL;
        this.productName = productName;
        this.discountPrice = discountPrice;
        this.price = price;
        this.description = description;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.brandName).append("\t");
        sb.append(this.productName).append("\t");
        sb.append(this.discountPrice).append("\t");
        sb.append(this.price).append("\t");
        sb.append(this.imageURL).append("\t");
        sb.append(this.description);
        return sb.toString();
    }

    public void transfer(Product product) {
        this.brandName = product.getBrand();
        this.imageURL = product.getImageURL();
        this.productName = product.getProductName();
        this.discountPrice = product.getOriginalprice();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

    public boolean equalAll(Product product) {
        if (!this.brandName.equals(product.brandName)) return false;
        if (!this.imageURL.equals(product.imageURL)) return false;
        if (!this.productName.equals(product.productName)) return false;
        if (!this.discountPrice.equals(product.discountPrice)) return false;
        if (!this.price.equals(product.price)) return false;
        if (!this.description.equals(product.description)) return false;
        return true;
    }


    public void setBrand(String brandName) {
        this.brandName = brandName;
    }
    public String getBrand() {
        return this.brandName;
    }
    public void setImageURL(String imageURL) {
        if (imageURL.indexOf("http")<0) {
            this.imageURL = "http:"+imageURL;
        } else {
            this.imageURL = imageURL;
        }
    }
    public String getImageURL() {
        return this.imageURL;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setOriginalprice(String discountPrice) {
        this.discountPrice = discountPrice;
    }
    public String getOriginalprice() {
        if (this.discountPrice.equals("")) return "-1";
        return this.discountPrice;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPrice() {
        if (this.price.equals("")) return "-1";
        return this.price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
}
