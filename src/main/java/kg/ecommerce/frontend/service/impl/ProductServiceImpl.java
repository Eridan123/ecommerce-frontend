package kg.ecommerce.frontend.service.impl;

import kg.ecommerce.frontend.model.dto.ProductDto;
import kg.ecommerce.frontend.service.ElasticDataService;
import kg.ecommerce.frontend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ElasticDataService elasticDataService;
    @Override
//    @Cacheable
    public List<ProductDto> getProducts() throws IOException {
        return elasticDataService.getProducts();
    }

    @Override
//    @Cacheable
    public List<ProductDto> getProductsByCategory(String categoryName) throws IOException {
        return elasticDataService.getByCategory(categoryName);
    }

    @Override
    public List<ProductDto> fullSearch(String searchText) throws IOException {
        return elasticDataService.fullSearch(searchText);
    }
}
