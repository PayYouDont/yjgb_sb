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

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.EBD_ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.EBD_EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.EBD_Signature;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket.WebScoketServer;

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
		if(StringUtils.isEmpty(inPath)) {
			return;
		}
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
		if(StringUtils.isEmpty(inPath)) {
			return;
		}
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
		if(StringUtils.isEmpty(tarPath)) {
			return null;
		}
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
	public static File readTar(String tarPath) {
		return readTar(tarPath,null);
	}
	public static File readTar(String tarPath,String EBDType) {
		File tempFile = archiveToTemp(tarPath);
		if(tempFile==null) {
			return null;
		}
		if(tempFile.isDirectory()) {
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
		}
		return null;
	}
	public static Map<String,Object> getTarByPath(String inTarPath, String outTarPath) {
		if(StringUtils.isEmpty(inTarPath)) {
			return null;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("isSign", false);
		//读取xml内容并生成实体类
		File ebdFile = readTar(inTarPath);
		EBD ebd = XMLUtil.readXMLToBean(ebdFile);
		if(ebd == null) {
			return map;
		}
		EBD_EBDResponse response = new EBD_EBDResponse();
		if(!(ebd instanceof EBD_ConnectionCheck)) {
			//读取签名文件，并验证
			File signFile = readTar(inTarPath,"sign");
			// 获取Signature
			EBD_Signature signature = (EBD_Signature) XMLUtil.readXMLToBean(signFile);
			String sign = signature.getSignature().getSignatureValue();
			//验证签名
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
		}else {
			map.put("isSign", true);
		}
		if(ebd instanceof EBD_EBD) {
			try {
				WebScoketServer.startpush(inTarPath);
			} catch (Exception e) {
				logger.error("播发节点消息失败", e);
			}
		}
		// 删除临时文件
		FileUtil.delete(ebdFile.getParent());
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
	
	public static EBD_EBDResponse getEBDResponse(String result){
		if(StringUtils.isEmpty(result)) {
			return null;
		}
		File xmlFile = readTar(result);
		EBD ebd = XMLUtil.readXMLToBean(xmlFile);
		if(ebd instanceof EBD_EBDResponse){
			EBD_EBDResponse response = (EBD_EBDResponse)ebd;
			return response;
		}
		return null;
	}
}
