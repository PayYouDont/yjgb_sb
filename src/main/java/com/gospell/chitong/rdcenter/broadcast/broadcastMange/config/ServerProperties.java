package com.gospell.chitong.rdcenter.broadcast.broadcastMange.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "server")
@PropertySource(value = "classpath:config/server.properties", encoding = "utf-8")
@Data
@Component
public class ServerProperties {
	// 后台管理系统名称
	private String manageName;
	// 后台管理系统logo地址（image）
	private String manageLogo;
	// 播发管理系统名称
	private String broadcastName;
	// 播发管理系统logo地址（image）
	private String broadcastLogo;
	// 版本号
	private String server_version;
	// 服务器ip地址
	private String server_ip;
	// 服务器端口
	private String server_port;
	// 项目名称
	private String system_root;
	// 应急信息发送地址
	private String emerSendIpAddress;
	// 应急信息获取频点资源
	private String programAddress;
	// 应急信息停止发送地址
	private String emerStopAddress;
	// 设备注册发送地址
	private String setParasAddress;
	// 平台名称
	private String UnitName;
	// 平台管辖区域等级 1省 2 市 3县/区 4镇 5村
	private String AreaLevel;
	// 平台管辖区域区域编码
	private String AreaCode;
	// 平台管辖区域中心坐标经度
	private String AreaLongitude;
	// 平台管辖区域中心坐标纬度
	private String AreaLatitude;
}
