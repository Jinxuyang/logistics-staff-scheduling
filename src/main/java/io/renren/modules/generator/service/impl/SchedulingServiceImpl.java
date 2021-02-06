package io.renren.modules.generator.service.impl;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.SchedulingDao;
import io.renren.modules.generator.entity.SchedulingEntity;
import io.renren.modules.generator.service.SchedulingService;


@Service("schedulingService")
public class SchedulingServiceImpl extends ServiceImpl<SchedulingDao, SchedulingEntity> implements SchedulingService {
    @Autowired
    private SchedulingDao schedulingDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SchedulingEntity> page = this.page(
                new Query<SchedulingEntity>().getPage(params),
                new QueryWrapper<SchedulingEntity>().like("status",params.get("key"))
                .or().eq("user_id",params.get("key"))
                .or().eq("date",params.get("key"))

        );

        return new PageUtils(page);
    }

    @Override
    public List<SchedulingEntity> getListByUserID(int id) {
        QueryWrapper<SchedulingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        return schedulingDao.selectList(queryWrapper);
    }
}