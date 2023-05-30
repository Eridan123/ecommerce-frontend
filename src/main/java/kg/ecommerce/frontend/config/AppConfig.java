package kg.ecommerce.frontend.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.dialect.SpringStandardDialect;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class })
public class AppConfig extends WebMvcAutoConfiguration implements ApplicationContextAware {


  private static final String UTF8 = "UTF-8";

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine((ISpringTemplateEngine) templateEngine());
    resolver.setCharacterEncoding(UTF8);
    resolver.setContentType("text/html;charset=UTF-8");
    resolver.setCache(false);
    return resolver;
  }

  private TemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    Set<IDialect> dialects = new HashSet<>();
    dialects.add(new SpringStandardDialect());
    engine.setTemplateResolver(templateResolver());
    engine.setAdditionalDialects(dialects);
    return engine;
  }

  private ITemplateResolver templateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("/resources/templates/");
    resolver.setSuffix(".html");
    resolver.setCharacterEncoding(UTF8);
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setCacheable(false);
    return resolver;
  }

  @Bean(name = "multipartResolver")
  public MultipartResolver createMultipartResolver() {
    return new StandardServletMultipartResolver();
  }
}