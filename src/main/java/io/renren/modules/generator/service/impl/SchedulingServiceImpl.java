package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.generator.dao.SchedulingDao;
import io.renren.modules.generator.entity.SchedulingEntity;
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
            sysUserEntityQueryWrapper.eq("username","staff"+entity.getUserId());
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
}