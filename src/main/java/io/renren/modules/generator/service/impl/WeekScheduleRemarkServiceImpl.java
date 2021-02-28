package io.renren.modules.generator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.WeekScheduleRemarkDao;
import io.renren.modules.generator.entity.WeekScheduleRemarkEntity;
import io.renren.modules.generator.service.WeekScheduleRemarkService;


@Service("weekScheduleRemarkService")
public class WeekScheduleRemarkServiceImpl extends ServiceImpl<WeekScheduleRemarkDao, WeekScheduleRemarkEntity> implements WeekScheduleRemarkService {

    @Autowired
    private WeekScheduleRemarkDao weekScheduleRemarkDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WeekScheduleRemarkEntity> page = this.page(
                new Query<WeekScheduleRemarkEntity>().getPage(params),
                new QueryWrapper<WeekScheduleRemarkEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public WeekScheduleRemarkEntity loadUserByUsername(String username) {
        QueryWrapper<WeekScheduleRemarkEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return weekScheduleRemarkDao.selectOne(queryWrapper);
    }
}