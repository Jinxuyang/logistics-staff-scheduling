package io.renren.modules.generator.controller.viewobject;

import lombok.Data;

import java.util.List;

/**
 * @Author Verge
 * @Date 2021/2/28 13:57
 * @Version 1.0
 */
@Data
public class WeekScheduleViewObject {
    //员工ID
    private String username;
    //员工分类
    private String category;
    //一周的排班状态
    private List<String> date;

    private int day;

    private int night;

    private int rest;

    private String remark;
}
