package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.DateJsonValueProcessor;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.NodeMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Service
public class INodeService implements NodeService {

	@Resource
	private NodeMapper dao;

	@Override
	public int deleteById(Integer id) throws Exception {
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
			count += deleteById(ids[i]);
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
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		return JSONArray.fromObject(nodes,jsonConfig).toString();
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
	public String receiveTar(HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest mhsRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = mhsRequest.getFileNames();
		String fileName = "";
		while(iter.hasNext()) {
			fileName = iter.next(); 
		}
		MultipartFile mfile= mhsRequest.getFile(fileName);
		String contentType = mfile.getContentType();
		if(!contentType.equals("application/x-tar")) {
			new RuntimeException("上传的文件不是x-tar类型");
		}
        File root = new File("D:\\tar");
		if(!root.exists()) {
        	root = new File("");
        }
		String outPath = root.getAbsolutePath()+File.separatorChar+"get";
		//将接收到的tar包写入指tar文件夹
        String tarPath = FileUtil.copyFile(mfile.getInputStream(), outPath, mfile.getOriginalFilename());
        //解析接收到的tar包并生成对应的回复tar包
        String outTarPath = TarUtil.getTarByInTar(tarPath);
        return outTarPath;
	}
}
