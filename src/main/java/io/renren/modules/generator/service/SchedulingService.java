package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.controller.viewobject.WeekScheduleViewObject;
import io.renren.modules.generator.entity.SchedulingEntity;
import io.renren.modules.generator.entity.viewentity.UserVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Verge
 * @email 981340404@qq.com
 * @date 2021-01-27 15:54:31
 */
public interface SchedulingService extends IService<SchedulingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SchedulingEntity> getListByUsername(String username);

    List<UserVo> getSameStatusUsers(String username, int day);

    List<WeekScheduleViewObject> getWeekSchedule(String key,String category,int start,int end);


}

