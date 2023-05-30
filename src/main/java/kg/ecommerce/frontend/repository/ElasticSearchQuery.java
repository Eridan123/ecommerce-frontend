package kg.ecommerce.frontend.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import kg.ecommerce.frontend.model.elastic.ElasticPrice;
import kg.ecommerce.frontend.model.elastic.ElasticProduct;
import kg.ecommerce.frontend.model.elastic.ElasticStock;
import org.codelibs.elasticsearch.common.unit.Fuzziness;
import org.codelibs.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class ElasticSearchQuery {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String productIndex = "products-index";
    private final String priceIndex = "prices-index";
    private final String stockIndex = "stocks-index";


    public  List<ElasticProduct> searchAllProducts() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(productIndex));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, ElasticProduct.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ElasticProduct> products = new ArrayList<>();
        for(Hit object : hits){
            products.add((ElasticProduct) object.source());
        }
        return products;
    }

    public  List<ElasticPrice> searchAllPrices() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(priceIndex));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, ElasticPrice.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ElasticPrice> prices = new ArrayList<>();
        for(Hit object : hits){
            prices.add((ElasticPrice) object.source());
        }
        return prices;
    }

    public  List<ElasticStock> searchAllStocks() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(stockIndex));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, ElasticStock.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ElasticStock> stocks = new ArrayList<>();
        for(Hit object : hits){
            stocks.add((ElasticStock) object.source());
        }
        return stocks;
    }

    public  List<ElasticProduct> searchByCategory(String category) throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put("category", category);

//        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(productIndex));
        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(productIndex).query(q -> q.match(
                t -> t .field("category").query(category))));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, ElasticProduct.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ElasticProduct> products = new ArrayList<>();
        for(Hit object : hits){
            products.add((ElasticProduct) object.source());

        }
        return products;
    }

    public  List<ElasticProduct> fullSearch(String searchText) throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(productIndex).query(q -> q.multiMatch(
                t -> t .fields("category", "name", "description", "sku").query(searchText).fuzziness("01"))));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, ElasticProduct.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ElasticProduct> products = new ArrayList<>();
        for(Hit object : hits){
            products.add((ElasticProduct) object.source());

        }
        return products;
    }
}
