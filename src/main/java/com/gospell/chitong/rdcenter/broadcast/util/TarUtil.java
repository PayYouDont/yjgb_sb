package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
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
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket.WebScoketServer;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.Signature;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in.EBM;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;

public class TarUtil {

	public static final Logger logger = LoggerFactory.getLogger("com.gospell.chitong.rdcenter.broadcast.util.TarUtil");

	/**
	 * 解压归档tar包
	 * 
	 * @Title: archive
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param inPath
	 * @param @param outPath 设定文件
	 * @return void 返回类型
	 * @throws @author peiyongdong
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

	public static String createXMLTarByBean(EBD entity, String outPath, String name) {
		String tempPath = outPath+File.separatorChar+"temp";
		// 生成xml
		String xmlpath = XMLUtil2.createXMLByBean(entity, tempPath, name);
		// 生成签名xml
		byte[] inData = {};
		try {
			inData = FileUtil.readFile(new File(xmlpath));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		XMLUtil.createSignature(EBDcodeUtil.getBaseEBDID(),tempPath,name,inData);
		name = name.indexOf(".tar") == -1 ? name + ".tar" : name;
		outPath += File.separatorChar + "EBDT_" + name;
		pack(tempPath,outPath);
		FileUtil.delete(tempPath);
		return outPath;
	}
	/**
	 * 将实体类生成xml文件并打成tar包
	 * 
	 * @Title: createXMLTar
	 * @Description: TODO(重载方法)
	 * @param @param entity
	 * @param @param outPath
	 * @param @param name 设定文件
	 * @return void 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年6月28日 上午9:10:40
	 */
	public static String createXMLTarByMap(BaseXML entity, String outPath, String name) {
		return createXMLTarByMap(entity.getMap(), outPath, name);
	}

