package fhi360.it.assetverify.model;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueLogEntry {
    private long id;

    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "Date.")
    private String date;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "Warehouse name")
    private String warehouseName;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "Item Description")
    private String itemDescription;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "Unit")
    private String unit;

    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "Voucher Or RefNumber")
    private String voucherOrRefNumber;

    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "Received From")
    private String receivedFrom;

    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "Issued To")
    private String issuedTo;

    @CsvBindByPosition(position = 7)
    @CsvBindByName(column = "Batch No")
    private String batchNo;

    @CsvBindByPosition(position = 8)
    @CsvBindByName(column = "Expiry Date")
    private String expiryDate;

    @CsvBindByPosition(position = 9)
    @CsvBindByName(column = "Quantity Received")
    private String quantityReceived;

    @CsvBindByPosition(position = 10)
    @CsvBindByName(column = "Quantity Issued")
    private String quantityIssued;

    @CsvBindByPosition(position = 11)
    @CsvBindByName(column = "Issued to Email")
    private String issuedToEmail;

    @CsvBindByPosition(position = 12)
    @CsvBindByName(column = "Phone")
    private String phone;

    @CsvBindByPosition(position = 13)
    @CsvBindByName(column = "Stock Balance")
    private String stockBalance;

    @CsvBindByPosition(position = 14)
    @CsvBindByName(column = "Dispatch Location")
    private String dispatchedLocation;
//    private Long inventoryId;

}

