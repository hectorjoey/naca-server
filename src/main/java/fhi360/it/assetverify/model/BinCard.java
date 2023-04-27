
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
    private String description;
    private String batchNo;
    private String manufactureDate;
    private String expiryDate;
    private String openingBalance;
    private String closingStock;
    private String quantityIssued;
    private String phone;
    private String issuedTo;
    private String issuedToEmail;
    private String dispatchedLocation;
    private Long inventoryId;
}
