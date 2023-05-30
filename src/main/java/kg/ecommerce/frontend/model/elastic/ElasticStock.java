package kg.ecommerce.frontend.model.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "stocks-index")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticStock {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "sku")
    private String sku;

    @Field(type = FieldType.Text, name = "region")
    private String region;

    @Field(type = FieldType.Integer, name = "quantity")
    private Integer quantity;

    @Field(type = FieldType.Date, name = "createdDate")
    private String createdDate;

    @Field(type = FieldType.Date, name = "lastModifiedDate")
    private String lastModifiedDate;
}