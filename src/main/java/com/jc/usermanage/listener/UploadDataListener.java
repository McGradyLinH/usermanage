package com.jc.usermanage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jc.usermanage.domain.*;
import com.jc.usermanage.service.TbCompanyService;
import com.jc.usermanage.service.TbUserService;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LinXing
 * @version 1.0
 * @desc 该类不能交由spring管理
 * @date 2020/6/18 15:21
 */
public class UploadDataListener extends AnalysisEventListener<UploadData> {

    /**
     * 每隔10条上传一次
     */
    private static final int BATCH_COUNT = 10;
    private List<UploadData> list = new ArrayList<>();
    private TbUserService userService;
    private TbCompanyService companyService;

    public UploadDataListener(TbUserService userService, TbCompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @Override
    public void invoke(UploadData data, AnalysisContext context) {
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            list.forEach(System.err::println);
            save();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        save();
        list.clear();
    }

    private void save() {
//        暂时没有时间，在职的以当前时间往后三年为期
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = now.plusYears(3L);
        UploadData data;
        Status status;
        UserInfo userInfo;
        TbCompany tbCompany;
        for (int i = 0; i < list.size(); i++) {
            data = list.get(i);
            Account account = new Account();

            userInfo = new UserInfo();
            userInfo.setPhone(data.getPhone());
            userInfo.setUser_pwd(DigestUtils.md5DigestAsHex("123456".getBytes()));
            userInfo.setName(data.getName());
            account.setInfo(userInfo);
            status = new Status();
            status.setStart_time(now);
            status.setEnd_time(endTime);
            if ("1".equals(data.getStatus())) {
                status.setStatus("在职");
                if ("null".equals(data.getCompany1()) || "#N/A".equals(data.getCompany1())) {
                    if ("null".equals(data.getCompany2()) || "#N/A".equals(data.getCompany2())) {
                        status.setStatus("离职");
                    } else {
                        tbCompany = companyService.queryByName(data.getCompany2());
                        account.setTbCompany(tbCompany);
                    }
                } else {
                    tbCompany = companyService.queryByName(data.getCompany1());
                    account.setTbCompany(tbCompany);
                }
            } else {
                tbCompany = null;
                account.setTbCompany(tbCompany);
                status.setStatus("离职");
            }
            account.setStatus(status);
            userService.insertapart(account);
        }

    }
}
