package org.jeecg.modules.ContractListManage.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 合同管理
 * @Author: jeecg-boot
 * @Date:   2021-05-07
 * @Version: V1.0
 */
@Data
@TableName("contract_list_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="contract_list_manage对象", description="合同管理")
public class ContractListManage implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人(即审核人)*/
    @ApiModelProperty(value = "更新人(即审核人)")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
    private String contractNumber;
	/**合同存放地址*/
	@Excel(name = "合同存放地址", width = 15)
    @ApiModelProperty(value = "合同存放地址")
    private String contractAdrdess;
	/**合同状态（待审核-合同关闭）*/
	@Excel(name = "合同状态（待审核-合同关闭）", width = 15)
    @ApiModelProperty(value = "合同状态（待审核-合同关闭）")
    private String contractStatus;
	/**关联租户id*/
	@Excel(name = "关联租户id", width = 15)
    @ApiModelProperty(value = "关联租户id")
    private String userId;
	/**关联房屋id*/
	@Excel(name = "关联房屋id", width = 15)
    @ApiModelProperty(value = "关联房屋id")
    private String houseId;
	/**入住时长*/
	@Excel(name = "入住时长", width = 15)
    @ApiModelProperty(value = "入住时长")
    private String contractTime;
	/**关联优惠类型*/
	@Excel(name = "关联优惠类型", width = 15)
    @ApiModelProperty(value = "关联优惠类型")
    private String discountId;
	/**是否有优惠*/
	@Excel(name = "是否有优惠", width = 15)
    @ApiModelProperty(value = "是否有优惠")
    private String hasDiscount;
	/**优惠时长*/
	@Excel(name = "优惠时长", width = 15)
    @ApiModelProperty(value = "优惠时长")
    private String discountTime;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String notes;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
    private String signTime;
	/**到期时间*/
	@Excel(name = "到期时间", width = 15)
    @ApiModelProperty(value = "到期时间")
    private String expireTime;
	/**应收款*/
	@Excel(name = "应收款", width = 15)
    @ApiModelProperty(value = "应收款")
    private String receivables;
	/**已收款*/
	@Excel(name = "已收款", width = 15)
    @ApiModelProperty(value = "已收款")
    private String received;
	/**账单总期数*/
	@Excel(name = "账单总期数", width = 15)
    @ApiModelProperty(value = "账单总期数")
    private String totalNumberOfBillingPeriods;
	/**已收期数*/
	@Excel(name = "已收期数", width = 15)
    @ApiModelProperty(value = "已收期数")
    private String numberOfPeriodsReceived;
	/**每期应收款*/
	@Excel(name = "每期应收款", width = 15)
    @ApiModelProperty(value = "每期应收款")
    private String receivablesInEachPeriod;
	/**每期实收款*/
	@Excel(name = "每期实收款", width = 15)
    @ApiModelProperty(value = "每期实收款")
    private String actualCollectionOfEachPeriod;
}
