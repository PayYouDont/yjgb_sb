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
import org.springframework.util.StringUtils;
import org.xeustechnologies.jtar.TarEntry;
import org.xeustechnologies.jtar.TarInputStream;
import org.xeustechnologies.jtar.TarOutputStream;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.ReceiveTarMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.SendTarMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ReceiveTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.SendTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD_ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD_EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD_Signature;
import com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket.WebScoketServer;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;

public class TarUtil {

	public static final Logger logger = LoggerFactory.getLogger(TarUtil.class);
	
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
	public static File archiveToTemp(String tarPath) {
		// d:/tar/xxxxx.tar
		String temDir = tarPath.substring(0, tarPath.lastIndexOf(File.separatorChar));
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
	public static File readEBD(String tarPath) {
		return readEBD(tarPath,null);
	}
	public static File readEBD(String tarPath,String EBDType) {
		File tempFile = archiveToTemp(tarPath);
		// 获取临时目录
		File[] files = tempFile.listFiles();
		EBDType = EBDType!=null&&EBDType.indexOf("sign")!=-1?"EBDS":"EBDB";
		for (int i = 0; i < files.length; i++) {
			// 获取临时目录里面的文件
			File file = files[i];
			if (!file.isDirectory()) {
				String fileName = file.getName();
				// 获取签名xml文件
				if (EBDType.equals("EBDS")&&fileName.indexOf("EBDS") != -1) {
					return file;
				}else if (EBDType.equals("EBDB")
						&&fileName.indexOf("EBDB") != -1 
						&& fileName.indexOf("EBDS") == -1) {
					return file;
				}
			}
		}
		return null;
	}
	public static int saveReceiveTar(EBD ebd) {
		try {
			ReceiveTarMapper dao = ApplicationContextRegister.getBean(ReceiveTarMapper.class);
			ReceiveTar tar = dao.selectByPrimaryKey(ebd.getEBD().getEBDID());
			boolean isAdd = false;
			if (tar == null) {
				isAdd = true;
				tar = new ReceiveTar();
				tar.setId(ebd.getEBD().getEBDID());
			}
			tar.setResourceCode(ebd.getEBD().getSRC().getEBRID());
			tar.setStatus(1);
			tar.setType(1);
			tar.setEbdType(ebd.getEBD().getEBDType());
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
	public static int saveSendTar(EBD ebd) {
		try {
			SendTarMapper dao = ApplicationContextRegister.getBean(SendTarMapper.class);
			SendTar tar = dao.selectByPrimaryKey(ebd.getEBD().getEBDID());
			SendTar selectTar = tar;
			if (tar == null) {
				tar = new SendTar();
			}
			tar.setEbdid(ebd.getEBD().getEBDID());
			tar.setEbdType(ebd.getEBD().getEBDType());
			tar.setDestId(ebd.getEBD().getDEST().getEBRID());
			/*if (xml instanceof ResponseXML) {
				ResponseXML response = (ResponseXML) xml;
				String code = response.getResultCode();
				if (code != null && !"".equals(code)) {
					tar.setResultCode(new Integer(code));
				}
				if (code != null && !"".equals(response.getResultDesc())) {
					tar.setResultDesc(response.getResultDesc());
				}
			}*/
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
	public static Map<String,Object> getTarByPath(String inTarPath, String outTarPath) {
		Map<String,Object> map = new HashMap<>();
		//读取xml内容并生成实体类
		File ebdFile = readEBD(inTarPath);
		EBD ebd = XMLUtil.readXMLToBean(ebdFile);
		EBD_EBDResponse response = new EBD_EBDResponse();
		if(!(ebd instanceof EBD_ConnectionCheck)) {
			//读取签名文件，并验证
			File signFile = readEBD(inTarPath,"sign");
			// 获取Signature
			EBD_Signature signature = (EBD_Signature) XMLUtil.readXMLToBean(signFile);
			String sign = signature.getSignature().getSignatureValue();
			try {
				byte[] inData = FileUtil.readFile(ebdFile);
				boolean isSign = SignatureUtil.verifySignature(inData,sign);
				map.put("isSign", isSign);
				if(!isSign) {
					response.getEBD().getEBDResponse().setResultCode(""+EBD_EBDResponse.SIGN_VERIF_FAILED);
					response.getEBD().getEBDResponse().setResultDesc("签名验证未通过");
				}
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
				response.getEBD().getEBDResponse().setResultCode(""+EBD_EBDResponse.OTHER_ERROR);
				response.getEBD().getEBDResponse().setResultDesc("签名错误");
			}
		}else{
			map.put("isSign", true);
		}
		// 删除临时文件
		FileUtil.delete(ebdFile.getParent());
		if(ebd != null) {
			// 将tar包信息保存至数据库
			saveReceiveTar(ebd);
			if(ebd instanceof EBD_EBD) {
				try {
					WebScoketServer.startpush(inTarPath);
				} catch (Exception e) {
					logger.error("播发节点消息失败", e);
				}
			}
		}
		map.put("tarPath", TarUtil.createXMLTarByBean(response, outTarPath, response.getEBD().getEBDID()));
		map.put("ebd", ebd);
		return map;
	}
	public static String createXMLTarByBean(EBD entity, String outPath, String name) {
		String tempPath = outPath+File.separatorChar+"temp";
		// 生成xml
		String xmlpath = XMLUtil.createXMLByBean(entity, tempPath, "EBDB_"+name);
		// 生成签名xml
		byte[] inData = {};
		try {
			inData = FileUtil.readFile(new File(xmlpath));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		XMLUtil.createSignature(entity.getEBD().getEBDID(),tempPath,name,inData);
		name = name.indexOf(".tar") == -1 ? name + ".tar" : name;
		outPath += File.separatorChar + "EBDT_" + name;
		pack(tempPath,outPath);
		FileUtil.delete(tempPath);
		return outPath;
	}
	
	public static void checkEBDResponse(String result) throws Exception{
		if(StringUtils.isEmpty(result)) {
			throw new NullPointerException("result is null");
		}
		File xmlFile = readEBD(result);
		EBD ebd = XMLUtil.readXMLToBean(xmlFile);
		if(ebd instanceof EBD_EBDResponse){
			EBD_EBDResponse response = (EBD_EBDResponse)ebd;
			Integer resultCode = new Integer(response.getEBD().getEBDResponse().getResultCode());
			if(resultCode!=EBD_EBDResponse.SUCCESS) {
				throw new RuntimeException(response.getEBD().getEBDResponse().getResultDesc());
			}
		}else {
			throw new ClassCastException("This file is not EBDResponse file");
		}
	}
}
