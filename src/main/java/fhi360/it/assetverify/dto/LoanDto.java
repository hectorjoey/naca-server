package fhi360.it.assetverify.dto;

import lombok.Data;

@Data
public class LoanDto {
    private String firstname;
    private String lastname;
    private String email;
    private String equipmentType;
    private String model;
    private String location;
    private String serialnumber;
    private String date;
    private String expiryDate;
    private String acknowledgedBy;
    private String status;


}
