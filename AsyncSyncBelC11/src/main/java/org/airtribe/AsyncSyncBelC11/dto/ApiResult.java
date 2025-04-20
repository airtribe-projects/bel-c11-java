package org.airtribe.AsyncSyncBelC11.dto;

import java.util.List;


public class ApiResult {

  public List<ApiMeasurement> products;

  public ApiResult(List<ApiMeasurement> apiMeasurementList) {
    products = apiMeasurementList;
  }

  public List<ApiMeasurement> getApiMeasurementList() {
    return products;
  }

  public void setApiMeasurementList(List<ApiMeasurement> apiMeasurementList) {
    products = apiMeasurementList;
  }
}
