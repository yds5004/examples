package net.ncue.martn.examples.datacrawler.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import net.htmlparser.jericho.Source;
import net.ncue.martn.examples.datacrawler.dao.HtmlParser;
import net.ncue.martn.examples.datacrawler.model.Product;
import net.ncue.martn.examples.datacrawler.model.ProductList;

public class EmartDataCrawler {

    HtmlParser htmlParser = new HtmlParser();

    public String getHTML(String webURL) throws IOException {
        String html = "";
        StringBuilder contents = new StringBuilder();

        URL url = new URL(webURL);
        URLConnection con = (URLConnection)url.openConnection();
        InputStreamReader reader = new InputStreamReader (con.getInputStream(), "utf-8");

        BufferedReader buff = new BufferedReader(reader);

        while((html = buff.readLine())!=null) {
            contents.append(html);
            contents.append(" ");
        }
        buff.close();

        return contents.toString();
    }

    public ProductList crawl (String webURL) throws Exception {
        System.out.println(webURL);

        URL url = new URL(webURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(35000);
        connection.setReadTimeout(5000);
        HttpURLConnection.setFollowRedirects(true);
        Thread.sleep(500);
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) return null;

        Source source = new Source(url);
        ProductList productList = htmlParser.parse(source.toString());

        return productList;
    }

    public ProductList crawl (BufferedWriter out, String webURL) throws Exception {
        System.out.println(webURL);

        ProductList productList = htmlParser.parse(this.getHTML(webURL));
        for (int i=0; i<productList.size(); i++) {
            Product product = productList.get(i);
            out.write(product.toString());
            out.newLine();
        }
        return productList;
    }

    public static void main(String[] args) {
        try {
            String emartDataFile = "resources/emart.txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(emartDataFile));

            EmartDataCrawler crawler = new EmartDataCrawler();
            ProductList productList = new ProductList();
            for (int pageNo=1; pageNo<=1000; pageNo++) {
                Thread.sleep(10000);
                String webURL = "http://emart.ssg.com/search.ssg?target=all&query="+URLEncoder.encode("핸드폰케이스", "UTF-8")+"&ctgId=0006511833&ctgLv=3&&page=";
                webURL += Integer.toString(pageNo);
                ProductList products = crawler.crawl(out, webURL);
                if (products != null) {
                    productList.addAll(products);
                }
            }

            out.close();
            System.out.println("EMart Crawler done...");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
