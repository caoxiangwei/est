package cn.est.controller;

import cn.est.dto.Result;
import cn.est.utils.ResultUtils;
import cn.est.dto.ClassifyDto;
import cn.est.service.ClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 首页控制器
 */
@Api(description = "首页控制器")
@RestController
@RequestMapping("/api/home")
public class HomeController {

    Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ClassifyService classifyService;

    /**
     * 首页商品分类树
     * @return
     */
    @ApiOperation(value = "首页商品分类树", produces = "application/json", notes = "首页商品分类树形结构查询")
    @GetMapping("/classifyTree")
    public Result<List<ClassifyDto>> list() {
        log.info("加载首页商品分类树");
        Result<List<ClassifyDto>> result = ResultUtils.returnFail();
        List<ClassifyDto> data = classifyService.getTrees();
        result = ResultUtils.returnDataSuccess(data);
        return result;
    }
}
