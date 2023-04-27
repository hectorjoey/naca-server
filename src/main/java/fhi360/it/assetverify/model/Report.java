package fhi360.it.assetverify.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String warehouseName;
    private String description;
    private String category;
    private String expiryDate;
    private String unit;
    private String stockState;
    private String quantityIssuedInMonthOne;
    private String quantityIssuedInMonthTwo;
    private String quantityIssuedInMonthThree;
    private String sum;
    private String stockOnHand;
    private String amc;
    private String mos;
    private String remark;
}
