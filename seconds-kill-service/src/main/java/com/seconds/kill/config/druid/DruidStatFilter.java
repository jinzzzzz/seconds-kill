package com.seconds.kill.config.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
 * druid监控Filter
 * @author jinjin
 * @date 2018-09-19
 */
@WebFilter(
	filterName="druidWebStatFilter",
	urlPatterns="/*",
	initParams={
		@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源    
	}
)
public class DruidStatFilter extends WebStatFilter{

}
