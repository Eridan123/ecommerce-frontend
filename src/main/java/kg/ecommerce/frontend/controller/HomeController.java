package kg.ecommerce.frontend.controller;

import jakarta.ws.rs.QueryParam;
import kg.ecommerce.frontend.api.ProductServiceClient;
import kg.ecommerce.frontend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController
{
    private final ProductService productService;
    private final ProductServiceClient productServiceClient;

    @GetMapping
    public String home(Model model) throws IOException {
        model.addAttribute("products", productService.getProducts());
        return "home";
    }

    @GetMapping("category/{categoryName}")
    public String category(Model model, @PathVariable String categoryName) throws IOException {
        model.addAttribute("products", productService.getProductsByCategory(categoryName));
        return "home";
    }

    @GetMapping("search")
    public String fullSearch(Model model, @QueryParam("searchText") String searchText) throws IOException {
        model.addAttribute("products", productService.fullSearch(searchText));
        return "home";
    }

    @GetMapping("products/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        return productServiceClient.getFile(filename);
    }
}
