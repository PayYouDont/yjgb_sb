package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other;

import lombok.Data;

@Data
public class CertXML {
    private DLBResponseData DLBResponseData;
    @lombok.Data
    public static class DLBResponseData {
        private String ResponseSignTime;
        private String ResponseCertPath;
        private Cert cert;
    }
    @lombok.Data
    public static class Cert {
        private String CertSN;
        private String CertUsage;
        private String CertCtx;
        private String CertState;
        private String SignDate;
        private String ValidDate;
    }
}
