
package org.jeecg.modules.demo.xxljob;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkcoding.http.util.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.DySmsEnum;
import org.jeecg.common.util.DySmsHelper;
import org.jeecg.modules.ContractListManage.entity.ContractListManage;
import org.jeecg.modules.ContractListManage.service.IContractListManageService;
import org.jeecg.modules.billlist.entity.BillList;
import org.jeecg.modules.billlist.service.IBillListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static me.zhyd.oauth.utils.GlobalAuthUtils.getTimestamp;
import static org.jeecg.common.util.DateUtils.*;


/**
 * xxl-job定时任务测试
 */
@Component
@Slf4j
public class TestJobHandler {
    @Autowired
    private IContractListManageService contractListManageService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IBillListService billListService;
    /**
     * 简单任务
     *
     */

    public static String getTimesMonthnightFisrt(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_MONTH,16);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return String.valueOf((cal.getTimeInMillis()/1000));

    }
    public static String getTimesMonthnight(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_MONTH,26);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return String.valueOf((cal.getTimeInMillis()/1000));
    }

    @XxlJob(value = "testJob")
    public ReturnT<String> demoJobHandler(String params) throws ClientException {
        log.info("demo_testJob...............................");
        List<ContractListManage> contractListManageList = contractListManageService.list();
        log.info("contractListManageListSize>>>>"+contractListManageList.size());

        contractListManageList.forEach((contractListManage) -> {
            if (!contractListManage.getTotalNumberOfBillingPeriods().equals(contractListManage.getNumberOfPeriodsReceived())){

            }

            //未达到总期数，但可能存在逾期状态
            BillList billList = new BillList();
            billList.setContractId(contractListManage.getId());
            billList.setBillDay(getTimesMonthnightFisrt());
            billList.setCreateBy("TestJobHandler");
            billList.setCreateTime(getDate());
            billList.setLatestDueDate(getTimesMonthnight());


            log.info("contractListManage.getUserId()>>>>"+contractListManage.getUserId());
            LoginUser loginUser = sysBaseAPI.getUserByName(contractListManage.getUserId());
            if (loginUser==null){
                log.info("通过用户名获取当前登录用户信息 <><><> null");
            }
            String mobile = loginUser.getPhone();
            log.info("mobile>>>>"+mobile);
            if (StringUtil.isEmpty(mobile)){
                mobile = "18709864407";
            }
            /*JSONObject obj = new JSONObject();
            String captcha = RandomUtil.randomNumbers(6);
            obj.put("name", "朱斌生");
            obj.put("year", "2020");
            obj.put("month", "5");
            obj.put("help_phone", "15006007008");
            obj.put("time", formatTime());
            name-其他；year-时间；month-时间；help_phone-电话号码；time-时间；*/
            String captcha = RandomUtil.randomNumbers(6);
            JSONObject obj = new JSONObject();
            obj.put("code", captcha);
            boolean sendSucc = false;
//            try {
//                sendSucc = DySmsHelper.sendSms(mobile, obj, DySmsEnum.REGISTER_TEMPLATE_CODE);
//            } catch (ClientException e) {
//                e.printStackTrace();
//            }finally {
//                if (!sendSucc){
//                }else {
//
//                }
//            }

            billList.setPrice("1700");

            billList.setUserId(loginUser.getId());
            billListService.save(billList);
            log.info("我执行完了...............................");
        });

        return ReturnT.SUCCESS;
    }

    public void init() {
        log.info("init");
    }

    public void destroy() {
        log.info("destory");
    }

}