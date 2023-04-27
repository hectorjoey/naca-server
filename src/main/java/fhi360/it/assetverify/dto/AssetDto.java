package fhi360.it.assetverify.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AssetDto {
    private String description;
    private String category;
    private String type;
    private String assetId;
    private String serialNumber;
    private String dateReceived;
    private String funder;
    private String model;
    private String purchasePrice;
    private String states;
    private String yearOfPurchase;
    private String implementer;
    private String implementationPeriod;
    private String location;
    private String custodian;
    private String condition;
    private String emailAddress;
    private String phone;
    private String status;
}
