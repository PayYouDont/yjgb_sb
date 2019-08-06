
package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tass.exceptions.YJException;
import cn.tass.yingjgb.YingJGBCALLDLL;

/**
 * @author peiyongdong
 * @ClassName: SignatureUtil
 * @Description: TODO()
 * @date 2018年11月21日 上午11:52:20
 */
public class SignatureUtil {
    private static final Logger log = LoggerFactory.getLogger(SignatureUtil.class);
    public static boolean isStart = false;
    public static int flag = 0;

    /**
     * @return void 返回类型
     * @throws @author peiyongdong
     * @Title: start
     * @Description: TODO(连接SJJ1313密码器设备) 设定文件
     * @date 2018年11月20日 上午10:40:44
     */
    public static void start(int flagData) {
        try {
            flag = flagData;
            YingJGBCALLDLL.openDevice(flagData);
            isStart = true;
            log.info("签名驱动启动成功");
        } catch (Exception e) {
            isStart = false;
            log.error("签名驱动启动错误", e);
        }
    }

    /**
     * @return void 返回类型
     * @throws @author peiyongdong
     * @Title: stop
     * @Description: TODO(关闭密码设备) 设定文件
     * @date 2018年11月20日 上午10:40:33
     */
    public static void stop() {
        try {
            if (isStart) {
                YingJGBCALLDLL.closeDevice();
                isStart = false;
            }
        } catch (YJException e) {
            log.error("签名驱动停止错误", e);
        }
    }

    /**
     * @return java.lang.String
     * @Author peiyongdong
     * @Description (计算平台数据签名)
     * @Date 16:17 2019/5/13
     * @Param [inData]
     **/
    public static String signature(byte[] inData) {
        // 调用平台签名的方法
        try {
            if (isStart) {
                String sign = YingJGBCALLDLL.platformCalculateSignature(1, inData);
                return sign;
            }
        } catch (YJException e) {
            log.error("生成签名错误", e);
        }
        return null;
    }

    /**
     * @return boolean
     * @Author peiyongdong
     * @Description (平台验证数据签名)
     * @Date 16:18 2019/5/13
     * @Param [inData, sign]
     **/
    public static boolean verifySignature(byte[] inData, String sign) {
        // 返回验签结果
        try {
            if (isStart) {
                return YingJGBCALLDLL.platformVerifySignature(1, inData, sign);
            }
        } catch (YJException e) {
            log.error("验证签名错误", e);
        }
        return false;
    }

    public static String getCertSN() {
        return "0001230000000001";
    }

    public static byte[] int2bytes(int i) {
        byte[] res = new byte[4];
        res[0] = (byte) ((i >> 24) & 0xFF);
        res[1] = (byte) ((i >> 16) & 0xFF);
        res[2] = (byte) ((i >> 8) & 0xFF);
        res[3] = (byte) (i & 0xFF);
        return res;
    }

    // 将byte[]转换为16进制字符串
    public static String byteToHexString(byte[] b) {
        if (b == null)
            return null;
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexStr.charAt((b[i] & 0xf0) >> 4));
            sb.append(hexStr.charAt((b[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    private static final String hexStr = "0123456789ABCDEF";

    private static String hexString = "0123456789ABCDEF";

    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    public static byte[] hexStringToByte(String hex) {
        if (hex == null)
            return null;
        int len = (hex.length() / 2);
        hex = hex.toUpperCase();
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(int c) {
        byte b = (byte) hexStr.indexOf(c);
        return b;
    }

    public static byte[] readFile(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        byte[] msg = new byte[(int) file.length()];
        while (input.read(msg) != -1) {
        }
        input.close();
        return msg;
    }

    public static byte[] readFile(String path) throws IOException {
        File file = new File(path);
        return readFile(file);
    }

    public static void writeSign(String sign, String name) throws IOException {
        // 将签名值结果写进一个文件中
        File f = new File(name);
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter writer = new FileWriter(f);
        writer.write(sign);
        writer.flush();
        writer.close();
    }
}
