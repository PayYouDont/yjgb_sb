package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.BroadcastRecordXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.BroadcastRecordXML.EBM;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.BroadcastRecordXML.UnitInfo;

public class XMLUitl {

	public static final Logger logger = LoggerFactory.getLogger("com.gospell.chitong.rdcenter.broadcast.util.XMLUitl");
	
	/**
	 * 根Map创建XML
	 * @Title: createXML 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param map
	 * @param @param outPath
	 * @param @param xmlName
	 * @param @return    设定文件 
	 * @return Document    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月15日 下午4:34:42
	 */
	public static Document createXML(Map<String,Object> map,String outPath,String xmlName) {
		//创建Document对象
		Document document = DocumentHelper.createDocument();
		//创建根节点
		Element EBD = document.addElement("EBD");
		//递归创建子节点
		try {
			setElem(EBD, map);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		xmlName = xmlName.indexOf(".xml")==-1?xmlName+".xml":xmlName;
		outPath = outPath + File.separatorChar+xmlName;
		//生成输出路径+名字
		XmlWriter(document, outPath);
		return document;
	}
	/**
	 * 重写方法，为标元素签添加属性
	 * @Title: createXML 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param map
	 * @param @param attributes
	 * @param @param outPath
	 * @param @param xmlName
	 * @param @return    设定文件 
	 * @return Document    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月15日 下午4:34:33
	 */
	public static Document createXML(Map<String,Object> map,Map<String,Map<String,String>> attributes,String outPath,String xmlName) {
		//创建Document对象
		Document document = DocumentHelper.createDocument();
		//创建根节点
		Element EBD = document.addElement("EBD");
		try {
			//递归创建子节点
			setElem(EBD, map);
			//递归为元素添加属性
			setAttributes(EBD, attributes);
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		xmlName = xmlName.indexOf(".xml")==-1?xmlName+".xml":xmlName;
		outPath = outPath + File.separatorChar+xmlName;
		//生成输出路径+名字
		XmlWriter(document, outPath);
		return document;
	}
	/**
	 * 递归设置元素属性
	 * @Title: setAttributes 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param el
	 * @param @param attributes    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月15日 下午3:22:36
	 */
	public static void setAttributes(Element el,Map<String,Map<String,String>> attributes) throws Exception{
		Set<Entry<String, Map<String, String>>> set = attributes.entrySet();
		//遍历属性map
		for (Entry<String, Map<String, String>> entry : set) {
			//获取元素名称
			String elName = el.getName();
			//获取属性map的key
			String key = entry.getKey();
			//属性map中是否有该元素名称
			if(elName.equals(key)) {
				Set<Entry<String, String>> vset = entry.getValue().entrySet();
				//遍历属性
				for (Entry<String, String> vse: vset) {
					//拿到属性名称
					String attrName = vse.getKey();
					//拿到属性值
					String attrValue = vse.getValue();
					//为该元素设置属性
					el.addAttribute(attrName, attrValue);
				}
			}
		}
		List<Element> elements = el.elements();
		for (Element element : elements) {
			setAttributes(element, attributes);
		}
	}
	/**
	 * 递归创建子节点
	 * @Title: setElem 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param root
	 * @param @param map    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月15日 下午2:09:38
	 */
	@SuppressWarnings("unchecked")
	public static void setElem(Element root,Map<String,Object> map) throws Exception{
		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> entry : set) {
			String key = entry.getKey();
			Object value = entry.getValue();
			Element elem = root.addElement(key);
			if(value instanceof Map) {
				setElem(elem, (Map<String,Object>)value);
			}else {
				elem.setText(value.toString());
			}
		}
	}
	/**
	 * 写出xml
	 * @Title: XmlWriter 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param document
	 * @param @param outPath    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月15日 上午11:38:12
	 */
	public static void XmlWriter(Document document,String outPath){
		// 创建输出格式(OutputFormat对象)
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置输出文件的编码
        // format.setEncoding("GBK");
        try {
        	File file = new File(outPath);
            // 创建XMLWriter对象
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);

            //设置不自动进行转义
            writer.setEscapeText(false);
            // 生成XML文件
            writer.write(document);

            //关闭XMLWriter对象
            writer.close();
            logger.info("创建XML成功！路径:"+file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("创建XML错误:creatFixedXML error");
            logger.error(e.getMessage(),e);
        }
	}
	public static void main(String[] args) {
		/*Map<String,Object> EBD = new LinkedHashMap<>();
		EBD.put("EBDVersion", 1);
		EBD.put("EBDID","10434152300000001030101010000000000000002");
		EBD.put("EBDType", "EBM");
		Map<String,Object> SRC = new LinkedHashMap<>();
		SRC.put("EBRID", "43415230000000103010101");
		SRC.put("URL","http://127.0.0.1:8080/Emergency/receive/ebd.htm");
		EBD.put("SRC", SRC);
		Map<String,Object> DEST = new LinkedHashMap<>();
		DEST.put("EBRID", "43415230000000303010201");
		EBD.put("DEST", DEST);
		Map<String,Object> EBM = new LinkedHashMap<>();
		EBM.put("EBMVersion",1);
		EBM.put("EBMID","43415230000000103010101201802130002");
		Map<String,Object> MsgBasicInfo = new LinkedHashMap<>();
		MsgBasicInfo.put("MsgType",1);
		MsgBasicInfo.put("SenderName","舒城县气象局");
		MsgBasicInfo.put("SenderCode","0001");
		MsgBasicInfo.put("SendTime","2018-02-13 08:50:21");
		MsgBasicInfo.put("EventType","11B06");
		MsgBasicInfo.put("Severity",2);
		MsgBasicInfo.put("StartTime","2018-02-13 09:10:21");
		MsgBasicInfo.put("EndTime","2018-04-13 11:36:21");
		EBM.put("MsgBasicInfo",MsgBasicInfo);
		Map<String,Object> MsgContent = new LinkedHashMap<>();
		MsgContent.put("LanguageCode","zho");
		MsgContent.put("MsgTitle","大雾橙色预警");
		MsgContent.put("MsgDesc","大大雾橙色预警大雾橙色预警大雾橙色预警雾橙色预警");
		MsgContent.put("AreaCode","341523000000");
		MsgContent.put("ProgramNum",2);
		Map<String,Object> Auxiliary = new LinkedHashMap<>();
		Auxiliary.put("AuxiliaryType",2);
		Auxiliary.put("AuxiliaryDesc","EBDR_0001.mp3");
		MsgContent.put("Auxiliary",Auxiliary);
		EBM.put("MsgContent",MsgContent);
		EBD.put("EBM", EBM);
		String outPath = "C:\\Users\\pay\\Desktop\\xml";
		Map<String,Map<String,String>> attrs = new LinkedHashMap<>();
		Map<String,String> attr = new LinkedHashMap<>();
		attr.put("xmlns:xs", "http://www.w3.org/2001/XMLSchema");
		attrs.put("EBD", attr);
		attrs.put("EBDVersion", attr);
		createXML(EBD,attrs,outPath, "播发请求222");*/
		/*HeartXML xml = new HeartXML();
		xml.setEBDVersion("1");
		xml.setEBDID("01234000000000001010101010000000000000001");
		xml.setEBDType("ConnectionCheck");
		xml.setSRC_EBRID("23400000000000101010101");
		xml.setDEST_EBRID("33415000000000101010101");
		xml.setEBDTime("2017-06-07 13:40:36");
		xml.setRptTime("YYYY-MM-DD HH:MI:SS");
		String outPath = "C:\\Users\\pay\\Desktop\\xml";
		createXML(xml.getMap(),outPath,"心跳检测222");*/
		BroadcastRecordXML xml = new BroadcastRecordXML();
		xml.setEBDVersion("1");
		xml.setEBDID("10234000000000001010101010000000000000001");
		xml.setEBDType("EBMBrdLog");
		xml.setSRC_EBRID("23400000000000101010101");
		xml.setDEST_EBRID("33415000000000101010101");
		xml.setEBDTime("2017-06-07 13:40:36");
		xml.setRptStartTime("YYYY-MM-DD HH:MI:SS");
		xml.setRptEndTime("YYYY-MM-DD HH:MI:SS");
		EBM ebm = xml.getEBM();
		ebm.setEBMID("23400000000000101010101");
		ebm.setMsgType("1");
		ebm.setSenderName("某应急办");
		ebm.setSenderCode("1234");
		ebm.setSendTime("YYYY-MM-DD HH:MI:SS");
		ebm.setEventType("11A01");
		ebm.setSeverity("2");
		ebm.setStartTime("YYYY-MM-DD HH:MI:SS");
		ebm.setEndTime("YYYY-MM-DD HH:MI:SS");
		ebm.setLanguageCode("zho");
		ebm.setMsgTitle("暴雨黄色预警");
		ebm.setMsgDesc("下雨啦收衣服啦");
		ebm.setAreaCode("341523000000,341523000001");
		ebm.setProgramNum("12");
		UnitInfo unitInfo = xml.getUnitInfo();
		unitInfo.setEBRID("33415000000000101010101");
		unitInfo.setUnitID("007");
		unitInfo.setUnitName("应急办");
		unitInfo.setPersonID("008");
		unitInfo.setPersonName("刘德华");
		xml.setBrdStateDesc("播发成功");
		String outPath = "C:\\Users\\pay\\Desktop\\xml";
		createXML(xml.getMap(),outPath,"播发记录2");
	}
	
}
