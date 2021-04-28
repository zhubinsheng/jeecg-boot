package org.jeecg.modules.typesofhousing.entity;

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
 * @Description: 房源类型
 * @Author: jeecg-boot
 * @Date:   2021-04-25
 * @Version: V1.0
 */
@Data
@TableName("typesofhousing")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="typesofhousing对象", description="房源类型")
public class Typesofhousing implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**关联区域*/
	@Excel(name = "关联区域", width = 15)
    @ApiModelProperty(value = "关联区域")
    private java.lang.String associatedArea;
	/**房源名称*/
	@Excel(name = "房源名称", width = 15)
    @ApiModelProperty(value = "房源名称")
    private java.lang.String typeName;
	/**房源信息*/
	@Excel(name = "房源信息", width = 15)
    @ApiModelProperty(value = "房源信息")
    private java.lang.String typeInfo;
}
