package net.ncue.martn.examples.datacrawler.model;

import java.util.ArrayList;

/**
 * Created by dsyoon on 2017. 7. 8..
 */
public class ProductList extends ArrayList<Product> {
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<this.size(); i++) {
            sb.append(this.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
    public void clear(ProductList productList) {
        for (int i=0; i<productList.size(); i++) {
            productList.clear();
        }
        this.clear();
    }
    public void transfer(ProductList productList) {
        for (int i=0; i<productList.size(); i++) {
            this.add(productList.get(i));
        }
    }
    public boolean equalAll(ProductList productList) {
        if (productList.size()!=this.size()) return false;
        for (int i=0; i<productList.size(); i++) {
            if (productList.get(i) == null || this.get(i)==null) return false;
            if (!productList.get(i).equalAll(this.get(i))) return false;
        }
        return true;
    }
}
