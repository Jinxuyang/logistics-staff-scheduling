package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Verge
 * @email 981340404@qq.com
 * @date 2021-02-28 19:29:23
 */
@Data
@TableName("tb_week_schedule_remark")
public class WeekScheduleRemarkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private Integer start;

	private Integer end;
	/**
	 * 
	 */
	private String remark;

}
