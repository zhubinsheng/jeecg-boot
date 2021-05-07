
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.jeecg.common.util.DateUtils.formatTime;


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
    /**
     * 简单任务
     *
     * @param params
     * @return
     */

    @XxlJob(value = "testJob")
    public ReturnT<String> demoJobHandler(String params) throws ClientException {
        log.info("demo_testJob...............................");
        List<ContractListManage> contractListManageList = contractListManageService.list();
        log.info("contractListManageListSize>>>>"+contractListManageList.size());

        contractListManageList.forEach((contractListManage) -> {
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
            try {
                sendSucc = DySmsHelper.sendSms(mobile, obj, DySmsEnum.REGISTER_TEMPLATE_CODE);
            } catch (ClientException e) {
                e.printStackTrace();
            }finally {
                if (!sendSucc){
                }else {

                }
            }
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