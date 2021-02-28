package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.WeekScheduleRemarkEntity;

import java.util.Map;

/**
 * 
 *
 * @author Verge
 * @email 981340404@qq.com
 * @date 2021-02-28 19:29:23
 */
public interface WeekScheduleRemarkService extends IService<WeekScheduleRemarkEntity> {

    PageUtils queryPage(Map<String, Object> params);

    WeekScheduleRemarkEntity loadUserByUsername(String username);
}

