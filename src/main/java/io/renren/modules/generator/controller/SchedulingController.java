package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.generator.entity.SchedulingEntity;
import io.renren.modules.generator.service.SchedulingService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Verge
 * @email 981340404@qq.com
 * @date 2021-01-27 15:54:31
 */
@RestController
@RequestMapping("generator/scheduling")
public class SchedulingController {
    @Autowired
    private SchedulingService schedulingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:scheduling:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = schedulingService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/listByUserID/{id}")
    public R listByUserID(@PathVariable int id){
        List<SchedulingEntity> list = schedulingService.getListByUserID(id);
        return R.ok().put("data",list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:scheduling:info")
    public R info(@PathVariable("id") Integer id){
		SchedulingEntity scheduling = schedulingService.getById(id);

        return R.ok().put("scheduling", scheduling);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:scheduling:save")
    public R save(@RequestBody SchedulingEntity scheduling){
		schedulingService.save(scheduling);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:scheduling:update")
    public R update(@RequestBody SchedulingEntity scheduling){
		schedulingService.updateById(scheduling);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:scheduling:delete")
    public R delete(@RequestBody Integer[] ids){
		schedulingService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
