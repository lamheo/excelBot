package koidira.product.excelFormularBot.config.bean;

import org.modelmapper.ModelMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper
        .getConfiguration()
        .setFieldMatchingEnabled(true)
        .setAmbiguityIgnored(true)
        .setMatchingStrategy(MatchingStrategies.STRICT)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    return modelMapper;
  }

  @Bean
  public WebDriver webDriver() {
    ChromeOptions options = new ChromeOptions();
      options.addArguments(
        "--no-sandbox",
        "--disable-notifications",
        "--disable-infobars",
        "--disable-extensions",
        "--disable-gpu",
        "--disable-dev-shm-usage",
        "--window-size=1920,1080",
        "--headless"
    );
    WebDriver webDriver = new ChromeDriver(options);
    return webDriver;
  }
}
