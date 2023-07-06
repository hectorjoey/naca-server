package fhi360.it.assetverify.serviceImpl;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
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

//    @Override
//    public Page<IssueLog> searchByDate(LocalDate startDate, LocalDate endDate, PageRequest of) {
//        return (startDate, endDate, of);
//    }


    @Override
    public Page<IssueLog> searchByDate(String startDate, String endDate, Pageable pageable) {
        return issueLogRepository.findByDateBetween(startDate, endDate, pageable);
    }

    public ByteArrayOutputStream exportToCSV(String startDate, String endDate) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        List<IssueLog> results = issueLogRepository.findByDateBetween(startDate, endDate);
        // Convert the results to CSV format
        String csvData = "";
        // Convert the list to CSV format

        CustomMappingStrategy<IssueLogEntry> mappingStrategy = new CustomMappingStrategy<>();
        mappingStrategy.setType(IssueLogEntry.class);
        StatefulBeanToCsv<IssueLogEntry> sbc = null;
        Path f = Files.createTempFile("Issue", ".csv");
        try {

            sbc = new StatefulBeanToCsvBuilder<IssueLogEntry>(new OutputStreamWriter(
                    Files.newOutputStream(f)))
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withMappingStrategy(mappingStrategy)
                    .build();

            List<IssueLogEntry> entries = results.stream()
                    .map(log -> {
                        IssueLogEntry entry = new IssueLogEntry();
                        BeanUtils.copyProperties(log, entry);
                        return entry;
                    })
                    .collect(Collectors.toList());
            sbc.write(entries);
            IOUtils.copy(new FileInputStream(f.toFile()), baos);

        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }

        return baos;
    }


    public static class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {

        @Override

        public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {

            final int numColumns = getFieldMap().values().size();

            super.generateHeader(bean);


            String[] header = new String[numColumns];


            BeanField<?, ?> beanField;

            for (int i = 0; i < numColumns; i++) {

                beanField = findField(i);

                String columnHeaderName = extractHeaderName(beanField);

                header[i] = columnHeaderName;

            }

            return header;

        }


        private String extractHeaderName(final BeanField<?, ?> beanField) {

            if (beanField == null || beanField.getField() == null || beanField.getField().getDeclaredAnnotationsByType(

                    CsvBindByName.class).length == 0) {

                return StringUtils.EMPTY;

            }


            final CsvBindByName bindByNameAnnotation = beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class)[0];

            return bindByNameAnnotation.column();

        }
    }
}