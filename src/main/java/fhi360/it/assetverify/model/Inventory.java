package fhi360.it.assetverify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@NoArgsConstructor
@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
