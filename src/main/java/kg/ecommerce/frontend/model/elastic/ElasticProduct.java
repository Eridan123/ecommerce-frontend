package kg.ecommerce.frontend.model.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products-index")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticProduct {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "sku")
    private String sku;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Text, name = "category")
    private String category;

    @Field(type = FieldType.Text, name = "image")
    private String image;

    @Field(type = FieldType.Date, name = "createdDate")
    private String createdDate;

    @Field(type = FieldType.Date, name = "lastModifiedDate")
    private String lastModifiedDate;
}