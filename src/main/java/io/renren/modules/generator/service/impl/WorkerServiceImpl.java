package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.WorkerDao;
import io.renren.modules.generator.entity.WorkerEntity;
import io.renren.modules.generator.service.WorkerService;


@Service("workerService")
public class WorkerServiceImpl extends ServiceImpl<WorkerDao, WorkerEntity> implements WorkerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorkerEntity> page = this.page(
                new Query<WorkerEntity>().getPage(params),
                new QueryWrapper<WorkerEntity>()
        );

        return new PageUtils(page);
    }

}