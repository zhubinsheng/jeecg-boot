package org.jeecg.modules.room.entity;

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
 * @Description: 房间表
 * @Author: jeecg-boot
 * @Date:   2021-04-25
 * @Version: V1.0
 */
@Data
@TableName("room")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="room对象", description="房间表")
public class Room implements Serializable {
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
	/**房间号*/
	@Excel(name = "房间号", width = 15)
    @ApiModelProperty(value = "房间号")
    private String roomNumber;
	/**关联类型*/
	@Excel(name = "关联类型", width = 15)
    @ApiModelProperty(value = "关联类型")
    private String relationType;
	/**面积*/
	@Excel(name = "面积", width = 15)
    @ApiModelProperty(value = "面积")
    private String area;
	/**价格*/
	@Excel(name = "价格", width = 15)
    @ApiModelProperty(value = "价格")
    private String price;
	/**具体信息*/
	@Excel(name = "具体信息", width = 15)
    @ApiModelProperty(value = "具体信息")
    private String specificInfo;
}
