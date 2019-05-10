package com.gospell.chitong.rdcenter.broadcast.commonManage.controller;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName BackCommunicationAction
 * @Description TODO
 * @Author pay
 * @DATE 2019/4/13 15:20
 **/
public class BackCommunicationAction extends BaseAction{
    @Resource
    private DeviceInfoService devService;
    public BackCommunicationAction() {
    }

    @RequestMapping({"/deviceHeart"})
    public void deviceHeart(HttpServletResponse response, String reJson) {
        JSONArray jsonArray = JSONArray.fromObject(reJson);
        JSONObject returnObject = new JSONObject();
        List<String> errorDev = new ArrayList();
        Deviceinfo info = null;

        try {
            Iterator var7 = jsonArray.iterator();

            while(var7.hasNext()) {
                Object object = var7.next();
                JSONObject jo = (JSONObject)object;
                String DEV_ID = jo.getString("DEV_ID");
                String Stat1 = jo.getString("Stat");
                String Stat = StringUtils.leftPad(Integer.toBinaryString(Integer.valueOf(Stat1)), 8, "0");
                String Script = jo.getString("Script");
                String emerEBM_ID = jo.getString("EBM_ID");
                Map<String, Object> map = new HashMap();
                map.put("devdsn", DEV_ID);
                List<Deviceinfo> list = this.devService.list(map);
                if (list.size() > 0) {
                    info = list.get(0);
                }
                if (info != null) {
                    info.setStatus(Stat);
                    info.setStatusscript(Script);
                    if (!"".equals(emerEBM_ID)) {
                        //Integer EBM_ID = new Integer(emerEBM_ID);
                        info.setMessageid(emerEBM_ID);
                    }
                } else {
                    errorDev.add(DEV_ID);
                }
            }

            if (errorDev.size() > 0) {
                returnObject.element("Result", "NO_DEVICE" + errorDev.toString());
            } else {
                returnObject.element("Result", "OK");
            }

            JSONObject myjson = new JSONObject();
            myjson.element("name", "device");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(returnObject.toString());
        } catch (Exception var19) {
            var19.printStackTrace();
            returnObject.element("Result", "ERROR");
            response.setContentType("text/html;charset=UTF-8");

            try {
                response.getWriter().print(returnObject.toString());
            } catch (IOException var18) {
                var18.printStackTrace();
            }
        }

    }

    @RequestMapping({"/getRegisterDevice"})
    @ResponseBody
    public void getRegisterDevice(HttpServletResponse response, String registerDeviceJson) {
        JSONObject jsonObject = JSONObject.fromObject(registerDeviceJson);
        String registerDevDsn = jsonObject.getString("DevDsn");
        List<String> devDsnList = new ArrayList();
        Map<String, Object> map = new HashMap();
        List<Deviceinfo> deviceInfoList = this.devService.list(map);
        Deviceinfo registerDeviceInfo;
        if (deviceInfoList.size() != 0) {
            Iterator var8 = deviceInfoList.iterator();

            while(var8.hasNext()) {
                registerDeviceInfo = (Deviceinfo)var8.next();
                devDsnList.add(registerDeviceInfo.getDevdsn());
            }
        }

        JSONObject returnData = new JSONObject();
        if (devDsnList.contains(registerDevDsn)) {
            returnData.element("Devdsn", registerDevDsn);
            returnData.element("Messsage", "Exist");
        } else {
            registerDeviceInfo = new Deviceinfo();
            registerDeviceInfo.setDevdsn(registerDevDsn);
            registerDeviceInfo.setTimefind(new Date());
            registerDeviceInfo.setStatus("00000000");

            try {
                this.devService.save(registerDeviceInfo);
            } catch (Exception var12) {
                this.logger.error(var12.getMessage(), var12);
            }

            returnData.element("Devdsn", registerDevDsn);
            returnData.element("Message", "OK");
        }

        try {
            response.getWriter().write(returnData.toString());
        } catch (IOException var11) {
            this.logger.error(var11.getMessage(), var11);
        }

    }
}
