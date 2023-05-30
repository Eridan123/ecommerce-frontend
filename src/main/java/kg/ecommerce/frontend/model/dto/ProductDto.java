package kg.ecommerce.frontend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  private Long id;
  private String sku;
  private String name;
  private String description;
  private String image;
  private Double price;
  private Boolean available = false;
  private int quantity;
  private String category;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
}
