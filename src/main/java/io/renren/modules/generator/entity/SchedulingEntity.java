package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * 
 * @author Verge
 * @email 981340404@qq.com
 * @date 2021-01-27 15:54:31
 */
@Data
@TableName("tb_scheduling")
public class SchedulingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 员工ID
	 */
	private Integer userId;
	/**
	 * 日期
	 */
	private Integer date;
	/**
	 * 状态
	 */
	private String status;

}
