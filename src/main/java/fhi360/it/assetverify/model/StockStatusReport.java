package fhi360.it.assetverify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "stock_status_report")
public class StockStatusReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String warehouseName;
    private String itemDescription;
    private String batchNo;
    private String expiryDate;
    private String shellLife;
//    private String sum;
    private String stockBalance;
    private String mos; //quantity Received/stock on hand
    private String remark;
    private String quantityReceived; //quantity
}
