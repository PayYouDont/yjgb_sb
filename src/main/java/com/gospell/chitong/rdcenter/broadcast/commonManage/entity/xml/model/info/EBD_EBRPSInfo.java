/**
 * @Title: EBD_EBRPSInfo.java @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml @Description: TODO(     ) @author peiyongdong @date 2018年12月13日 下午5:49:55
 */
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Platform;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.PlatformService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import lombok.EqualsAndHashCode;

/** @ClassName: EBD_EBRPSInfo
 * @Description: TODO(平台信息,应急广播平台信息)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:49:55
 **/
@lombok.Data
public class EBD_EBRPSInfo implements EBDResponse {
    private EBD EBD;

    @lombok.Data
    @EqualsAndHashCode(callSuper = false)
    public static class EBD extends BaseEBD {
        private EBRPSInfo EBRPSInfo;
    }

    @lombok.Data
    public static class EBRPSInfo {
        private Params Params;
        private EBRPS EBRPS;
    }

    @lombok.Data
    public static class Params {
        private String RptStartTime;
        private String RptEndTime;
        private String RptType;
    }

    @lombok.Data
    public static class EBRPS {
        private String RptTime;
        private String RptType;
        private RelatedEBRPS RelatedEBRPS;
        private String EBRID;
        private String EBRName;
        private String Address;
        private String Contact;
        private String PhoneNumber;
        private String Longitude;
        private String Latitude;
        private String URL;
    }

    @lombok.Data
    public static class RelatedEBRPS {
        private String EBRID;
    }
    public EBD_EBRPSInfo() {}
    public EBD_EBRPSInfo(ServerProperties prop) {
        Platform platform = ApplicationContextRegister.getBean(PlatformService.class).selectById(1);
        createInstance(platform,"Sync");
    }

    @Override
    public EBD_EBRPSInfo createFullResponse() {
        Platform platform = ApplicationContextRegister.getBean(PlatformService.class).selectById(1);
        return createInstance(platform,"Full");
    }

    @Override
    public EBD_EBRPSInfo createIncrementalResponse() {
        EBD = new EBD ();
        EBD.setEBDHeader ();
        EBD.setEBDType ("EBRPSInfo");
        EBD.EBRPSInfo = new EBRPSInfo ();
        EBD.EBRPSInfo.Params = new Params ();
        EBD.EBRPSInfo.Params.RptStartTime = DateUtils.getDateTime ();
        EBD.EBRPSInfo.Params.RptEndTime = DateUtils.getDateTime ();
        EBD.EBRPSInfo.Params.RptType = "Incremental";
        EBD.EBRPSInfo.EBRPS = new EBRPS ();
        return this;
    }
    public EBD_EBRPSInfo createInstance(Platform platform,String rptType){
        if (platform!=null){
            EBD = new EBD ();
            EBD.setEBDHeader ();
            EBD.setEBDType ("EBRPSInfo");
            EBD.EBRPSInfo = new EBRPSInfo ();
            EBD.EBRPSInfo.EBRPS = new EBRPS ();
            EBD.EBRPSInfo.EBRPS.RptTime = DateUtils.getDateTime ();
            EBD.EBRPSInfo.EBRPS.RptType = rptType;
            EBD.EBRPSInfo.EBRPS.RelatedEBRPS = new RelatedEBRPS();
            EBD.EBRPSInfo.EBRPS.RelatedEBRPS.EBRID = "23400000000000103010101";
            EBD.EBRPSInfo.EBRPS.EBRID = EBDcodeUtil.getEBRID(platform.getAddressCode());//prop.getSRC_EBRID ();
            EBD.EBRPSInfo.EBRPS.EBRName = platform.getName();
            EBD.EBRPSInfo.EBRPS.Address = platform.getAddress();
            EBD.EBRPSInfo.EBRPS.Contact = platform.getContact();
            EBD.EBRPSInfo.EBRPS.PhoneNumber = platform.getPhoneNumber();
            EBD.EBRPSInfo.EBRPS.Longitude = platform.getLng();
            EBD.EBRPSInfo.EBRPS.Latitude = platform.getLat();
            EBD.EBRPSInfo.EBRPS.URL = platform.getUrl();
        }
        return this;
    }
}
