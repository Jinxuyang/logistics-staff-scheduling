package io.renren;

import io.renren.RenrenApplication;
import io.renren.modules.sys.controller.SysLoginController;
import io.renren.modules.sys.controller.SysUserController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.SysLoginForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Verge
 * @Date 2021/2/6 16:48
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddUser {
    @Test
    public void test() throws IOException {
        // 登录
        /*SysLoginController sysLoginController = new SysLoginController();
        SysLoginForm sysLoginForm = new SysLoginForm();
        sysLoginForm.setUsername("admin");
        sysLoginForm.setCaptcha("111111");
        sysLoginForm.setPassword("admin");
        sysLoginForm.setUuid("dfad158a-3634-4131-8b5d-df1c65d59798");
        sysLoginController.login(sysLoginForm);*/

        SysUserController sysUserController = new SysUserController();
        SysUserEntity sysUserEntity = new SysUserEntity();
        //sysUserEntity.setEmail("test@163.com");
        sysUserEntity.setMobile("16312346789");
        sysUserEntity.setPassword("staff");
        sysUserEntity.setSalt("");
        sysUserEntity.setStatus(1);
        sysUserEntity.setCreateUserId(1L);
        List<Long> list = new ArrayList<>();
        list.add(1L);
        sysUserEntity.setRoleIdList(list);
        for (int i = 3; i <= 3 ; i++) {
            sysUserEntity.setUsername("staff"+i);
            System.out.println(sysUserEntity);
            sysUserController.save(sysUserEntity);
        }
        //sysUserController.save();
    }
}
