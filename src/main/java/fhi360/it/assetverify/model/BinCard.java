
package fhi360.it.assetverify.model;

import lombok.Data;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Data
@Entity
@Table(name = "binCard")
public class BinCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String warehouseName;
    private String itemDescription;
    private String unit;
    private String voucherOrRefNumber;
    private String receivedFrom;
    private String batchNo;
    private String issuedTo;
    private String expiryDate;
    private String quantityReceived;
    private String manufactureDate;
    private String quantityIssued;
    private String lossesAndAdjustments;
//    private String adjustmentUp;
//    private String adjustmentDown;
    private String phone;
    private String issuedToEmail;
    private String stockBalance;
    private String closingStock;
    private String dispatchedLocation;
    private Long inventoryId;

    private String mos;


}
