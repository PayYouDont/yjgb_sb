package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xeustechnologies.jtar.TarEntry;
import org.xeustechnologies.jtar.TarInputStream;
import org.xeustechnologies.jtar.TarOutputStream;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.ReceiveTarMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.SendTarMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ReceiveTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.SendTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket.WebScoketServer;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in.EBM;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;

public class TarUtil {

	public static final Logger logger = LoggerFactory.getLogger("com.gospell.chitong.rdcenter.broadcast.util.TarUtil");

	/**
	 * 解压归档tar包
	 * 
	 * @Title: archive
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param
	 *            inPath
	 * @param @param
	 *            outPath 设定文件
	 * @return void 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月21日 下午5:44:32
	 */
	public static void archive(String inPath, String outPath) {
		// outPath = outPath.lastIndexOf("\\")==outPath.length()?outPath:outPath+"\\";
		File file = new File(inPath);
		TarInputStream in = null;
		try {
			in = new TarInputStream(new FileInputStream(file));
			TarEntry entry = null;
			while ((entry = in.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				File outFile = new File(outPath + File.separatorChar + entry.getName());
				new File(outFile.getParent()).mkdirs();
				OutputStream out = new FileOutputStream(outFile);
				byte[] b = new byte[1024];
				int len = 0;
				while ((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			logger.error("解压tar包出错！", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 将实体类生成xml文件并打成tar包
	 * 
	 * @Title: createXMLTar
	 * @Description: TODO(重载方法)
	 * @param @param
	 *            entity
	 * @param @param
	 *            outPath
	 * @param @param
	 *            name 设定文件
	 * @return void 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月28日 上午9:10:40
	 */
	public static String createXMLTar(BaseXML entity, String outPath, String name) {
		return createXMLTar(entity.getMap(), outPath, name);
	}

	/**
	 * 生成xml文件并打包成tar包
	 * 
	 * @Title: createXMLTar
	 * @Description: TODO(生成xml文件并打包成tar包)
	 * @param @param
	 *            map
	 * @param @param
	 *            outPath
	 * @param @param
	 *            name
	 * @param @return
	 *            设定文件
	 * @return String 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月20日 下午1:36:30
	 */
	public static String createXMLTar(Map<String, Object> map, String outPath, String name) {
		String xmlpath = XMLUtil.createXML(map, outPath, name);
		name = name.indexOf(".tar") == -1 ? name + ".tar" : name;
		outPath += File.separatorChar +"EBDT_"+name;
		OutputStream tar = null;
		TarOutputStream tarOut = null;
		try {
			tar = new FileOutputStream(outPath);
			tarOut = new TarOutputStream(tar);
			File xml = new File(xmlpath);
			tarOut.putNextEntry(new TarEntry(xml, xml.getName()));
			FileUtil.wirteFile(new FileInputStream(xml), tarOut);
			FileUtil.delete(xmlpath);
		} catch (IOException e) {
			logger.error("创建tar包出错：" + e);
		}
		return outPath;
	}

	/**
	 * 根据tar包路径生成对应回执tar包路径
	 * 
	 * @Title: getTarByInTar
	 * @Description: TODO(根据tar包路径生成对应回执tar包路径)
	 * @param @param
	 *            tarPath
	 * @param @return
	 *            设定文件
	 * @return String 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月27日 上午9:13:19
	 */
	public static String getTarByInTar(String inTarPath, String outTarPath) {
		BaseXML xml = archiveAndgetBaseXML(inTarPath);
		if (xml != null) {
			// 将tar包信息保存至数据库
			//saveReceiveTar(xml);
			if (xml instanceof EBM) {
				try {
					WebScoketServer.startpush(inTarPath);
				} catch (Exception e) {
					logger.error("播发节点消息失败", e);
				}
			}
			BaseXML resultEntity = xml.getResponseByClass(xml);
			String resultEntityName = "EBDE_" + resultEntity.getEBD_EBDID();
			// 创建回执tar包返回路径
			outTarPath = createXMLTar(resultEntity, outTarPath, resultEntityName);
			// 保存发送tar包信息
			//saveSendTar(resultEntity);
			return outTarPath;
		}
		return null;
	}

	/**
	 * 解压tar包，并解析xml文件返回内容实体
	 * @Title: archiveAndgetBaseXML 
	 * @Description: TODO(解压tar包，并解析xml文件返回内容实体) 
	 * @param tarPath
	 * @return    设定文件 
	 * @return BaseXML    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午2:30:14
	 */
	public static BaseXML archiveAndgetBaseXML(String tarPath) {
		// d:/tar/xxxxx.tar
		String temDir = tarPath.substring(0, tarPath.indexOf(".tar"));
		// 临时文件夹名称
		String temPath = temDir + File.separatorChar + "temp";
		File tempFile = new File(temPath);
		// 查看临时文件夹是否存在
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		// 解压tar包到临时目录
		archive(tarPath, temPath);
		// 获取临时目录
		File[] files = tempFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 获取临时目录里面的文件
			File file = files[i];
			if (!file.isDirectory()) {
				String fileName = file.getName();
				// 获取非签名xml文件
				if (fileName.indexOf("EBDB") != -1 && fileName.indexOf("EBDS") == -1) {
					// 获取BaseXML
					BaseXML xml = XMLUtil.readXML(file, BaseXML.class);
					// 获取EBDType
					String EBDType = xml.getEBD_EBDType();
					// 获取实际属于哪个子类
					Class<? extends BaseXML> clazz = BaseXML.getClassByEBDType(EBDType);
					String xmlPath = file.getAbsolutePath();
					// 获取实际xml实体类
					xml = XMLUtil.readXML(xmlPath, clazz);
					// 删除临时文件
					FileUtil.delete(temDir);
					return xml;
				}
			}
		}
		return null;
	}
	/**
	 * 保存接收到的tar包信息
	 * @Title: saveReceiveTar 
	 * @Description: TODO(重载方法保存接收到的tar包信息) 
	 * @param tarPath
	 * @return    设定文件 
	 * @return int    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午2:27:10
	 */
	public static int saveReceiveTar(String tarPath) {
		BaseXML xml = archiveAndgetBaseXML(tarPath);
		return saveReceiveTar(xml);
	}
	/**
	 * 保存接收到的tar包信息
	 * @Title: saveReceiveTar
	 * @Description: TODO(保存接收到的tar包信息)
	 * @param xml
	 * @return 设定文件
	 * @return int 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年7月17日 下午2:04:47
	 */
	public static int saveReceiveTar(BaseXML xml) {
		try {
			ReceiveTarMapper dao = ApplicationContextRegister.getBean(ReceiveTarMapper.class);
			ReceiveTar tar = new ReceiveTar();
			tar.setId(xml.getEBD_EBDID());
			tar.setResourceCode(xml.getSRC_EBRID());
			tar.setStatus(1);
			tar.setType(1);
			tar.setEbdType(xml.getEBD_EBDType());
			// tar.setResourceId();
			return dao.insertSelective(tar);
		}catch(Exception e) {
			logger.error("接收tar包信息保存失败",e);
			return 0;
		}
	}
	/**
	 * 保存接发送的tar包信息
	 * @Title: saveSendTar 
	 * @Description: TODO(重载方法保存接发送的tar包信息) 
	 * @param tarPath
	 * @return    设定文件 
	 * @return int    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午2:28:21
	 */
	public static int saveSendTar(String tarPath) {
		BaseXML xml = archiveAndgetBaseXML(tarPath);
		return saveSendTar(xml);
	}
	/**
	 * 保存接发送的tar包信息
	 * @Title: saveSendTar
	 * @Description: TODO(保存接发送的tar包信息)
	 * @param xml
	 * @return 设定文件
	 * @return int 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年7月17日 下午2:05:10
	 */
	public static int saveSendTar(BaseXML xml) {
		try {
			SendTarMapper dao = ApplicationContextRegister.getBean(SendTarMapper.class);
			SendTar tar = new SendTar();
			tar.setEbdid(xml.getEBD_EBDID());
			tar.setEbdType(xml.getEBD_EBDType());
			tar.setDestId(xml.getDEST_EBRID());
			if (xml instanceof ResponseXML) {
				ResponseXML response = (ResponseXML) xml;
				String code = response.getResultCode();
				if(!code.equals("")) {
					tar.setResultCode(new Integer(code));
				}
				if(!response.getResultDesc().equals("")) {
					tar.setResultDesc(response.getResultDesc());
				}
			}
			return dao.insertSelective(tar);
		}catch(Exception e) {
			logger.error("发送tar包信息保存失败",e);
			return 0;
		}
	}
}
