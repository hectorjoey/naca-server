package fhi360.it.assetverify.util;

import fhi360.it.assetverify.model.Asset;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class AssetExcelExporter
{
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<Asset> listAssets;
    
    public AssetExcelExporter(final List<Asset> listAssets) {
        this.listAssets = listAssets;
        this.workbook = new XSSFWorkbook();
    }
    
    private void writeHeaderLine() {
        this.sheet = this.workbook.createSheet("Asset");
        final Row row = this.sheet.createRow(0);
        final CellStyle style = this.workbook.createCellStyle();
        final XSSFFont font = this.workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16.0);
        style.setFont(font);
        this.createCell(row, 0, "User ID", style);
        this.createCell(row, 1, "E-mail", style);
        this.createCell(row, 2, "Full Name", style);
        this.createCell(row, 3, "Roles", style);
        this.createCell(row, 4, "Enabled", style);
    }
    
    private void createCell(final Row row, final int columnCount, final Object value, final CellStyle style) {
        this.sheet.autoSizeColumn(columnCount);
        final Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((int)value);
        }
        else if (value instanceof Boolean) {
            cell.setCellValue((boolean)value);
        }
        else {
            cell.setCellValue((String)value);
        }
        cell.setCellStyle(style);
    }
    
    private void writeDataLines() {
        int rowCount = 1;
        final CellStyle style = this.workbook.createCellStyle();
        final XSSFFont font = this.workbook.createFont();
        font.setFontHeight(14.0);
        style.setFont(font);
        for (final Asset asset : this.listAssets) {
            final Row row = this.sheet.createRow(rowCount++);
            int columnCount = 0;
            this.createCell(row, columnCount++, asset.getDescription(), style);
            this.createCell(row, columnCount++, asset.getCategory(), style);
            this.createCell(row, columnCount++, asset.getType(), style);
            this.createCell(row, columnCount++, asset.getAssetId(), style);
            this.createCell(row, columnCount++, asset.getSerialNumber(), style);
            this.createCell(row, columnCount++, asset.getDateReceived(), style);
            this.createCell(row, columnCount++, asset.getFunder(), style);
            this.createCell(row, columnCount++, asset.getModel(), style);
            this.createCell(row, columnCount++, asset.getPurchasePrice(), style);
            this.createCell(row, columnCount++, asset.getStates(), style);
            this.createCell(row, columnCount++, asset.getYearOfPurchase(), style);
            this.createCell(row, columnCount++, asset.getImplementer(), style);
            this.createCell(row, columnCount++, asset.getImplementationPeriod(), style);
            this.createCell(row, columnCount++, asset.getLocation(), style);
            this.createCell(row, columnCount++, asset.getCustodian(), style);
            this.createCell(row, columnCount++, asset.getCondition(), style);
            this.createCell(row, columnCount++, asset.getEmailAddress(), style);
            this.createCell(row, columnCount++, asset.getPhone(), style);
            this.createCell(row, columnCount++, asset.getStatus(), style);
        }
    }
    
    public void export(final HttpServletResponse response) throws IOException {
        this.writeHeaderLine();
        this.writeDataLines();
        final ServletOutputStream outputStream = response.getOutputStream();
        this.workbook.write(outputStream);
        this.workbook.close();
        outputStream.close();
    }
}
