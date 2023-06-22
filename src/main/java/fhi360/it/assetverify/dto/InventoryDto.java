package fhi360.it.assetverify.dto;

import lombok.Data;

@Data
public class InventoryDto {
    private String warehouseName;
    private String itemDescription;
    private String category;
    private String batchNo;
    private String manufactureDate;
    private String expiryDate;
    private String unit;
    private String stockState;
    private String stockBalance;
    private String quantityReceived;
    private String closingStock;
    private String stockOnHand;
    private String reportingMonth;
    //    private String states;
    private String donor;

    private String mos;
    private String shellLife;
    //    private String lossesAndAdjustments;
    private String losses;
    private String positiveAdjustment;
    private String negativeAdjustment;
    private String remark;
}