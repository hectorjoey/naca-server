package fhi360.it.assetverify.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue_log")
public class IssueLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    private String lossesAndAdjustments;

}
