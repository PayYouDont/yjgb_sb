/*
package com.gospell.chitong.rdcenter.broadcast.util;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.CertXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;

public class CertUtil {
    private Logger logger = LoggerFactory.getLogger(CertUtil.class);

    public static void main(String[] args) throws Exception{
        String code = "RET_OK SN=000000000001,SIGN_LIST=MEUCID9+xM0KznefUTSEBzxQqedoPKAlhtzS3NQxGhceVZ+eAiEArSf2dN2As5FpjrT65TVHpi93pcJVPjXh/G7XLmb4w0w=,LIST=AAAAABNBDwIAAAAAE0sAAAAAE0wAAAAAAAE/fsTNCs53n1E0hAc8UKnnaDygJYbc0tzUMRoXHlWfnq0n9nTdgLORaY60+uU1R6Yvd6XCVT414fxu1y5m+MNM,SIGN_CERT=MEUCIQD+nG8eNFunl7BxUIS7wYtyIMK6dmyvcexEA3Ojdm6slgIgT8Q/vvXgtFV4ueYvNFD0QZQfGZ8T8mmYWnBG2kYaNhY=,<?xml version=\"1.0\" encoding=\"GBK\"?><DLBResponseData><ResponseSignTime>2019-06-21 14:21:43</ResponseSignTime><ResponseCertPath>00000000134B,00000000134C</ResponseCertPath><cert SN=\"00000000134B\"><CertSN>00000000134b</CertSN><CertUsage>0</CertUsage><CertCtx>1000000000000100000000134B48099AC7DEAD748342C7C49A09E41412D3F5B66527DA59A5D778EC62106DD30629BF89A12652259F5F51CA116C9DC2A3F41CDCC85EE7A496C71A59522ACE39849F519DD012F844BDC9F386A2EA33CF8AB001381849A814198CDB1B6CD169E971ED56F1826571D2C825E0D853C5D0E90ED2D5ABCA82D19CE89A51ECECDD12AE802961</CertCtx><CertState>0</CertState><SignDate>2018-09-21 17:31:59.0</SignDate><ValidDate>2048-09-30 23:59:59.0</ValidDate></cert><cert SN=\"00000000134C\"><CertSN>00000000134c</CertSN><CertUsage>0</CertUsage><CertCtx>1000000000000100000000134C4809D7972F7304E0DE1459909624EF49283ACC6A3B6CD3852B19C5612081116FD90BE2F930D7816139216FF66EB541FAD6FC5C4DCB6E86FDC26A9E38033EC89A8A4917B0C1FD3E903691511C653010EB4EDC4E1E2D1F2C85FBD81F8E6BF372300DA8EB03E63D55A6696AFC542EEAFD2FA0EA1117DDE3CEF4933E27ED539E217529B5</CertCtx><CertState>0</CertState><SignDate>2018-09-21 17:31:59.0</SignDate><ValidDate>2048-09-30 23:59:59.0</ValidDate></cert></DLBResponseData>";
        String list = code.substring (code.lastIndexOf ("LIST=")+5,code.indexOf (",SIGN_CERT"));
        System.out.println("list="+list);
        int begin = code.indexOf("<CertCtx>")+9;
        int end = code.indexOf("</CertCtx>");
        while (begin!=-1&&end!=-1){
            String certCtx = code.substring (begin,end);
            System.out.println("CertCtx="+certCtx);
            code = code.substring(end+9);
            begin = code.indexOf("<CertCtx>")+9;
            end = code.indexOf("</CertCtx>");
        }
    }
}
*/
