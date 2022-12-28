package koidira.product.excelFormularBot.shared;


public enum ResponseDataStatus {
  SUCCESS("SUCCESS"),
  ERROR("ERROR");

  private final String status;

  ResponseDataStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}

