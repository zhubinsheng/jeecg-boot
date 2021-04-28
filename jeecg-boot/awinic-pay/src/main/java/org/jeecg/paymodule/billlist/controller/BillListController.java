package org.jeecg.paymodule.billlist.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.paymodule.billlist.service.IBillListService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.paymodule.billlist.entity.BillList;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 账单
 * @Author: jeecg-boot
 * @Date:   2021-04-27
 * @Version: V1.0
 */
@Api(tags="账单")
@RestController
@RequestMapping("/bill")
@Slf4j
public class BillListController extends JeecgController<BillList, IBillListService> {
	@Autowired
	private IBillListService billListService;
	
	/**
	 * 分页列表查询
	 *
	 * @param billList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "账单-分页列表查询")
	@ApiOperation(value="账单-分页列表查询", notes="账单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BillList billList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BillList> queryWrapper = QueryGenerator.initQueryWrapper(billList, req.getParameterMap());
		Page<BillList> page = new Page<BillList>(pageNo, pageSize);
		IPage<BillList> pageList = billListService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param billList
	 * @return
	 */
	@AutoLog(value = "账单-添加")
	@ApiOperation(value="账单-添加", notes="账单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BillList billList) {
		billListService.save(billList);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param billList
	 * @return
	 */
	@AutoLog(value = "账单-编辑")
	@ApiOperation(value="账单-编辑", notes="账单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BillList billList) {
		billListService.updateById(billList);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账单-通过id删除")
	@ApiOperation(value="账单-通过id删除", notes="账单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		billListService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "账单-批量删除")
	@ApiOperation(value="账单-批量删除", notes="账单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.billListService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账单-通过id查询")
	@ApiOperation(value="账单-通过id查询", notes="账单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BillList billList = billListService.getById(id);
		if(billList==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(billList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param billList
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BillList billList) {
        return super.exportXls(request, billList, BillList.class, "账单");
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
        return super.importExcel(request, response, BillList.class);
    }

}
