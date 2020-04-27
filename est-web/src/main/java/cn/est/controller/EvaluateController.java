package cn.est.controller;

import cn.est.constants.Constants;
import cn.est.dto.Result;
import cn.est.constants.ResultEnum;
import cn.est.utils.ResultUtils;
import cn.est.utils.StringUtil;
import cn.est.dto.EvaluateDto;
import cn.est.dto.MalfunctionDto;
import cn.est.dto.ModelDto;
import cn.est.dto.UsersDto;
import cn.est.service.EvaluateService;
import cn.est.service.MalfunctionService;
import cn.est.service.ModelService;
import cn.est.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @Description 评估控制器
 * @Date 2019-08-16 14:31
 * @Author Liujx
 * Version 1.0
 **/
@Api(description = "评估控制器")
@RestController
@RequestMapping("/api/evaluate")
public class EvaluateController {

    Logger log = LoggerFactory.getLogger(EvaluateController.class);

    @Autowired
    private MalfunctionService malfunctionService;

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private UserService userService;

    @Autowired
    ModelService modelService;

    /**
     * 旧品维修评估维度
     *
     * @return
     */
    @ApiOperation(value = "旧品维修评估维度", produces = "application/json", notes = "旧品维修评估维度")
    @ApiImplicitParam(name = "modelId", value = "商品模型id", required = true, dataType = "String")
    @GetMapping("/specList")
    public Result<List<MalfunctionDto>> list(Long modelId) {
        log.info("加载旧品维修评估维度列表");
        Result<List<MalfunctionDto>> result = null;
        List<MalfunctionDto> data = malfunctionService.getMalfunctionList(modelId);
        result = ResultUtils.returnDataSuccess(data);
        return result;
    }


    /**
     * 维修估价
     *
     * @param modelId
     * @param optionIds
     * @return
     */
    @ApiOperation(value = "维修估价", produces = "application/json", notes = "根据用户选择的故障信息进行评估")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户token", required = true, dataType = "String")
            , @ApiImplicitParam(name = "modelId", value = "商品模型id", required = true, dataType = "String")
            , @ApiImplicitParam(name = "optionIds", value = "故障选项ids", required = true, dataType = "String")})
    @PostMapping("/assess")
    public Result<EvaluateDto> assess(Long modelId, String optionIds, HttpServletRequest request) {
        log.info("旧品维修估价");
        Result<EvaluateDto> result = null;
        String token = request.getHeader("token");
        UsersDto user = userService.getLoginUser(token);
        // 验证modelId是否存在
        if (!checkModel(modelId)) {
            log.info("商品型号错误modelId:{}", modelId);
            return ResultUtils.returnFail();
        }
        //  验证optionIds
        if (!checkOptionIds(optionIds)) {
            log.info("错误的商品问题选项optionIds:{}", optionIds);
            return ResultUtils.returnFail("错误的故障选项", ResultEnum.FAIL_PARAM.getCode());
        }
        EvaluateDto data = evaluateService.assess(modelId, optionIds, user.getId());
        result = ResultUtils.returnDataSuccess(data);
        return result;
    }

    /**
     * 维修估价详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "维修估价详情", produces = "application/json", notes = "根据id查询商品的评估详情信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "评估id", required = true, dataType = "Long")
    @GetMapping("/{id}/assessInfo")
    public Result<EvaluateDto> assess(@PathVariable Long id, HttpServletRequest request) {
        log.info("旧品维修估价");
        Result<EvaluateDto> result = null;
        EvaluateDto data = evaluateService.getById(id);
        if(data == null){
            result = ResultUtils.returnResult(ResultEnum.FAIL_HAVE_NOT_EXIST);
        }else {
            result = ResultUtils.returnDataSuccess(data);
        }
        return result;
    }

    /**
     * 验证问题选项ids
     *
     * @param optionIds
     * @return
     */
    private boolean checkOptionIds(String optionIds) {
        boolean flag = false;
        Set<Long> optionIdSet = StringUtil.string2LongSet(optionIds, Constants.Connnector.COMMA_);
        if (optionIdSet != null && !optionIdSet.isEmpty()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 校验商品类型
     *
     * @param modelId
     */
    private boolean checkModel(Long modelId) {
        boolean flag = false;
        ModelDto model = modelService.getById(modelId);
        if (model != null) {
            flag = true;
        }
        return flag;
    }

}
