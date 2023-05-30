package kg.ecommerce.frontend.service;

import kg.ecommerce.frontend.model.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface ElasticDataService {
    List<ProductDto> getProducts() throws IOException;

    List<ProductDto> getByCategory(String categoryName) throws IOException;
    List<ProductDto> fullSearch(String searchText) throws IOException;
}
