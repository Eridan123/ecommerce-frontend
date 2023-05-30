package kg.ecommerce.frontend.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "admin-client", url = "${service.admin.endpoint}")
public interface ProductServiceClient {

  @RequestMapping(
          method = {RequestMethod.GET},
          value = {"/products/files/{filename}"},
          consumes = {"application/json"}
  )
  ResponseEntity<Resource> getFile(@PathVariable String filename);
}
