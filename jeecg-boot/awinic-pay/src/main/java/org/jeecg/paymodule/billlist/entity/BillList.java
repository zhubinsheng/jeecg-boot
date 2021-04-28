package org.jeecg.paymodule.billlist.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date:   2021-04-27
 * @Version: V1.0
 */
@Data
@TableName("bill_list")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bill_list对象", description="账单")
public class BillList implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期（账单自动生成的日期）*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期（账单自动生成的日期）")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**对应租户id*/
	@Excel(name = "对应租户id", width = 15)
    @ApiModelProperty(value = "对应租户id")
    private String userId;
	/**对应合同id*/
	@Excel(name = "对应合同id", width = 15)
    @ApiModelProperty(value = "对应合同id")
    private String contractId;
	/**对应房屋类型*/
	@Excel(name = "对应房屋类型", width = 15)
    @ApiModelProperty(value = "对应房屋类型")
    private String typeId;
	/**对应房屋详情*/
	@Excel(name = "对应房屋详情", width = 15)
    @ApiModelProperty(value = "对应房屋详情")
    private String houseInfo;
	/**账单金额(应缴 - 优惠)*/
	@Excel(name = "账单金额(应缴 - 优惠)", width = 15)
    @ApiModelProperty(value = "账单金额(应缴 - 优惠)")
    private String price;
	/**账单应缴费用*/
	@Excel(name = "账单应缴费用", width = 15)
    @ApiModelProperty(value = "账单应缴费用")
    private String payable;
	/**账单优惠金额*/
	@Excel(name = "账单优惠金额", width = 15)
    @ApiModelProperty(value = "账单优惠金额")
    private String freeprice;
	/**优惠类型*/
	@Excel(name = "优惠类型", width = 15)
    @ApiModelProperty(value = "优惠类型")
    private String discountType;
	/**账单日*/
	@Excel(name = "账单日", width = 15)
    @ApiModelProperty(value = "账单日")
    private String billDay;
	/**最迟应缴日期*/
	@Excel(name = "最迟应缴日期", width = 15)
    @ApiModelProperty(value = "最迟应缴日期")
    private String latestDueDate;
	/**账单状态*/
	@Excel(name = "账单状态", width = 15)
    @ApiModelProperty(value = "账单状态")
    private String status;
	/**账单商户编号*/
	@Excel(name = "账单商户编号", width = 15)
    @ApiModelProperty(value = "账单商户编号")
    private String billingMerchantNumber;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private String picture;
}
