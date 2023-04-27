package fhi360.it.assetverify.dto;

import lombok.Data;

@Data
public class InventoryDto {
    private String warehouseName;
    private String description;
    private String category;
    private String batchNo;
    private String manufactureDate;
    private String expiryDate;
    private String unit;
    private String stockState;
    private String openingBalance;
    private String quantityReceived;
    private String closingStock;
    private String stockOnHand;
    private String reportingMonth;
    //    private String states;
    private String donor;
}