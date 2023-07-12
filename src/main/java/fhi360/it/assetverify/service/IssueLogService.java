package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.IssueLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public interface IssueLogService {


    IssueLog addIssueLog(IssueLog issueLog);

    List<IssueLog> getAllIssueLogs();


    Page<IssueLog> getAllIssueLogs(Pageable pageable);

    Page<IssueLog> searchByDate(String startDate, String endDate, Pageable pageable);

}
