package fhi360.it.assetverify.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
