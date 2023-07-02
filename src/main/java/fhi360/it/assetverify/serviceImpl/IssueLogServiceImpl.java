package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.IssueLog;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.repository.IssueLogRepository;
import fhi360.it.assetverify.service.IssueLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IssueLogServiceImpl implements IssueLogService {

    private final InventoryRepository inventoryRepository;
    private final IssueLogRepository issueLogRepository;


    @Override
    public IssueLog addIssueLog(@RequestBody IssueLog issueLog) {

        System.out.println(issueLog.getBatchNo());
        return  issueLogRepository.save(issueLog);
    }

    @Override
    public List<IssueLog> getAllIssueLogs() {
        return issueLogRepository.findAll();
    }
}
