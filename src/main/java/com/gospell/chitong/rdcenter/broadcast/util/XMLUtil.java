
package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.*;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.util.StringUtils;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_Signature;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD_Type;
/** 
* @ClassName: XMLUtil O
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年12月13日 上午11:56:50 
*  
*/
public class XMLUtil {
	
	public static EBD readXMLToBean(String xmlPath) {
		File xmlFile = new File(xmlPath);
		return readXMLToBean(xmlFile);
	}
	public static EBD readXMLToBean(File xmlFile) {
		String json = readXMLToJsonStr(xmlFile);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		if(element.isJsonObject()) {
			String EBDType = "";
			EBDType = parseJsonElement(element,EBDType);
			Class<? extends EBD> clazz = EBD_Type.getClassByEBDType(EBDType);
			return readXMLToBean(xmlFile, clazz);
		}
		return null;
	}
	public static String parseJsonElement(JsonElement element,String EBDType) {
		JsonObject jsonObject = element.getAsJsonObject();
		Set<Entry<String, JsonElement>> set = jsonObject.entrySet();
		for (Entry<String, JsonElement> entry : set) {
			JsonElement value = entry.getValue();
			if(value.isJsonObject()) {
				EBDType = parseJsonElement(value,EBDType);
			}else {
				String key = entry.getKey();
				if("EBDType".equals(key)) {
					EBDType = value.toString().replace("\"","");
					return EBDType;
				}
			}
		}
		return EBDType;
	}
	public static <T>T readXMLToBean(String xmlPath,Class<T> clazz) {
		File file = new File(xmlPath);
		return readXMLToBean(file, clazz);
	}
	public static <T>T readXMLToBean(File xmlFile,Class<T> clazz) {
		String json = readXMLToJsonStr(xmlFile);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(element,clazz);
}
	public static String readXMLToJsonStr(File xmlFile) {
		SAXReader reader = new SAXReader();
		// 通过reader对象的read方法加载xml文件，获取document对象  
		try {
			Document document = reader.read(xmlFile);
			// 通过document对象获取根节点bookstore  
            Element root = document.getRootElement();  
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb = readElementToJson(root, sb);
            sb.replace(sb.length()-1, sb.length(),"}");
            return sb.toString();
		} catch (DocumentException e) {
			LoggerUtil.log(XMLUtil.class,e);
		}
		return null;
	}
	public static StringBuilder readElementToJson(Element element,StringBuilder sb) {
		if(element.hasContent()) {
			String key = element.getName();
			String value = element.getText().trim();
			List<Element> elements = element.elements();
			if(elements.size()>0) {
				sb.append("\""+key+"\":{");
				for (int i=0;i<elements.size();i++) {
					Element elem = elements.get(i);
					readElementToJson(elem, sb);
					if(i==elements.size()-1) {
						sb.delete(sb.length()-1, sb.length());
					}
				}
				sb.append("},");
			}else {
				sb.append("\""+key+"\":\""+value+"\",");
			}
		}
		return sb;
	}
	public static String createXMLByBean(EBD ebd,String outPath,String xmlName) {
		if(ebd==null) {
			return null;
		}
		String jsonStr;
		if(ebd instanceof EBD_Signature) {
			jsonStr = JsonUtil.toJson(ebd);
		}else {
			jsonStr = JsonUtil.toJson(ebd,ebd.getClass());
		}
		return createXMLByJson(jsonStr, outPath, xmlName);
	}
	public static String createXMLByJson(String jsonStr,String outPath,String xmlName) {
		if(StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		return createXMLByJson(jsonStr, null, outPath, xmlName);
	}
	public static String createXMLByJson(String jsonStr,String rootName,String outPath,String xmlName) {
		if(StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		//创建Document对象
		Document document = DocumentHelper.createDocument();
		Branch root = document;
		if(rootName!=null) {
			root = document.addElement(rootName);
		}
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonStr);
		if(element.isJsonObject()) {
			setElement(element,root);
		}
		return XmlWriter(document, outPath, xmlName);
	}
	public static void setElement(JsonElement element,Branch document) {
		JsonObject jsonObject = element.getAsJsonObject();
		Set<Entry<String, JsonElement>> set = jsonObject.entrySet();
		for (Entry<String, JsonElement> entry : set) {
			String key = entry.getKey();
			JsonElement value = entry.getValue();
			Element e = document.addElement(key);
			if(value.isJsonObject()) {
				setElement(value,e);
			}else if(value.isJsonArray()){
				JsonArray array = (JsonArray)value;
				array.forEach(v->setElement(v,e));
			}else {
			    if (!value.toString().equals ("")){
                    e.setText(value.toString().replace("\"", ""));
                }
			}
		}
	}
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
        	LoggerUtil.log (XMLUtil.class,e);
        }
        return null;
	}
	public static String createSignature(String EBDID,String outPath,String name,byte[] inData) {
		EBD_Signature sign = new EBD_Signature(EBDID, inData);
		name = "EBDS_EBDB_"+name;
		String jsonStr = JsonUtil.toJson(sign);
		String xmlpath = createXMLByJson(jsonStr, outPath, name);
		File file = new File(xmlpath);
		return file.getPath();
	}
}
