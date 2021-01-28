package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.SchedulingEntity;

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
}

