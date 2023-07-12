package fhi360.it.assetverify.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="assetLog")
public class AssetLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String description;
    private String category;
    private String type;
    private String assetId;
    private String serialNumber;
    private String dateReceived;
    private String funder;
    private String model;
    private String states;
    private String location;
    private String custodian;
    private String condition;
    private String emailAddress;
    private String phone;
    private String status;

    private Long assetsId;

}