	/**
	 * 
	 * @Title: createXMLTar
	 * @Description: TODO(生成xml文件并打包成tar包)
	 * @param map
	 * @param outPath
	 * @param name
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年11月21日 下午4:04:33
	 */
	public static String createXMLTarByMap(Map<String, Object> map, String outPath, String name) {
		String tempPath = outPath+File.separatorChar+"temp";
		// 生成xml
		String xmlpath = XMLUtil.createXML(map,tempPath,"EBD","EBDB_"+name);
		// 生成签名xml
		byte[] inData = {};
		try {
			inData = FileUtil.readFile(new File(xmlpath));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		XMLUtil.createSignature(EBDcodeUtil.getBaseEBDID(),tempPath,name,inData);
		name = name.indexOf(".tar") == -1 ? name + ".tar" : name;
		outPath += File.separatorChar + "EBDT_" + name;
		pack(tempPath,outPath);
		FileUtil.delete(tempPath);
		return outPath;
	}

	/**
	 * 根据tar包路径生成对应回执tar包路径
	 * 
	 * @Title: getTarByInTar
	 * @Description: TODO(根据tar包路径生成对应回执tar包路径)
	 * @param @param tarPath
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年6月27日 上午9:13:19
	 */
	public static Map<String,Object> getTarByPath(String inTarPath, String outTarPath) {
		Map<String,Object> map = new HashMap<>();
		//读取签名文件，并验证
		Signature signature = readSignature(inTarPath);
		String sign = signature.getSignature_SignatureValue();
		//读取xml内容并生成实体类
		File file = readXML(inTarPath);
		try {
			byte[] inData = FileUtil.readFile(file);
			boolean isSign = SignatureUtil.verifySignature(inData,sign);
			map.put("isSign", isSign);
			if(!isSign) {
				return map;
			}
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		BaseXML xml = readXML(file);
		if (xml != null) {
			// 将tar包信息保存至数据库
			saveReceiveTar(xml);
			if (xml instanceof EBM) {
				try {
					WebScoketServer.startpush(inTarPath);
				} catch (Exception e) {
					logger.error("播发节点消息失败", e);
				}
			}
			BaseXML resultEntity = xml.getResponseByClass(xml);
			String resultEntityName = resultEntity.getEBD_EBDID();
			// 创建回执tar包返回路径
			outTarPath = createXMLTarByMap(resultEntity, outTarPath, resultEntityName);
			// 保存发送tar包信息
			saveSendTar(resultEntity);
			map.put("tarPath", outTarPath);
		}
		return map;
	}

	/**
	 * @Title: archiveToTemp 
	 * @Description: TODO(在tar包存放路径中创建临时文件夹并解压至该临时文件夹) 
	 * @param tarPath=tar包存放路径
	 * @return    设定文件 
	 * @return File    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月22日 上午9:36:50
	 */
	public static File archiveToTemp(String tarPath) {
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
		return tempFile;
	}
	
	/**
	 * 解压tar包，并解析xml文件返回内容实体
	 * 
	 * @Title: archiveAndgetBaseXML
	 * @Description: TODO(解压tar包，并解析xml文件返回内容实体)
	 * @param tarPath
	 * @return 设定文件
	 * @return BaseXML 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年7月17日 下午2:30:14
	 */
	public static File readXML(String tarPath) {
		File tempFile = archiveToTemp(tarPath);
		// 获取临时目录
		File[] files = tempFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 获取临时目录里面的文件
			File file = files[i];
			if (!file.isDirectory()) {
				String fileName = file.getName();
				// 获取非签名xml文件
				if (fileName.indexOf("EBDB") != -1 && fileName.indexOf("EBDS") == -1) {
					/*// 获取BaseXML
					BaseXML xml = XMLUtil.readXML(BaseXML.class,file);
					// 获取EBDType
					String EBDType = xml.getEBD_EBDType();
					// 获取实际属于哪个子类
					Class<? extends BaseXML> clazz = BaseXML.getClassByEBDType(EBDType);
					String xmlPath = file.getAbsolutePath();
					// 获取实际xml实体类
					xml = XMLUtil.readXML(clazz,xmlPath);
					// 删除临时文件
					FileUtil.delete(tempFile);
					return xml;*/
					return file;
				}
			}
		}
		return null;
	}
	public static BaseXML readXML(File file) {
		// 获取BaseXML
		BaseXML xml = XMLUtil.readXML(BaseXML.class,file);
		// 获取EBDType
		String EBDType = xml.getEBD_EBDType();
		// 获取实际属于哪个子类
		Class<? extends BaseXML> clazz = BaseXML.getClassByEBDType(EBDType);
		String xmlPath = file.getAbsolutePath();
		// 获取实际xml实体类
		xml = XMLUtil.readXML(clazz,xmlPath);
		return xml;
	}
	/**
	 * @Title: readSignature 
	 * @Description: TODO(读取签名文件) 
	 * @param tarPath
	 * @return    设定文件 
	 * @return Signature    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月22日 上午10:52:52
	 */
	public static Signature readSignature(String tarPath) {
		File tempFile = archiveToTemp(tarPath);
		// 获取临时目录
		File[] files = tempFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 获取临时目录里面的文件
			File file = files[i];
			if (!file.isDirectory()) {
				String fileName = file.getName();
				// 获取签名xml文件
				if (fileName.indexOf("EBDS") != -1) {
					// 获取Signature
					Signature xml = XMLUtil.readXML(Signature.class,file);
					// 删除临时文件
					FileUtil.delete(tempFile);
					return xml;
				}
			}
		}
		return null;
	}
	/**
	 * 保存接收到的tar包信息
	 * 
	 * @Title: saveReceiveTar
	 * @Description: TODO(重载方法保存接收到的tar包信息)
	 * @param tarPath
	 * @return 设定文件
	 * @return int 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年7月17日 下午2:27:10
	 */
	public static int saveReceiveTar(String tarPath) {
		File file = readXML(tarPath);
		BaseXML xml = readXML(file);
		return saveReceiveTar(xml);
	}

	/**
	 * 保存接收到的tar包信息
	 * 
	 * @Title: saveReceiveTar
	 * @Description: TODO(保存接收到的tar包信息)
	 * @param xml
	 * @return 设定文件
	 * @return int 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年7月17日 下午2:04:47
	 */
	public static int saveReceiveTar(BaseXML xml) {
		try {
			ReceiveTarMapper dao = ApplicationContextRegister.getBean(ReceiveTarMapper.class);
			ReceiveTar tar = dao.selectByPrimaryKey(xml.getEBD_EBDID());
			boolean isAdd = false;
			if (tar == null) {
				isAdd = true;
				tar = new ReceiveTar();
				tar.setId(xml.getEBD_EBDID());
			}
			tar.setResourceCode(xml.getSRC_EBRID());
			tar.setStatus(1);
			tar.setType(1);
			tar.setEbdType(xml.getEBD_EBDType());
			// tar.setResourceId();
			if (isAdd) {
				return dao.insertSelective(tar);
			} else {
				return dao.updateByPrimaryKeySelective(tar);
			}
		} catch (Exception e) {
			logger.error("接收tar包信息保存失败", e);
			return 0;
		}
	}

