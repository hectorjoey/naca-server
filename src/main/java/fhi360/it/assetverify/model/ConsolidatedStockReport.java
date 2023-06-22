package fhi360.it.assetverify.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "consolidated_stock_report")
public class ConsolidatedStockReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String no;
    private String date;
    private String warehouseName;
    private String itemDescription;
    private String unit;
    private String batchNo;
    private String expiryDate;
    private String quantityReceived;
    private String quantityIssued;
    private String lossesAndAdjustments;
    private String stockOnHand; //Quantity Received/Stock On Hand
    private String mos;

    private Long inventoryId;
}