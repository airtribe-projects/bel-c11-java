package org.airtribe.AsyncSyncBelC11.controller;

import java.util.List;
import org.airtribe.AsyncSyncBelC11.dto.ApiResult;
import org.airtribe.AsyncSyncBelC11.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class ApiController {

  @Autowired
  private ApiService _apiService;

  @GetMapping("/hello")
  public String hello() {
    return "Hello, World!";
  }

  @GetMapping("/hello2")
  public String hello2() {
    return _apiService.invokeHello();
  }

  @GetMapping("/products/sync")
  public ApiResult productsSync() {
    return _apiService.getProductsSync();
  }

  @GetMapping("/products/async")
  public Mono<ApiResult> getProductsAsync() {
    return _apiService.getProductsAsync();
  }

  @GetMapping("/products/syncWebClient")
  public ApiResult getProductsSyncWebClient() {
    return _apiService.getProductsSyncWebClient();
  }


  @GetMapping("/products/parallel")
  public Mono<List<ApiResult>> getProductsInParallel() {
    return _apiService.getProductsInParallel();
  }

  @GetMapping("/products/fastest")
  public Mono<ApiResult> getFastestProduct() {
    return _apiService.getFastestProduct();
  }

}