	/**
	 * 保存接发送的tar包信息
	 * 
	 * @Title: saveSendTar
	 * @Description: TODO(重载方法保存接发送的tar包信息)
	 * @param tarPath
	 * @return 设定文件
	 * @return int 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年7月17日 下午2:28:21
	 */
	public static int saveSendTar(String tarPath) {
		File file = readXML(tarPath);
		BaseXML xml = readXML(file);
		return saveSendTar(xml);
	}

	/**
	 * 保存接发送的tar包信息
	 * 
	 * @Title: saveSendTar
	 * @Description: TODO(保存接发送的tar包信息)
	 * @param xml
	 * @return 设定文件
	 * @return int 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年7月17日 下午2:05:10
	 */
	public static int saveSendTar(BaseXML xml) {
		try {
			SendTarMapper dao = ApplicationContextRegister.getBean(SendTarMapper.class);
			SendTar tar = dao.selectByPrimaryKey(xml.getEBD_EBDID());
			SendTar selectTar = tar;
			if (tar == null) {
				tar = new SendTar();
			}
			tar.setEbdid(xml.getEBD_EBDID());
			tar.setEbdType(xml.getEBD_EBDType());
			tar.setDestId(xml.getDEST_EBRID());
			if (xml instanceof ResponseXML) {
				ResponseXML response = (ResponseXML) xml;
				String code = response.getResultCode();
				if (code != null && !"".equals(code)) {
					tar.setResultCode(new Integer(code));
				}
				if (code != null && !"".equals(response.getResultDesc())) {
					tar.setResultDesc(response.getResultDesc());
				}
			}
			if (selectTar == null) {
				return dao.insertSelective(tar);
			} else {
				return dao.updateByPrimaryKeySelective(tar);
			}
		} catch (Exception e) {
			logger.error("发送tar包信息保存失败", e);
			return 0;
		}
	}

	/**
	 * @Title: pack
	 * @Description: TODO(将文件或者目录压缩成tar包)
	 * @param inPath
	 * @param outPath 设定文件
	 * @return void 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年11月21日 下午3:58:11
	 */
	public static void pack(String inPath, String outPath) {
		File inputFile = new File(inPath);
		OutputStream out = null;
		TarOutputStream tarOut = null;
		try {
			out = new FileOutputStream(outPath);
			tarOut = new TarOutputStream(out);
			if (inputFile.isDirectory()) {
				packDir(tarOut,inputFile);
			} else {
				packFile(tarOut,inputFile);
			}
		}catch(IOException e) {
			logger.error(e.getMessage(),e);
		}finally {
			try {
				if(tarOut!=null) {
					tarOut.close();
				}
				if(out!=null) {
					out.close();
				}
			}catch(IOException e) {
				logger.error(e.getMessage(),e);
			}
			
		}
	}

	/**
	 * @Title: packDir
	 * @Description: TODO(将目录压缩成tar包)
	 * @param file
	 * @param outPath 设定文件
	 * @return void 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年11月21日 下午3:58:40
	 */
	public static void packDir(TarOutputStream tarOut,File file) {
		File[] files = file.listFiles();
		for (File f : files) {
			packFile(tarOut,f);
		}
	}

	/**
	 * @Title: packFile
	 * @Description: TODO(将文件压缩成tar包)
	 * @param file
	 * @param outPath 设定文件
	 * @return void 返回类型
	 * @throws @author peiyongdong
	 * @date 2018年11月21日 下午3:59:01
	 */
	public static void packFile(TarOutputStream tarOut,File file) {
		InputStream in = null;
		try {
			tarOut.putNextEntry(new TarEntry(file, file.getName()));
			in = new FileInputStream(file);
			FileUtil.wirteFile(in, tarOut,false);
		} catch (IOException e) {
			logger.error("创建tar包出错：" + e);
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
	}
}
