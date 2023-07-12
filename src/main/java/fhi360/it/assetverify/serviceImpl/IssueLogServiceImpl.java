package fhi360.it.assetverify.serviceImpl;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.IssueLog;
import fhi360.it.assetverify.model.IssueLogEntry;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.repository.IssueLogRepository;
import fhi360.it.assetverify.service.IssueLogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class IssueLogServiceImpl implements IssueLogService {

    private final InventoryRepository inventoryRepository;
    private final IssueLogRepository issueLogRepository;


    @Override
    public IssueLog addIssueLog(@RequestBody IssueLog issueLog) {

        System.out.println(issueLog.getBatchNo());
        return issueLogRepository.save(issueLog);
    }

    @Override
    public List<IssueLog> getAllIssueLogs() {
        return issueLogRepository.findAll();
    }

    @Override
    public Page<IssueLog> getAllIssueLogs(Pageable pageable) {
        Page<IssueLog> result = issueLogRepository.findByOrderByIdAsc(pageable);
        for (IssueLog value : result) {
            value.setTotal(calcTotal());
        }
        return result;
    }

    private String calcTotal() {
        List<IssueLog> issueLogs = issueLogRepository.findAll();
        int total = 0;

        for (IssueLog issueLog : issueLogs) {
            String quantityIssued = issueLog.getQuantityIssued();
            if (quantityIssued != null) {
                total += Integer.parseInt(quantityIssued);
            }
        }
        System.out.println(total);
        return String.valueOf(total);
    }




    @Override
    public Page<IssueLog> searchByDate(String startDate, String endDate, Pageable pageable) {
        return issueLogRepository.findByDateBetween(startDate, endDate, pageable);
    }
}