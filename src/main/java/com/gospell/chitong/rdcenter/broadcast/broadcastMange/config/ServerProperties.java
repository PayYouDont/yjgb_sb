package com.gospell.chitong.rdcenter.broadcast.broadcastMange.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.UnitJosn;

import lombok.Data;
import net.sf.json.JSONObject;

@Configuration
@ConfigurationProperties(prefix = "server")
/*
 *  加载配置文件资源，从classpath:config和配置文件config.path中获取，
 *  当config.path中文件不存在时，用classpath中的配置，当存在时会被覆盖
 *  ignoreResourceNotFound = true表示当文件不存在时不报错，默认为false
 */
@PropertySource(value = { "classpath:config/server.properties",
		"file:${config.path}" }, ignoreResourceNotFound = true, 
		encoding = "utf-8")
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
	// private String system_root;
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
	// 收到的tar包路径
	private String tarInPath;
	// 输出的tar包路径
	private String tarOutPath;
	// 发送心跳节点路径
	private String sendUrl;
	// 上级平台区域等级
	private String SuperAreaLevel;
	// 上级平台区域编码
	private String SuperAreaCode;

	public String getSRC_EBRID() {
		String SRC_EBRID = "";
		// 格式为：区域级别+区域编码+资源类型码+资源子类型号(0103=调度控制系统)
		SRC_EBRID = getAreaLevel() + getAreaCode() + "0103" + "010101";
		return SRC_EBRID;
	}

	public String getDEST_EBRID() {
		String SRC_EBRID = "";
		// 格式为：区域级别+区域编码+资源类型码+资源子类型号(0103=调度控制系统)
		SRC_EBRID = getSuperAreaLevel() + getSuperAreaCode() + "0101" + "010101";
		return SRC_EBRID;
	}

	public String getLocation() {
		UnitJosn unitjson = new UnitJosn();
		unitjson.setName(UnitName);
		unitjson.setCode(AreaCode);
		unitjson.setLongitude(AreaLongitude);
		unitjson.setLatitude(AreaLatitude);
		unitjson.setLevel(AreaLevel);
		String json = JSONObject.fromObject(unitjson).toString();
		return json;
	}
}
