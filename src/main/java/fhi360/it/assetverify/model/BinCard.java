
package fhi360.it.assetverify.model;

import com.sun.istack.Nullable;
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
    private String issuedTo;
    private String batchNo;
    private String expiryDate;
    private String quantityReceived;
    private String quantityIssued;

    private String losses;
    private String positiveAdjustment;
    private String negativeAdjustment;

    private String issuedToEmail;
    private String phone;
    @Nullable
    private String stockBalance;
    private String dispatchedLocation;
    private Long inventoryId;

    @Nullable
    private String openingBalance;
    @Nullable
    private String closingStock;


}
