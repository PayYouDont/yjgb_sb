package com.gospell.chitong.rdcenter.broadcast.broadcastMange.config;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.UnitJosn;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Platform;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.PlatformService;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Configuration
@ConfigurationProperties(prefix = "server")
/*
 *  加载配置文件资源，从classpath:config和配置文件config.path中获取，
 *  当config.path中文件不存在时，用classpath中的配置，当存在时会被覆盖
 *  ignoreResourceNotFound = true表示当文件不存在时不报错，默认为false
 */
@PropertySource(value = { "classpath:config/server.properties", "file:${config.path}" }, ignoreResourceNotFound = true, encoding = "utf-8")
@Data
@Component
public class ServerProperties {
	// 播发管理系统名称
	private String broadcastName;
	// 版本号
	private String server_version;
	// 服务器ip地址
	private String server_ip;
	// 服务器端口
	private String server_port;
	// 平台名称
	private String unitName;
	// 平台管辖区域中心坐标经度
	private String areaLongitude;
	// 平台管辖区域中心坐标纬度
	private String areaLatitude;
	// 收到的tar包路径
	private String tarInPath;
	// 输出的tar包路径
	private String tarOutPath;
	//收到的心跳回执包存放路径
	private String heartTarPath;
	// 发出的通用回复包存放路径
	private String replyOutTarPath;
	// 收到的通用回复包存放路径
	private String replyInTarPath;
	//后台信息地址
	private String supporterUrl;
	//应急信息获取后台频点资源
    private String programAddress;
    //指令通道
    private String cmdChannel;
	//是否开启心跳检测
	private boolean connectionCheck;
	//终端与netty服务器之间的心跳间隔
	private Integer NettyHeartRate;
	//是否生成签名文件
	private boolean createSign;
	//是否验签
	private boolean isCheckSign;
	private boolean reportSuperior;
    @Resource
    private PlatformService platformService;
    private Platform platform;

    public Platform getPlatform() {
        platform = platformService.selectById (1);
        return platform;
    }
    public String getSRC_EBRID() {
		String SRC_EBRID = "";
		// 格式为：区域级别+区域编码+资源编码后10位
        //SRC_EBRID = getAreaLevel() + platform.getAddressCode () +  getSouceLastCode ();
        SRC_EBRID = EBDcodeUtil.getEBRID (platform.getAddressCode ());
        //SRC_EBRID = "43415230000000101010101";
		return SRC_EBRID;
	}

	public String getDEST_EBRID() {
		String SRC_EBRID = "";
		// 格式为：区域级别+区域编码+资源编码后10位
        SRC_EBRID = EBDcodeUtil.getEBRID (platform.getSuperAreaCode ());
		//SRC_EBRID = "23400000000000101010101";
		return SRC_EBRID;
	}

	public String getLocation() {
		UnitJosn unitjson = new UnitJosn();
		unitjson.setName(unitName);
        unitjson.setCode(platform.getAddressCode ());
		unitjson.setLongitude(areaLongitude);
		unitjson.setLatitude(areaLatitude);
		unitjson.setLevel(EBDcodeUtil.getAreaCodeLevel (platform.getAddressCode ()).toString ());
		String json = JsonUtil.toJson(unitjson);
		return json;
	}
	//平台资源编码后10位编码
	public String getSouceLastCode(){
        return  platform.getEBRPSLastCode ();
    }
    //适配器资源编码后10位编码
    public String getEBRASLastCode(){
        return  platform.getEBRASLastCode ();
    }
    //传输设备源编码后10位编码
    public String getEBRBSLastCode(){
        return  platform.getEBRBSSLastCode ();
    }
    //终端资源编码后10位编码
    public String getEBRDTLastCode(){
        return  platform.getEBRDTLastCode ();
    }
    //播发管理系统logo地址（image）
    public String getBroadcastLogo() {
        return platform.getLogoPath ();
    }
    // 后台管理系统名称
    public String getManageName() {
        return platform.getName ();
    }
    public String getSuperUrl(){
        return platform.getSuperiorUrl ();
    }
}
