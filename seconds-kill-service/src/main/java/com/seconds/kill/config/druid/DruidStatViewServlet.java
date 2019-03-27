package com.seconds.kill.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;


/**
 * druid监控视图配置
 * @author jinjin
 * @date 2018-09-19
 */
@WebServlet(urlPatterns = "/druid/*", initParams = {
		@WebInitParam(name = "allow", value = ""),// IP白名单(没有配置或者为空，则允许所有访问)
		@WebInitParam(name = "deny", value = ""),// IP黑名单 (存在共同时，deny优先于allow)
		@WebInitParam(name = "loginUsername", value = "jinjin@zz"),// 用户名
		@WebInitParam(name = "loginPassword", value = "jinjin"),// 密码
		@WebInitParam(name = "resetEnable", value = "false"), // 禁用HTML页面上的“ResetAll”功能
		@WebInitParam(name = "sessionStatEnable", value = "false") // 暂时关闭session监控功能
})
public class DruidStatViewServlet extends StatViewServlet {

	private static final long serialVersionUID = 3220659039630924983L;

}
