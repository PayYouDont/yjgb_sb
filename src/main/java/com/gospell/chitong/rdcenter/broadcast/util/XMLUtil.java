package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.Signature;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in.EBM;

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
	public static String createXML(BaseXML entity,String outPath,String rootName,String xmlName) {
		Map<String,Object> map = entity.getMap();
		return createXML(map, outPath,rootName,xmlName);
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
	public static String createXML(Map<String,Object> map,String outPath,String rootName,String xmlName) {
		//创建Document对象
		Document document = DocumentHelper.createDocument();
		//创建根节点
		Element EBD = document.addElement(rootName);
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
	 * @Title: createSignature 
	 * @Description: TODO(生成签名) 
	 * @param map
	 * @param outPath
	 * @param name
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月21日 下午3:57:32
	 */
	public static String createSignature(String RelatedEBD_EBD_EBDID,String outPath,String name,byte[] inData) {
		Signature sign = new Signature();
		sign.setRelatedEBD_EBDID(RelatedEBD_EBD_EBDID);
		sign.setCertSN(SignatureUtil.getCertSN());
		sign.setSignature_SignatureValue(SignatureUtil.signature(inData));
		name = "EBDS_EBDB_"+name;
		String xmlpath = createXML(sign.getMap(),outPath,"Signature",name);
		File file = new File(xmlpath);
		return file.getPath();
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
			Element elem = root;
			if(key!=null) {
				elem = root.addElement(key);
			}
			if(value instanceof Map) {
				setElem(elem, (Map<String,Object>)value);
			}else {
				if(value!=null) {
					elem.setText(value.toString());
				}else {
					deleteNullElement(elem);
					return;
				}
			}
		}
	}
	/**
	 * 删除空节点
	 * @Title: deleteNullElement 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param elem    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月5日 下午5:41:29
	 */
	public static void deleteNullElement(Element elem) {
		String text = elem.getText();
		Element parent = elem.getParent();
		if(!"EBD".equals(parent.getName())&&"".equals(text)) {
			parent.remove(elem);
			deleteNullElement(parent);
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
        	if(xmlName.indexOf(".xml")==-1) {
        		xmlName = xmlName+".xml";
        	}
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
            return file.getPath();
        } catch (IOException e) {
            logger.error("创建XML错误:creatFixedXML error",e);
        }
        return null;
	}
	/**
	 * 
	 * @Title: readXML 
	 * @Description: TODO(重写方法：根据xml路径读取xml文件内容，并生成对应实体类) 
	 * @param clazz
	 * @param xmlPath
	 * @return    设定文件 
	 * @return T    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月22日 上午10:48:45
	 */
	public static <T>T readXML(Class<T> clazz,String xmlPath) {
		File xmlFile = new File(xmlPath);
		return readXML(clazz, xmlFile);
	}
	/**
	 * 读取xml文件内容，并生成对应实体类
	 * @Title: readXML 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param clazz
	 * @param xmlFile
	 * @return    设定文件 
	 * @return T    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月22日 上午10:47:57
	 */
	public static <T>T readXML(Class<T> clazz,File xmlFile) {
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载xml文件，获取document对象  
			Document document = reader.read(xmlFile);
			// 通过document对象获取根节点bookstore  
            Element root = document.getRootElement();  
            //通过反射创建对象
            T xml = clazz.newInstance();
   		 	//属性集合
	   		List<Field> fieldList = new ArrayList<>();
	   		fieldList = ReflecUtil.getFields(clazz);
	   		xml = writeToEntity(xml,root, fieldList);
	   		return xml;
		}catch(Exception e) {
			logger.error("解析xml错误：",e);
			return null;
		}
	}
	/**
	 * @Title: writeToEntity 
	 * @Description: TODO(将属性值写入实体类) 
	 * @param xml
	 * @param elem
	 * @param fieldList
	 * @return
	 * @throws Exception    设定文件 
	 * @return T    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月22日 上午10:49:13
	 */
	@SuppressWarnings("unchecked")
	public static <T>T writeToEntity(T xml,Element elem,List<Field> fieldList) throws Exception{
		 List<Element> elements = elem.elements();
		 if(elements.size()>0) {
			 for (Element element : elements) {
				 String parentName = element.getParent().getName();
				 String elemName = element.getName();
				 String ename = parentName+"_"+elemName;
				 for (Field field : fieldList) {
					 String fieldName = field.getName();
					 if(fieldName.equals(ename)) {
						//如果是数组类型
						if(field.getType().isInstance(new ArrayList<Object>())) {
							List<T> list = new ArrayList<>();
							if(field.get(xml)!=null) {
								list = (List<T>)field.get(xml);
							}
							list.addAll(writeArrayToEntrty(element,field));
							field.set(xml, list);
						}else {
							String text = element.getText();
							field.set(xml, text);
						}
					 }
				 }
				 writeToEntity(xml,element, fieldList);
			 }
		 }
		return xml;
	}
	/**
	 * @Title: writeArrayToEntrty 
	 * @Description: TODO(将xml元素中的数组类型值写入到对象数组属性中) 
	 * @param elem
	 * @param field
	 * @return
	 * @throws Exception    设定文件 
	 * @return List<T>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月22日 上午10:49:36
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> writeArrayToEntrty(Element elem,Field field) throws Exception {
		List<T> list = new ArrayList<>();
		Type type = field.getGenericType();
		if(ParameterizedType.class.isAssignableFrom(type.getClass())) {
			T xml = null;
			for(Type t:((ParameterizedType) type).getActualTypeArguments()) {
				Class<?> clazz = (Class<?>)t;
				List<Field> fieldList = ReflecUtil.getFields(clazz);
				xml = (T) clazz.newInstance();
				xml = writeToEntity(xml,elem, fieldList);
				list.add(xml);
			}
		}
		return list;
	}
	public static void main(String[] args) {
		String xmlPath = "D:\\tar\\get\\EBDB_10344510300000001030101012018121200000001.xml";
		File file = new File(xmlPath);
		BaseXML xml = XMLUtil.readXML(EBM.class,file);
		System.out.println(xml);
	}
}
