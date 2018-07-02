package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBRDTInfo;

public class XMLUtil {

	public static final Logger logger = LoggerFactory.getLogger("com.gospell.chitong.rdcenter.broadcast.util.XMLUitl");
	
	/**
	 * 根基实体类生成xml
	 * @Title: createXML 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param entity
	 * @param @param outPath
	 * @param @param xmlName
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月28日 上午9:00:06
	 */
	public static String createXML(BaseXML entity,String outPath,String xmlName) {
		Map<String,Object> map = entity.getMap();
		return createXML(map, outPath, xmlName);
	}
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
	public static String createXML(Map<String,Object> map,String outPath,String xmlName) {
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
		outPath = XmlWriter(document, outPath,xmlName);
		return outPath;
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
		//生成输出路径+名字
		XmlWriter(document, outPath,xmlName);
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
	public static String XmlWriter(Document document,String outPath,String xmlName){
		// 创建输出格式(OutputFormat对象)
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置输出文件的编码
        // format.setEncoding("GBK");
        try {
        	File file = new File(outPath);
        	if(!file.exists()) {
        		file.mkdirs();
        	}
        	xmlName = xmlName+".xml";
    		outPath = outPath + File.separatorChar+xmlName;
    		file = new File(outPath);
    		//生成输出路径+名字
            // 创建XMLWriter对象
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);

            //设置不自动进行转义
            writer.setEscapeText(false);
            // 生成XML文件
            writer.write(document);

            //关闭XMLWriter对象
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("创建XML错误:creatFixedXML error");
            logger.error(e.getMessage(),e);
        }
        return outPath;
	}
	/**
	 * 读取xml文件内容，并生成对应实体类
	 * @Title: readXML 
	 * @Description: TODO(读取xml文件内容，并生成对应实体类) 
	 * @param @param xmlFile
	 * @param @return    设定文件 
	 * @return BaseXML    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月25日 下午4:16:22
	 */
	public static BaseXML readXML(File xmlFile,Class<? extends BaseXML> clazz) {
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载xml文件，获取document对象  
			Document document = reader.read(xmlFile);
			// 通过document对象获取根节点bookstore  
            Element EBD = document.getRootElement();  
            //通过反射创建对象
            BaseXML xml = clazz.newInstance();
   		 	//属性集合
	   		List<Field> fieldList = new ArrayList<>();
	   		fieldList = ReflecUtil.getFields(clazz);
	   		xml = writeToEntity(EBD, fieldList,xml);
	   		return xml;
		}catch(Exception e) {
			logger.error("解析xml错误：",e);
			return null;
		}
	}
	/**
	 * 根据xml路径读取xml文件内容，并生成对应实体类
	 * @Title: readXML 
	 * @Description: TODO(重写方法：根据xml路径读取xml文件内容，并生成对应实体类) 
	 * @param @param xmlPath
	 * @param @param clazz
	 * @param @return    设定文件 
	 * @return BaseXML    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月25日 下午4:19:04
	 */
	public static BaseXML readXML(String xmlPath,Class<? extends BaseXML> clazz) {
		File xmlFile = new File(xmlPath);
		return readXML(xmlFile, clazz);
	}
	/**
	 * 将属性值写入实体类
	 * @Title: writeToEntity 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param elem
	 * @param @param fieldList
	 * @param @param xml
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return BaseXML    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月21日 下午4:28:46
	 */
	public static BaseXML writeToEntity(Element elem,List<Field> fieldList,BaseXML xml) throws Exception{
		 List<Element> elements = elem.elements();
		 if(elements.size()>0) {
			 for (Element element : elements) {
				String parentName = element.getParent().getName();
				String elemName = element.getName();
				String text = element.getText();
				for (Field field : fieldList) {
					String fieldName = field.getName();
					String ename = parentName+"_"+elemName;
					if(fieldName.equals(ename)) {
						field.set(xml, text);
					}
				}
				writeToEntity(element, fieldList,xml);
			 }
		 }
		return xml;
	}
	public static void main(String[] args) {
		String xmlPath = "C:\\Users\\pay\\Desktop\\资料\\tar\\EBDB_10434152300000001030101012018062300000001.xml";
	 	BaseXML xml = readXML(xmlPath, EBRDTInfo.class);
	 	String outPath = "C:\\Users\\pay\\Desktop\\xml\\";
	 	Map<String,Object> map = xml.getMap();
	 	Map<String,Object> EBRDT = new HashMap<>();
 		EBRDT.put("RptTime", "2018-06-23 16:38:45");
 		EBRDT.put("RptType", "Sync");
 		map = xml.addSubMap("EBRDTInfo", EBRDT);
	 	createXML(map, outPath, "终端信息111");
	 	System.out.println(xml);
	}
}
