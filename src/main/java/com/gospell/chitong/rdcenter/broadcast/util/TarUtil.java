package com.gospell.chitong.rdcenter.broadcast.util;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_Signature;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.EBD_EBM;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.SendTarService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket.WebScoketServer;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.xeustechnologies.jtar.TarEntry;
import org.xeustechnologies.jtar.TarInputStream;
import org.xeustechnologies.jtar.TarOutputStream;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
	* @Author peiyongdong
	* @Description ( 将目录压缩成tar包 )
	* @Date 11:40 2019/4/11
	* @Param [tarOut, file]
	* @return void
	**/
	public static void packDir(TarOutputStream tarOut,File file) {
		File[] files = file.listFiles();
		for (File f : files) {
			packFile(tarOut,f);
		}
	}

	/**
	* @Author peiyongdong
	* @Description ( 将文件压缩成tar包 )
	* @Date 11:40 2019/4/11
	* @Param [tarOut, file]
	* @return void
	**/
	public static void packFile(TarOutputStream tarOut,File file) {
		InputStream in = null;
		try {
            TarEntry tarEntry = new TarEntry(file, file.getName());
			tarOut.putNextEntry(tarEntry);
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
	/**
	* @Author peiyongdong
	* @Description ( 解压tar)
	* @Date 09:21 2019/5/24
	* @Param [inPath, outPath]
	* @return void
	**/
	public static void archive(String inPath, String outPath) {
		if(StringUtils.isEmpty(inPath)) {
			return;
		}
		File file = new File(inPath);
		TarInputStream in = null;
		try {
			in = new TarInputStream(new FileInputStream(file));
			TarEntry entry;
			while ((entry = in.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				File outFile = new File(outPath + File.separatorChar + entry.getName());
				new File(outFile.getParent()).mkdirs();
				OutputStream out = new FileOutputStream(outFile);
				byte[] b = new byte[1024];
				int len;
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
	/**
	* @Author peiyongdong
	* @Description (将新文件添加至tar)
	* @Date 09:21 2019/5/24
	* @Param [tarPath, filePath, fileName]
	* @return void
	**/
    public static void addFileToTar(String tarPath,String filePath,String fileName){
	    String intTarPath = archiveToTemp (tarPath).getPath ();
	    FileUtil.copyFile (filePath,intTarPath,fileName);
	    FileUtil.delete (tarPath);
	    pack (intTarPath,tarPath);
	    FileUtil.delete (intTarPath);
    }
    /**
    * @Author peiyongdong
    * @Description (读取tar)
    * @Date 09:21 2019/5/24
    * @Param [tarPath]
    * @return java.io.File
    **/
	public static File readTar(String tarPath) {
		return readTar(tarPath,null);
	}
	/**
	* @Author peiyongdong
	* @Description (读取tar)
	* @Date 09:21 2019/5/24
	* @Param [tarPath, EBDType]
	* @return java.io.File
	**/
	public static File readTar(String tarPath,String EBDType) {
		File tempFile = archiveToTemp(tarPath);
		if(tempFile==null) {
			return null;
		}
		if(tempFile.isDirectory()) {
			// 获取临时目录
			File[] files = tempFile.listFiles();
			if(EBDType!=null&&EBDType.toLowerCase().indexOf(".mp3")!=-1){
				EBDType = "MP3";
			}else if (EBDType!=null&&EBDType.indexOf("sign")!=-1){
				EBDType = "EBDS";
			}else {
				EBDType = "EBDB";
			}
			for (int i = 0; i < files.length; i++) {
				// 获取临时目录里面的文件
				File file = files[i];
				if (!file.isDirectory()) {
					String fileName = file.getName();
					// 获取签名xml文件
					if (EBDType.equals("EBDS")&&fileName.indexOf("EBDS") != -1) {
						return file;
					}else if (EBDType.equals("EBDB")&&fileName.indexOf("EBDB") != -1&& fileName.indexOf("EBDS") == -1) {
						return file;
					}else if (EBDType.equals("MP3")&&fileName.toLowerCase().indexOf(".mp3")!=-1){
						return file;
					}
				}
			}
		}
		return null;
	}
	/**
	* @Author peiyongdong
	* @Description (根据tar路径获取tar相关信息)
	* @Date 09:22 2019/5/24
	* @Param [inTarPath, outTarPath]
	* @return java.util.Map<java.lang.String,java.lang.Object>
	**/
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
        boolean isSign = true;
		ServerProperties serverProperties = ApplicationContextRegister.getBean(ServerProperties.class);
		if(!(ebd instanceof EBD_ConnectionCheck)&&serverProperties.isCheckSign()) {//心跳文件不验证签名
			//读取签名文件，并验证
			File signFile = readTar(inTarPath,"sign");
			// 获取Signature
			EBD_Signature signature = (EBD_Signature) XMLUtil.readXMLToBean(signFile);
			String sign = signature.getSignature().getSignatureValue();
			//验证签名
			try {
				byte[] inData = FileUtil.readFile(ebdFile);
				isSign = SignatureUtil.verifySignature(inData,sign);
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
				logger.info("验证:"+ebdFile.getName()+"时签名错误,路径为：",inTarPath);
			}
		}else {
		    //心跳不验证签名
            isSign = true;
		}
        map.put("isSign", isSign);
		if(isSign && ebd instanceof EBD_EBM) {
			try {
				WebScoketServer.startpush(inTarPath);
			} catch (Exception e) {
				logger.error("播发节点消息失败", e);
			}
		}
		map.put("ebd", ebd);
		// 删除临时文件
		FileUtil.delete(ebdFile.getParent());
		return map;
	}
	/**
	 * @Author peiyongdong
	 * @Description (根据实体类创建tar)
	 * @Date 09:22 2019/5/24
	 * @Param [entity, outPath, name, type]
	 * @return java.lang.String
	 **/
	public static String createXMLTarByBean(EBD entity, String outPath, String name) {
		String tempPath = outPath+File.separatorChar+"temp_"+name;
		ServerProperties serverProperties = ApplicationContextRegister.getBean(ServerProperties.class);
		// 生成xml
		String xmlpath = XMLUtil.createXMLByBean(entity, tempPath, "EBDB_"+name);
		if (serverProperties.isCreateSign()){
			// 生成签名xml
			byte[] inData = {};
			try {
				inData = FileUtil.readFile(new File(xmlpath));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			XMLUtil.createSignature(entity.getEBD().getEBDID(),tempPath,name,inData);
		}
		name = name.indexOf(".tar") == -1 ? name + ".tar" : name;
		outPath += File.separatorChar + "EBDT_" + name;
		pack(tempPath,outPath);
		FileUtil.delete(tempPath);
		return outPath;
	}
	/**
	* @Author peiyongdong
	* @Description (根据回执tar路径获取回执文件实体)
	* @Date 09:23 2019/5/24
	* @Param [result]
	* @return com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse
	**/
	public static EBD_EBDResponse getEBDResponse(String result){
		if(StringUtils.isEmpty(result)) {
			return null;
		}
		File xmlFile = readTar(result);
		EBD ebd = XMLUtil.readXMLToBean(xmlFile);
		FileUtil.delete(xmlFile.getParent());
		if(ebd instanceof EBD_EBDResponse){
			EBD_EBDResponse response = (EBD_EBDResponse)ebd;
			return response;
		}
		LoggerUtil.print(TarUtil.class,result+"：解析结果为空");
		return null;
	}
	/**
	* @Author peiyongdong
	* @Description (向上级平台发送tar)
	* @Date 09:23 2019/5/24
	* @Param [ebd]
	* @return java.lang.String
	**/
	public static String sendEBDToSuperior(EBD ebd) throws Exception{
        ServerProperties serverProperties = ApplicationContextRegister.getBean (ServerProperties.class);
        String outPath = serverProperties.getTarOutPath ();
	    boolean isHeart = false;
	    if(ebd instanceof EBD_ConnectionCheck){
			isHeart = true;
		}
		String tarPath = createXMLTarByBean (ebd, outPath, ebd.getEBD ().getEBDID ());
        String result = HttpClientUtil.sendPostFile (serverProperties.getSuperiorUrl (), tarPath,isHeart);
        EBD_EBDResponse response = getEBDResponse (result);
        ApplicationContextRegister.getBean (SendTarService.class).saveSendTar (ebd,response);
	    return result;
    }
}
