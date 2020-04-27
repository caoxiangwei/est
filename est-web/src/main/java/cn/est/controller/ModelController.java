package cn.est.controller;

import cn.est.dto.Page;
import cn.est.dto.Result;
import cn.est.utils.ResultUtils;
import cn.est.dto.ModelDto;
import cn.est.req.ModelReq;
import cn.est.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 商品模型控制器
 * @Date 2019-08-15 14:45
 * @Author Liujx
 * Version 1.0
 **/
@Api(description = "商品模型控制器")
@RestController
@RequestMapping("/api/model")
public class ModelController {

    Logger log = LoggerFactory.getLogger(ModelController.class);
    
    @Autowired
    private ModelService modelService;

    /**
     * 商品型号详情
     * @param id
     * @return
     */
    @ApiOperation(value = "商品型号详情", produces = "application/json", notes = "根据id查询商品型号详情信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "商品型号id", required = true, dataType = "Long")
    @GetMapping("/{id}/detail")
    public Result<ModelDto> detail(@PathVariable Long id) {
        log.info("根据id查询商品模型详情id:{}" ,id);
        ModelDto data = modelService.getById(id);
        return ResultUtils.returnDataSuccess(data);
    }

    /**
     * 商品列表搜索
     * @param modelReq
     * @return
     */
    @ApiOperation(value = "商品列表搜索", produces = "application/json", notes = "分页查询商品型号列表数据")
    @GetMapping("/search")
    public Result<Page<ModelDto>> list(ModelReq modelReq) {
        log.info("商品列表搜索");
        if(modelReq.getPageNo() == null){
            modelReq.setPageNo(1);
        }
        if(modelReq.getPageSize() == null){
            modelReq.setPageSize(20);
        }
        Page<ModelDto> page = modelService.getModelPage(modelReq);
        return ResultUtils.returnDataSuccess(page);
    }


}
