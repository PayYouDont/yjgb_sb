package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.NodeMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.FileToolsUtil;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;
import com.gospell.chitong.rdcenter.broadcast.util.XMLUtil;

@Service
public class INodeService implements NodeService {

	@Resource
	private NodeMapper dao;

	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public Node selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int save(Node node) throws Exception {
		int i = 0;
		if (node.getId() != null) {
			node.setUpdateBy(ShiroUtils.getUser().getName());
			i = dao.updateByPrimaryKeySelective(node);
		} else {
			node.setCreateBy(ShiroUtils.getUser().getName());
			i = dao.insertSelective(node);
		}
		return i;
	}

	@Override
	public List<Node> list(Map<String, Object> map) {
		List<Node> list = dao.list(map);
		return list;
	}

	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	@Override
	public int deleteByIds(Integer[] ids) throws Exception {
		int count = 0;
		for (int i = 0; i < ids.length; i++) {
			count += delete(ids[i]);
		}
		return count;
	}

	/**
	 * 检查node集合连接状态，将结果list转成json字符串，用于webscoket传递(暂停使用)
	 * <p>Title: checkNodesToJsonStr</p> 
	 * <p>Description: </p> 
	 * @param nodes
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService#checkNodesToJsonStr(java.util.List) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月14日 下午2:16:32
	 */
	@Override
	public String checkNodesToJsonStr(List<Node> nodes) {
		for (Node node : nodes) {
			int status = -1;
			if (node.getNodeStatus() == 0) {
				continue;
			}
			try {
				status = HttpClientUtil.checkNode(node);
			} catch (ClientProtocolException e) {
				status = -1;
			} catch (IOException e) {
				status = -2;
			}
			node.setLinkStatus(status);
		}
		return JsonUtil.toJson(nodes);
	}

	@Override
	public List<Node> checkNodes(List<Node> nodes) {
		for (Node node : nodes) {
			int status = -1;
			if (node.getNodeStatus() == 0) {
				continue;
			}
			try {
				status = HttpClientUtil.checkNode(node);
			} catch (ClientProtocolException e) {
				status = -1;
			} catch (IOException e) {
				status = -2;
			}
			node.setLinkStatus(status);
		}
		return nodes;
	}

	@Override
	public Map<String,Object> receiveTar(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<>();
		MultipartHttpServletRequest mhsRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = mhsRequest.getFileNames();
		String fileName = "";
		while(iter.hasNext()) {
			fileName = iter.next(); 
		}
		MultipartFile mfile= mhsRequest.getFile(fileName);
		
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		String basePath = prop.getTarInPath();
		//将接收到的tar包写入指定tar文件夹
        String tarPath = FileUtil.copyFile(mfile.getInputStream(), basePath, mfile.getOriginalFilename());
        boolean isTar = FileToolsUtil.getFileType(tarPath).indexOf("tar")!=-1;
		map.put("isTar", isTar);
		if(!isTar) {
			return map;
		}
        String outPath = prop.getTarOutPath();
        //解析接收到的tar包并生成对应的回复tar包
        map.putAll(TarUtil.getTarByPath(tarPath,outPath));
        return map;
	}
	
	/**
	 * 根据tar包获取tar包内容对应实体类
	 * @param tarfile
	 * @return EBM
	 */
	@Override
	public EBD getEbmFromTar(File tarfile){
		String filepath = tarfile.getPath();
		String outPath = filepath.substring(0,filepath.lastIndexOf('.'));
		TarUtil.archive(filepath,outPath);
		String xmlPath = refreshFileList(outPath);
		EBD ebd = XMLUtil.readXMLToBean(xmlPath);
		FileUtil.delete(outPath);
		return ebd;
	}
	/**
	 * 循环文件夹找xml文件
	 * @param strPath 文件夹路径
	 * @return
	 */
	public  String refreshFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();
		String xmlPath =null;
		if (files == null)
			return null;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				refreshFileList(files[i].getAbsolutePath());
			} else {
				String strFileName = files[i].getAbsolutePath();
				if (strFileName.indexOf("EBDB") !=-1 && strFileName.indexOf("EBDS") == -1) {
					xmlPath = files[i].getPath();
				}
			}
		}
		return xmlPath;
	}
}
