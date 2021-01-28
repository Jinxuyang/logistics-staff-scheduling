package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.WorkerEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author Verge
 * @email 981340404@qq.com
 * @date 2021-01-27 13:08:10
 */
@Service
public interface WorkerService extends IService<WorkerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

