package io.renren;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import io.renren.RenrenApplication;
import io.renren.modules.generator.entity.SchedulingEntity;
import io.renren.modules.generator.service.SchedulingService;
import io.renren.modules.generator.service.WorkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author Verge
 * @Date 2021/1/26 15:10
 * @Version 1.0
 */
@SpringBootTest(classes = RenrenApplication.class)
@RunWith(SpringRunner.class)
public class TestExcel {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private SchedulingService schedulingService;
    @Test
    public void test(){

        ExcelReader reader = ExcelUtil.getReader("D:\\Workspace\\Java\\logistics\\src\\main\\resources\\排班-最终结果.xlsx");
        List<List<Object>> data = reader.read();
        for (int i = 1;i <= 200;i++){
            System.out.println("员工"+i+":");
            List<Object> s = data.get(i);
            int cnt = 1;
            for (int j = 0;j<60;j++){
                if ((j+1) % 15 != 0 || j == 0){
                    System.out.println("第"+cnt+"天: ");
                    Long x,y;
                    x = (Long) s.get(j);
                    j+=1;
                    y = (Long) s.get(j);
                    SchedulingEntity schedulingEntity = new SchedulingEntity();
                    schedulingEntity.setUserId(i);
                    schedulingEntity.setDate(cnt);
                    cnt++;
                    if (x == 1 && y == 0) {
                        schedulingEntity.setStatus("白班");
                        System.out.println("白班 ");
                    }
                    else if (x == 0 && y == 1) {
                        schedulingEntity.setStatus("夜班");
                        System.out.println("夜班" );
                    }
                    else if (x == 1 && y == 1){
                        schedulingEntity.setStatus("全天");
                        System.out.println("全天");
                    }
                    else {
                        schedulingEntity.setStatus("休息");
                        System.out.println("休息");
                    }
                    System.out.println(schedulingEntity);
                    schedulingService.save(schedulingEntity);
                }
            }

        }
    }
}
