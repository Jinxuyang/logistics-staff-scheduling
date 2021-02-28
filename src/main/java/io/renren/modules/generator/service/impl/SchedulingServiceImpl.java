package io.renren.modules.generator.service.impl;

import cn.hutool.core.date.Week;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.generator.controller.viewobject.WeekScheduleViewObject;
import io.renren.modules.generator.dao.SchedulingDao;
import io.renren.modules.generator.dao.WeekScheduleRemarkDao;
import io.renren.modules.generator.entity.SchedulingEntity;
import io.renren.modules.generator.entity.WeekScheduleRemarkEntity;
import io.renren.modules.generator.entity.viewentity.UserVo;
import io.renren.modules.generator.service.SchedulingService;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("schedulingService")
public class SchedulingServiceImpl extends ServiceImpl<SchedulingDao, SchedulingEntity> implements SchedulingService {
    @Autowired
    private SchedulingDao schedulingDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private WeekScheduleRemarkDao weekScheduleRemarkDao;

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
    public List<SchedulingEntity> getListByUsername(String username) {
        QueryWrapper<SchedulingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return schedulingDao.selectList(queryWrapper);
    }

    @Override
    public List<UserVo> getSameStatusUsers(int userId, int day) {
        QueryWrapper<SchedulingEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId)
                .eq("date",day);
        SchedulingEntity schedulingEntity = schedulingDao.selectOne(queryWrapper);
        queryWrapper.clear();
        queryWrapper.eq("status",schedulingEntity.getStatus())
                .eq("date",day);
        List<SchedulingEntity> schedulingEntityList = schedulingDao.selectList(queryWrapper);

        List<UserVo> userVoList = new ArrayList<>();
        QueryWrapper<SysUserEntity> sysUserEntityQueryWrapper = new QueryWrapper<>();
        for (SchedulingEntity entity : schedulingEntityList) {
            sysUserEntityQueryWrapper.eq("username",entity.getUsername());
            UserVo userVo = new UserVo();
            SysUserEntity sysUserEntity = sysUserDao.selectOne(sysUserEntityQueryWrapper);
            if (sysUserEntity!=null) {
                BeanUtils.copyProperties(sysUserEntity,userVo);
                userVoList.add(userVo);
            }
            sysUserEntityQueryWrapper.clear();
        }
        return userVoList;
    }

    @Override
    public List<WeekScheduleViewObject> getWeekSchedule(String key, String category,int start,int end) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("category",category);
        List<SysUserEntity> sysUserEntityList = sysUserDao.selectList(queryWrapper);
        List<WeekScheduleViewObject> weekScheduleViewObjectList = new ArrayList<>();
        for (SysUserEntity sysUserEntity : sysUserEntityList) {

            QueryWrapper<SchedulingEntity> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("username",sysUserEntity.getUsername())
            .between("date",start,end);
            List<SchedulingEntity> schedulingEntityList = schedulingDao.selectList(queryWrapper1);

            WeekScheduleViewObject weekScheduleViewObject = new WeekScheduleViewObject();
            weekScheduleViewObject.setUsername(sysUserEntity.getUsername());
            weekScheduleViewObject.setCategory(sysUserEntity.getCategory());


            List<String> stringList = new ArrayList<>();
            int day = 0,night = 0,rest = 0;
            for (SchedulingEntity schedulingEntity : schedulingEntityList) {
                String status = schedulingEntity.getStatus();
                if (status.equals("白班")) day++;
                else if (status.equals("夜班")) night++;
                else rest++;
                stringList.add(status);
            }
            weekScheduleViewObject.setDay(day);
            weekScheduleViewObject.setNight(night);
            weekScheduleViewObject.setRest(rest);
            weekScheduleViewObject.setDate(stringList);

            QueryWrapper<WeekScheduleRemarkEntity> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("username",sysUserEntity.getUsername())
                    .eq("start",start)
                    .eq("end",end);
            WeekScheduleRemarkEntity weekScheduleRemarkEntity = weekScheduleRemarkDao.selectOne(queryWrapper2);
            if (weekScheduleRemarkEntity != null)weekScheduleViewObject.setRemark(weekScheduleRemarkEntity.getRemark());

            weekScheduleViewObjectList.add(weekScheduleViewObject);
        }
        return weekScheduleViewObjectList;

    }
}