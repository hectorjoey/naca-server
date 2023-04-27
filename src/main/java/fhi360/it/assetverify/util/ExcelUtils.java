package fhi360.it.assetverify.util;

import fhi360.it.assetverify.model.Asset;
import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.Item;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    public static List<Asset> parseExcelAssetFile(final InputStream is) {
        try {
            final XSSFWorkbook workbook = new XSSFWorkbook(is);
            final XSSFSheet sheet = workbook.getSheetAt(0);
            final Iterator<Row> rows = (Iterator<Row>) sheet.iterator();
            final ArrayList<Asset> lstAsset = new ArrayList<Asset>();
            int rowNumber = 1;
            while (rows.hasNext()) {
                final Row currentRow = rows.next();
                if (rowNumber == 1) {
                    ++rowNumber;
                } else {
                    final Iterator<Cell> cellsInRow = (Iterator<Cell>) currentRow.iterator();
                    final DataFormatter formatter = new DataFormatter();
                    final Asset asset = new Asset();
                    int cellIndex = 1;
                    while (cellsInRow.hasNext()) {
                        final Cell currentCell = cellsInRow.next();
                        if (cellIndex == 1) {
                            asset.setDescription(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 2) {
                            asset.setCategory(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 3) {
                            asset.setType(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 4) {
                            asset.setAssetId(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 5) {
                            asset.setSerialNumber(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 6) {
                            asset.setDateReceived(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 7) {
                            asset.setFunder(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 8) {
                            asset.setModel(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 9) {
                            asset.setPurchasePrice(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 10) {
                            asset.setStates(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 11) {
                            asset.setYearOfPurchase(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 12) {
                            asset.setImplementer(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 13) {
                            asset.setImplementationPeriod(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 14) {
                            asset.setLocation(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 15) {
                            asset.setCustodian(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 16) {
                            asset.setCondition(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 17) {
                            asset.setEmailAddress(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 18) {
                            asset.setPhone(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 19) {
                            asset.setStatus(formatter.formatCellValue(currentCell));
                        }
                        ++cellIndex;
                    }
                    lstAsset.add(asset);
                }
            }
            workbook.close();
            return lstAsset;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static List<Item> parseExcelItemFile(final InputStream is) {
        try {
            final XSSFWorkbook workbook = new XSSFWorkbook(is);
            final XSSFSheet sheet = workbook.getSheetAt(0);
            final Iterator<Row> rows = (Iterator<Row>) sheet.iterator();
            final ArrayList<Item> lstItem = new ArrayList<Item>();
            int rowNumber = 1;
            while (rows.hasNext()) {
                final Row currentRow = rows.next();
                if (rowNumber == 1) {
                    ++rowNumber;
                } else {
                    final Iterator<Cell> cellsInRow = (Iterator<Cell>) currentRow.iterator();
                    final DataFormatter formatter = new DataFormatter();
                    final Item item = new Item();
                    int cellIndex = 1;
                    while (cellsInRow.hasNext()) {
                        final Cell currentCell = cellsInRow.next();
                        if (cellIndex == 1) {
                            item.setDate(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 2) {
                            item.setDescription(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 3) {
                            item.setQuantity(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 4) {
                            item.setRequestedBy(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 5) {
                            item.setRequesterEmail(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 6) {
                            item.setStatus(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 7) {
                            item.setCondition(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 8) {
                            item.setStates(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 9) {
                            item.setLocation(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 10) {
                            item.setAuthorizedBy(formatter.formatCellValue(currentCell));
                        }
                        ++cellIndex;
                    }
                    lstItem.add(item);
                }
            }
            workbook.close();
            return lstItem;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static List<Inventory> parseExcelInventoryFile(final InputStream is) {
        try {
            final XSSFWorkbook workbook = new XSSFWorkbook(is);
            final XSSFSheet sheet = workbook.getSheetAt(0);
            final Iterator<Row> rows = (Iterator<Row>) sheet.iterator();
            final ArrayList<Inventory> lstInventory = new ArrayList<Inventory>();
            int rowNumber = 1;
            while (rows.hasNext()) {
                final Row currentRow = rows.next();
                if (rowNumber == 1) {
                    ++rowNumber;
                } else {
                    final Iterator<Cell> cellsInRow = (Iterator<Cell>) currentRow.iterator();
                    final DataFormatter formatter = new DataFormatter();
                    final Inventory inventory = new Inventory();
                    int cellIndex = 1;
                    while (cellsInRow.hasNext()) {
                        final Cell currentCell = cellsInRow.next();

                        if (cellIndex == 1) { // NameOfArticle
                            inventory.setWarehouseName(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 2) { //DateOfPurchase
                            inventory.setDescription(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 3) { //DateOfPurchase
                            inventory.setCategory(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 4) { // source
                            inventory.setBatchNo(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 5) { //manufacturer
                            inventory.setManufactureDate(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 6) { //manufacturer
                            inventory.setExpiryDate(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 7) { // PurchaseOrderNumber
                            inventory.setUnit(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 8) { // ReceivedQuantity
                            inventory.setStockState(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 9) { // OpeningBalance
                            inventory.setOpeningBalance(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 10) { // QuantityReceived
                            inventory.setQuantityReceived(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 11) { // ClosingStock
                            inventory.setClosingStock(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 12) { // StockOnHand
                            inventory.setStockOnHand(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 13) { // ReportingMonth
                            inventory.setReportingMonth(formatter.formatCellValue(currentCell));
                        } else if (cellIndex == 14) { // Donor
                            inventory.setDonor(formatter.formatCellValue(currentCell));
//                    } else if (cellIndex == 15) { // grant
//                        inventory.setShellLifeInDays(formatter.formatCellValue(currentCell));
//                    } else if (cellIndex == 16) { // grant
//                        inventory.(formatter.formatCellValue(currentCell));
                        }
                        ++cellIndex;
                    }
                    lstInventory.add(inventory);
                }
            }
            workbook.close();
            return lstInventory;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }
}
