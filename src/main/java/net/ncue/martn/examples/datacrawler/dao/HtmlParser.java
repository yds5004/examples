package net.ncue.martn.examples.datacrawler.dao;

import net.ncue.martn.examples.datacrawler.model.Product;
import net.ncue.martn.examples.datacrawler.model.ProductList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by dsyoon on 2017. 7. 8..
 */
public class HtmlParser {

    public ProductList parse (String html) throws IOException, InterruptedException {
        Document doc = Jsoup.parse(html);
        String imageURL = "";
        String brandName = "";
        String productName = "";
        String discountPrice = "";
        String price = "";
        String description = "";

        ProductList productList = new ProductList();
        Elements elements = doc.select("tr[data-unittype=item]");
        for (int i=0; i<elements.size(); i++) {
            Element element = elements.get(i);
            // 이미지 얻기
            Element divElement = element.select("div[class=item_thm]").first();
            Element imageElement = divElement.select("img[src~=(?i)\\.(png|jpe?g|gif)]").first();
            imageURL = imageElement.attr("src");
            if (imageURL.indexOf("http")<0) {
                imageURL = "http:" + imageURL;
            }

            // brand & product Name 얻기
            divElement = element.select("div[class=item_info]").first();
            brandName = divElement.select("div[id=search_result_brand").first().ownText();
            productName = divElement.select("a[class=clickable").first().attr("title");


            // price 얻기
            divElement = element.select("td[class=item_info]").first();
            divElement = divElement.select("div[class=price]").first();
            discountPrice = divElement.select("strong").first().ownText().replace(",", "");
            price = "";
            description = "";
            Product product = new Product(brandName, imageURL, productName, discountPrice, price, description);
            productList.add(product);
        }
        return productList;
    }
}
