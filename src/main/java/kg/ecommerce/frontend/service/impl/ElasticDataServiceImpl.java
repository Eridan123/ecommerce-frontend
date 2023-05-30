package kg.ecommerce.frontend.service.impl;

import kg.ecommerce.frontend.model.dto.ProductDto;
import kg.ecommerce.frontend.model.elastic.ElasticPrice;
import kg.ecommerce.frontend.model.elastic.ElasticProduct;
import kg.ecommerce.frontend.model.elastic.ElasticStock;
import kg.ecommerce.frontend.repository.ElasticSearchQuery;
import kg.ecommerce.frontend.service.ElasticDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ElasticDataServiceImpl implements ElasticDataService {
    private final ElasticSearchQuery elasticSearchQuery;
    @Override
    public List<ProductDto> getProducts() throws IOException {
        List<ElasticProduct> products = elasticSearchQuery.searchAllProducts();
        List<ElasticStock> stocks = elasticSearchQuery.searchAllStocks();
        List<ElasticPrice> prices = elasticSearchQuery.searchAllPrices();

        List<ProductDto> productDtos = products.stream().map(
          a -> {
              ProductDto productDto = new ProductDto();
              productDto.setId(Long.valueOf(a.getId()));
              productDto.setImage(a.getImage());
              productDto.setName(a.getName());
              productDto.setDescription(a.getDescription());
              productDto.setSku(a.getSku());
              productDto.setCreatedDate(LocalDateTime.parse(a.getCreatedDate()));
              productDto.setLastModifiedDate(LocalDateTime.parse(a.getLastModifiedDate()));

              ElasticStock stock = stocks.stream().filter(elasticStock -> a.getSku().equals(elasticStock.getSku())).findAny().orElse(null);
              productDto.setAvailable(stock!=null?stock.getQuantity()>0:false);
              productDto.setQuantity(stock!=null?stock.getQuantity():0);

              ElasticPrice price = prices.stream().filter(elasticPrice -> a.getSku().equals(elasticPrice.getSku())).findAny().orElse(null);
              if (price == null){
                  productDto.setAvailable(false);
              }
              else if(price.getValue() == Double.valueOf(0)){
                  productDto.setAvailable(false);
              }
              else{
                  productDto.setPrice(price.getValue());
              }
              return productDto;
          }
        ).toList();
        return productDtos;
    }

    @Override
    public List<ProductDto> getByCategory(String categoryName) throws IOException {
        List<ElasticProduct> products = elasticSearchQuery.searchByCategory(categoryName);
        List<ElasticStock> stocks = elasticSearchQuery.searchAllStocks();
        List<ElasticPrice> prices = elasticSearchQuery.searchAllPrices();

        return products.stream().map(
                a -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(Long.valueOf(a.getId()));
                    productDto.setImage(a.getImage());
                    productDto.setName(a.getName());
                    productDto.setDescription(a.getDescription());
                    productDto.setSku(a.getSku());
                    productDto.setCategory(a.getCategory());
                    productDto.setCreatedDate(LocalDateTime.parse(a.getCreatedDate()));
                    productDto.setLastModifiedDate(LocalDateTime.parse(a.getLastModifiedDate()));

                    ElasticStock stock = stocks.stream().filter(elasticStock -> a.getSku().equals(elasticStock.getSku())).findAny().orElse(null);
                    productDto.setAvailable(stock!=null?stock.getQuantity()>0:false);
                    productDto.setQuantity(stock!=null?stock.getQuantity():0);

                    ElasticPrice price = prices.stream().filter(elasticPrice -> a.getSku().equals(elasticPrice.getSku())).findAny().orElse(null);
                    if (price == null){
                        productDto.setAvailable(false);
                    }
                    else if(price.getValue() == Double.valueOf(0)){
                        productDto.setAvailable(false);
                    }
                    else{
                        productDto.setPrice(price.getValue());
                    }
                    return productDto;
                }
        ).toList();
    }

    @Override
    public List<ProductDto> fullSearch(String searchText) throws IOException {
        List<ElasticProduct> products = elasticSearchQuery.fullSearch(searchText);
        List<ElasticStock> stocks = elasticSearchQuery.searchAllStocks();
        List<ElasticPrice> prices = elasticSearchQuery.searchAllPrices();

        List<ProductDto> result = new ArrayList<>();

        products.stream().map(
                a -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(Long.valueOf(a.getId()));
                    productDto.setImage(a.getImage());
                    productDto.setName(a.getName());
                    productDto.setDescription(a.getDescription());
                    productDto.setSku(a.getSku());
                    productDto.setCategory(a.getCategory());
                    productDto.setCreatedDate(LocalDateTime.parse(a.getCreatedDate()));
                    productDto.setLastModifiedDate(LocalDateTime.parse(a.getLastModifiedDate()));

                    ElasticStock stock = stocks.stream().filter(elasticStock -> a.getSku().equals(elasticStock.getSku())).findAny().orElse(null);
                    productDto.setAvailable(stock!=null?stock.getQuantity()>0:false);
                    productDto.setQuantity(stock!=null?stock.getQuantity():0);

                    ElasticPrice price = prices.stream().filter(elasticPrice -> a.getSku().equals(elasticPrice.getSku())).findAny().orElse(null);
                    if (price == null){
                        productDto.setAvailable(false);
                    }
                    else if(price.getValue() == Double.valueOf(0)){
                        productDto.setAvailable(false);
                    }
                    else{
                        productDto.setPrice(price.getValue());
                    }
                    if (productDto.getAvailable())
                        result.add(productDto);
                    return productDto;
                }
        );

        return result;
    }
}
