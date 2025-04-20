package org.airtribe.AsyncSyncBelC11.service;

import java.util.List;
import org.airtribe.AsyncSyncBelC11.dto.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class ApiService {

  @Autowired
  private RestTemplate _restTemplate;

  @Autowired
  private WebClient _webClient;


  public String invokeHello() {
    String result = _restTemplate.getForObject("http://localhost:9300/hello", String.class);
    System.out.println(result);
    for(int i=0;i<100;i++) {

    }
    return result;
  }

  public ApiResult getProductsSync() {
    long startTime = System.currentTimeMillis();
    ApiResult apiResult = _restTemplate.getForObject("https://dummyjson.com/products", ApiResult.class);
    System.out.println("Request was handled by thread " + Thread.currentThread().getName());
    for(int i=0;i<100L;i++) {
      //System.out.println("Doing some work in the foreground");
    }

    long endTime = System.currentTimeMillis();
    System.out.println("Time taken: " + (endTime - startTime));
    return apiResult;
  }

  public Mono<ApiResult> getProductsAsync() {
    System.out.println("Starting the async api call");

    Long startTime = System.currentTimeMillis();

    Mono<ApiResult> apiResultMono = _webClient.get().uri("https://dummyjson.com/products")
        .retrieve()
        .bodyToMono(ApiResult.class)
        .doOnSuccess(apiResult -> {
          System.out.println("Async Request was handled by thread " + Thread.currentThread().getName());
          Long endTime = System.currentTimeMillis();
          System.out.println("Time taken: " + (endTime - startTime));
        });

    System.out.println("Outside Async Request was handled by thread " + Thread.currentThread().getName());
    for(int i=0;i<1000L;i++) {
      //System.out.println("Doing some work in the foreground");
    }
    return apiResultMono;
  }

  public ApiResult getProductsSyncWebClient() {
    System.out.print("Starting the sync api call with web client");
    long startTime = System.currentTimeMillis();

    ApiResult apiResult = _webClient.get().uri("https://dummyjson.com/products")
        .retrieve()
        .bodyToMono(ApiResult.class)
        .block();

    System.out.println("Sync Request was handled by thread " + Thread.currentThread().getName());
    for(int i=0;i<1000L;i++) {
      //System.out.println("Doing some work in the foreground");
    }
    return apiResult;
  }

  public Mono<List<ApiResult>> getProductsInParallel() {
    Long startTime = System.currentTimeMillis();
    System.out.println("Starting the parallel api call");

    Mono<ApiResult> apiResultMono1 = _webClient.get().uri("https://dummyjson.com/products")
        .retrieve()
        .bodyToMono(ApiResult.class);

    Mono<ApiResult> apiResultMono2 = _webClient.get().uri("https://dummyjson.com/products").retrieve().bodyToMono(ApiResult.class);

    Mono<ApiResult> apiResultMono3 = _webClient.get().uri("https://dummyjson.com/products").retrieve().bodyToMono(ApiResult.class);

    Mono<List<ApiResult>> apiResultMonoList = Mono.zip(apiResultMono1, apiResultMono2, apiResultMono3)
        .map(tuple -> {
          ApiResult result1 = tuple.getT1();
          ApiResult result2 = tuple.getT2();
          ApiResult result3 = tuple.getT3();
          return List.of(result1, result2, result3);
        })
        .doOnSuccess(apiResults -> {
          System.out.println("Parallel Request was handled by thread " + Thread.currentThread().getName());
          Long endTime = System.currentTimeMillis();
          System.out.println("Time taken: " + (endTime - startTime));
        });
    return apiResultMonoList;
  }

  public Mono<ApiResult> getFastestProduct() {
    Long startTime = System.currentTimeMillis();
    System.out.println("Starting the parallel api call");

    Mono<ApiResult> apiResultMono1 = _webClient.get().uri("https://dummyjson.com/products")
        .retrieve()
        .bodyToMono(ApiResult.class);

    Mono<ApiResult> apiResultMono2 = _webClient.get().uri("https://dummyjson.com/products").retrieve().bodyToMono(ApiResult.class);

    Mono<ApiResult> apiResultMono3 = _webClient.get().uri("https://dummyjson.com/products").retrieve().bodyToMono(ApiResult.class);

    Mono<ApiResult> apiResultMono = Mono.first(apiResultMono1, apiResultMono2, apiResultMono3)
        .doOnSuccess(apiResult -> {
          System.out.println("Fastest Request was handled by thread " + Thread.currentThread().getName());
          Long endTime = System.currentTimeMillis();
          System.out.println("Time taken: " + (endTime - startTime));
        });

    return apiResultMono;
  }
}
