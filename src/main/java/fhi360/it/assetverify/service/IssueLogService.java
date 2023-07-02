package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.BinCard;
import fhi360.it.assetverify.model.IssueLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueLogService {


    IssueLog addIssueLog(IssueLog issueLog);

    List<IssueLog> getAllIssueLogs();
}
