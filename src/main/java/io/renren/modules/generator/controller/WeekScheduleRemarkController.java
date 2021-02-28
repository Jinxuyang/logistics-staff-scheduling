package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.WeekScheduleRemarkEntity;
import io.renren.modules.generator.service.WeekScheduleRemarkService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Verge
 * @email 981340404@qq.com
 * @date 2021-02-28 19:29:23
 */
@RestController
@RequestMapping("generator/weekscheduleremark")
public class WeekScheduleRemarkController {
    @Autowired
    private WeekScheduleRemarkService weekScheduleRemarkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:weekscheduleremark:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = weekScheduleRemarkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:weekscheduleremark:info")
    public R info(@PathVariable("id") Integer id){
		WeekScheduleRemarkEntity weekScheduleRemark = weekScheduleRemarkService.getById(id);

        return R.ok().put("weekScheduleRemark", weekScheduleRemark);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:weekscheduleremark:save")
    public R save(@RequestBody WeekScheduleRemarkEntity weekScheduleRemark){
		weekScheduleRemarkService.save(weekScheduleRemark);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("generator:weekscheduleremark:update")
    public R update(@RequestParam String username,
                    @RequestParam int week,
                    @RequestParam String remark){
        WeekScheduleRemarkEntity weekScheduleRemarkEntity = new WeekScheduleRemarkEntity();
        weekScheduleRemarkEntity.setStart(1+(7*(week-1)));
        weekScheduleRemarkEntity.setEnd(week*7);
        weekScheduleRemarkEntity.setRemark(remark);
        weekScheduleRemarkEntity.setUsername(username);

        WeekScheduleRemarkEntity entity =weekScheduleRemarkService.loadUserByUsername(username);
		if (entity == null){
		    weekScheduleRemarkService.save(weekScheduleRemarkEntity);
        } else {
		    weekScheduleRemarkEntity.setId(entity.getId());
		    weekScheduleRemarkService.updateById(weekScheduleRemarkEntity);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:weekscheduleremark:delete")
    public R delete(@RequestBody Integer[] ids){
		weekScheduleRemarkService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
