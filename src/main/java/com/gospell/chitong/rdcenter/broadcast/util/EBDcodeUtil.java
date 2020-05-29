package com.gospell.chitong.rdcenter.broadcast.util;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;

import java.util.Date;

public class EBDcodeUtil {
	
	public static int EBDID = 0;
	
	public static Date DAY = new Date();
	//状态描述
	public static String EBRPSStateDesc = "";
	
	public static String getEBDIDCode() {
		Date now = new Date();
		if(DateUtils.getDistanceOfTwoDate(now, DAY)>=1) {//每天重置EBDID
			DAY = new Date();
			EBDID = 0;
		}
		EBDID++;
		return StringUtil.patch("0", 8, EBDID);
	}
	public static String getConnectionCheckCode() {
		return StringUtil.patch("0", 16, 0);
	}
	
	private static String createBaseDateCode() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		return prop.getSRC_EBRID()+DateUtils.getDate("yyyyMMdd");
	}
	
	public static String getEBMID() {
		EBDID++;
		return createBaseDateCode()+StringUtil.patch("0",4, EBDID);
	}
	public static String getConnectionCheckID() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		return "01"+prop.getSRC_EBRID()+getConnectionCheckCode();
	}
	public static String getBaseEBDID() {
		return "10"+createBaseDateCode()+getEBDIDCode();
	}
	/**
	* @Author peiyongdong
	* @Description ( 23位资源编码)
	* @Date 14:02 2019/5/8
	* @Param [deviceInfo, lastCode]
	* @return java.lang.String
	**/
	public static String getEBRID(Deviceinfo deviceInfo,String lastCode) {
		String code = deviceInfo.getDevaddresscode();
		int level = getAreaCodeLevel(code);
		return level+code+lastCode;
	}
	public static String getEBRID(String areaColde) {
		return getEBRID(areaColde,null);
	}
	/**
	* @Author peiyongdong
	* @Description ( 23位资源编码)
	* @Date 14:01 2019/5/8
	* @Param [areaColde]
	* @return java.lang.String
	**/
	public static String getEBRID(String areaColde,String type) {
		ServerProperties serverProperties =  ApplicationContextRegister.getBean(ServerProperties.class);
		String lastCode = "";
        int level = getAreaCodeLevel(areaColde);
		if (type==null){//平台
			lastCode = serverProperties.getSouceLastCode();
		}else if (type.indexOf("EBRAS")!=-1){//适配器
			lastCode = serverProperties.getEBRASLastCode();
		}else if (type.indexOf("EBRDT")!=-1){//终端
			lastCode = serverProperties.getEBRDTLastCode();
            level = 6;//村级
		}else if (type.indexOf("EBRBS")!=-1){//传输设备
            lastCode = serverProperties.getEBRBSLastCode ();
        }
		return level+areaColde+lastCode;
	}
    public static String getAreaCode(String EBRID) {
        String areaCode = EBRID.substring (1,13);
        return areaCode;
    }
	public static Integer getAreaCodeLevel(String areaCode) {
		if(areaCode.length()==12) {
			String first6Str = areaCode.substring(0,6);//前6位
			String last6Str = areaCode.substring(6, 12);//后6位
			if(last6Str.equals("000000")) {
				String str5_6 = first6Str.substring(4,6);//5-6位
				if(!str5_6.equals("00")) {
                    return 4;
				}
				String str2_4 = first6Str.substring(2,4);//3-4位
				if(!str2_4.equals("00")) {
                    return 3;
				}
                String str1_2 = first6Str.substring(1,2);//1-2位
                if(!str1_2.equals("00")) {
                    return 2;
                }else{//000000000000
                    return 1;
                }
			}else {
				String last3Str = last6Str.substring(3,6);//最后3位数
				if(last3Str.equals("000")) {
					return 5;
				}
				return 6;
			}
		}
		return -1;
	}
	public static String getEBRPSStateDesc() {
		if("".equals(EBRPSStateDesc)) {
			EBRPSStateDesc = "开机";
		}else {
			EBRPSStateDesc = "运行正常";
		}
		return EBRPSStateDesc;
	}
	public static String getParentCode(String addressCode){
        Integer level = getAreaCodeLevel (addressCode);
        if(level>0){
            String subCode;
            if(level<4){
                subCode = addressCode.substring (0,level*2);
            }else{
                subCode = addressCode.substring (0,6+(level-3)*3);
            }
            return subCode;
        }
	    return  null;
    }
}
