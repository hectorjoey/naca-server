
package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.dto.InventoryDto;
import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.IssueLog;
import fhi360.it.assetverify.model.StockStatusReport;
import fhi360.it.assetverify.repository.BinCardRepository;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class InventoryController {
    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;


    @GetMapping("all-inventories")
    List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }


    @GetMapping("all-invent")
    List<Inventory> getInventory() {
        return inventoryService.getInventories();
    }

    //get all inventory
    @GetMapping("inventories")
    public Page<Inventory> getAllInventories(Pageable pageable) {
        return inventoryService.getHealthCommodities(pageable);
    }

    @PostMapping("inventory")
    ResponseEntity<Object> createInventory(@RequestBody Inventory inventory) {
        return new ResponseEntity<>(inventoryService.addInventory(inventory), HttpStatus.CREATED);
    }

    //get inventory by Id
    @GetMapping("invent/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
        return ResponseEntity.ok().body(inventory);
    }

    // For searching inventory
    @GetMapping("inventory/{keyword}")
    public Page<Inventory> getInventories(Pageable pageable, @PathVariable("keyword") String keyword) {
        return inventoryRepository.findAll(pageable, keyword);
    }

    @PutMapping("inventories/{id}")
    public Inventory updateInventory(@PathVariable("id") Long id, @Valid @RequestBody InventoryDto inventoryDto) throws ResourceNotFoundException {
        System.out.println("Update inventory with ID = " + id + "...");
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
        inventory.setOpeningBalance(inventoryDto.getOpeningBalance());
        inventory.setClosingStock(inventoryDto.getClosingStock());
        final Inventory updatedInventory = inventoryRepository.save(inventory);
        System.out.println("Updated Inventory " + updatedInventory);
        return inventoryRepository.save(updatedInventory);
    }


    @PatchMapping("inventorys/{id}")
    public Inventory updateInventorys(@PathVariable("id") Long id, @Valid @RequestBody InventoryDto inventoryDto) throws ResourceNotFoundException {
        System.out.println("Update inventory with ID = " + id + "...");
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
        inventory.setQ1(inventoryDto.getQ1());
        inventory.setQ2(inventoryDto.getQ2());
        inventory.setQ3(inventoryDto.getQ3());
        int totalQ = Integer.parseInt(inventory.getQ1()) + Integer.parseInt(inventory.getQ2()) + Integer.parseInt(inventory.getQ3());
        System.out.println("totalQ ::: " + totalQ);
        int calculateAnc = totalQ / 3;

//        int calculateAnc;
        double roundedUpAnc = Math.ceil(calculateAnc);
        System.out.println("calculateAnc ::: " + calculateAnc);
        inventory.setAnc(String.valueOf(roundedUpAnc));
        System.out.println("rounde ;; ::: " + roundedUpAnc);

        int calculateStockOnHand = Integer.parseInt(inventory.getStockOnHand());
        int calculateMos = calculateStockOnHand / calculateAnc;
        System.out.println("stock onhand ::: " + calculateStockOnHand);
        inventory.setMos(String.valueOf(calculateMos));
        System.out.println("MOs::: " + inventory.getMos());
        System.out.println("Anc::: " + inventory.getAnc());

        final Inventory updatedInventory = inventoryRepository.save(inventory);
        System.out.println("Updated Inventory " + updatedInventory);
        return inventoryRepository.save(updatedInventory);
    }


    @PatchMapping("invent/{id}")
    public Inventory updateAnInventory(@PathVariable("id") Long id, @Valid @RequestBody InventoryDto inventoryDto) throws ResourceNotFoundException {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
//        inventory.setStockBalance(inventoryDto.getStockBalance());
//        inventory.setClosingStock(inventoryDto.getClosingStock());
        inventory.setLosses(inventoryDto.getLosses());
        inventory.setPositiveAdjustment(inventoryDto.getPositiveAdjustment());
        inventory.setNegativeAdjustment(inventoryDto.getNegativeAdjustment());

        final Inventory updatedInventory = inventoryRepository.save(inventory);
        System.out.println("Updated Inventory" + updatedInventory);
//        assetEmailsService.sendEmailWithAttachment(asset1);
        return inventoryRepository.save(updatedInventory);

    }

    @DeleteMapping("inventory/{id}")
    public Map<String, Boolean> deleteInventory(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
        inventoryRepository.delete(inventory);
//        deleteAssetService.deleteAssetEmail(asset);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @GetMapping("export")
    public void exportToCSV(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, HttpServletResponse response) throws IOException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate end = LocalDate.parse(endDate, dateTimeFormatter);

        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = start.format(desiredFormat);
        String formattedEndDate = end.format(desiredFormat);

        List<Inventory> inventories = inventoryRepository.findByDateReceivedBetween(formattedStartDate, formattedEndDate);

        // Create a StringBuilder to store the CSV content
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Warehouse Name,Date Received, Item Description, Batch Number, Expiry date, Shelf Life (Months), Stock Balance, Month Of Stock, Donor \n"); // Replace with actual column names

        // Append each IssueLog entry as a CSV row
        for (Inventory inventory : inventories) {
            csvContent.append(inventory.getWarehouseName()).append(",");
            csvContent.append(inventory.getDateReceived()).append(",");
            csvContent.append(inventory.getItemDescription()).append(",");
            csvContent.append(inventory.getBatchNo()).append(",");
            csvContent.append(inventory.getExpiryDate()).append(",");
            csvContent.append(inventory.getShellLife()).append(",");
            csvContent.append(inventory.getStockBalance()).append(",");
            csvContent.append(inventory.getMos()).append(",");
            csvContent.append(inventory.getDonor()).append("\n");
        }

        // Set the response headers for CSV file download
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=export.csv");

        // Write the CSV content to the response output stream
        try (PrintWriter writer = response.getWriter()) {
            writer.write(csvContent.toString());
        }
    }



    @GetMapping("searches")
    public Page<Inventory> search(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("page") int page) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, inputFormat);
        LocalDate end = LocalDate.parse(endDate, inputFormat);

        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = start.format(desiredFormat);
        String formattedEndDate = end.format(desiredFormat);
        return inventoryService.searchByDate(formattedStartDate, formattedEndDate, PageRequest.of(page - 1, 10));
    }


}
