package org.jeecg.modules.typesofhousing.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.typesofhousing.entity.Typesofhousing;
import org.jeecg.modules.typesofhousing.service.ITypesofhousingService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 房源类型
 * @Author: jeecg-boot
 * @Date:   2021-04-25
 * @Version: V1.0
 */
@Api(tags="房源类型")
@RestController
@RequestMapping("/typesofhousing/typesofhousing")
@Slf4j
public class TypesofhousingController extends JeecgController<Typesofhousing, ITypesofhousingService> {
	@Autowired
	private ITypesofhousingService typesofhousingService;
	
	/**
	 * 分页列表查询
	 *
	 * @param typesofhousing
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "房源类型-分页列表查询")
	@ApiOperation(value="房源类型-分页列表查询", notes="房源类型-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Typesofhousing typesofhousing,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Typesofhousing> queryWrapper = QueryGenerator.initQueryWrapper(typesofhousing, req.getParameterMap());
		Page<Typesofhousing> page = new Page<Typesofhousing>(pageNo, pageSize);
		IPage<Typesofhousing> pageList = typesofhousingService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param typesofhousing
	 * @return
	 */
	@AutoLog(value = "房源类型-添加")
	@ApiOperation(value="房源类型-添加", notes="房源类型-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Typesofhousing typesofhousing) {
		typesofhousingService.save(typesofhousing);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param typesofhousing
	 * @return
	 */
	@AutoLog(value = "房源类型-编辑")
	@ApiOperation(value="房源类型-编辑", notes="房源类型-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Typesofhousing typesofhousing) {
		typesofhousingService.updateById(typesofhousing);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "房源类型-通过id删除")
	@ApiOperation(value="房源类型-通过id删除", notes="房源类型-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		typesofhousingService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "房源类型-批量删除")
	@ApiOperation(value="房源类型-批量删除", notes="房源类型-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.typesofhousingService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "房源类型-通过id查询")
	@ApiOperation(value="房源类型-通过id查询", notes="房源类型-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Typesofhousing typesofhousing = typesofhousingService.getById(id);
		if(typesofhousing==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(typesofhousing);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param typesofhousing
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Typesofhousing typesofhousing) {
        return super.exportXls(request, typesofhousing, Typesofhousing.class, "房源类型");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Typesofhousing.class);
    }

}
