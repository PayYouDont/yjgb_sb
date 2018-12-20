
package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.MediaResouceMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.vo.MediaResouceVO;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

/** 
* @ClassName: IMediaResouceService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月25日 下午4:49:06 
*  
*/
@Service
public class IMediaResouceService implements MediaResouceService{
	@Resource
	private MediaResouceMapper dao;
	@Value("${upload.file.location}")
	private String uploadLocation;
	
	
	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#delete(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月25日 下午4:49:41
	 */
	@Override
	@Transactional
	public int delete(Integer id) throws Exception {
		String filePath = selectById(id).getFilePath();
		FileUtil.delete(filePath);
		return dao.deleteByPrimaryKey(id);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param record
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#save(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月25日 下午4:49:41
	 */
	@Override
	public int save(MediaResouce record) throws Exception {
		if(record.getId()!=null) {
			record.setUpdateBy(ShiroUtils.getUser().getName());
			return dao.updateByPrimaryKeySelective(record);
		}
		record.setCreateBy(ShiroUtils.getUser().getName());
		return dao.insertSelective(record);
	}

	/** 
	 * <p>Title: selectById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#selectById(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月25日 下午4:49:41
	 */
	@Override
	public MediaResouce selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param vo
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService#save(com.gospell.chitong.rdcenter.broadcast.broadcastMange.vo.MediaResouceVO) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月27日 上午9:03:30
	 */
	@Override
	@Transactional
	public int save(MediaResouceVO vo) throws Exception {
		MediaResouce resouce = vo.getMediaResouce();
		Integer id = resouce.getId();
		MultipartFile file = vo.getFile();
		if(id!=null&&file!=null) {
			MediaResouce media = selectById(id);
			String filePath = media.getFilePath();
			FileUtil.delete(filePath);
		}
		if(file!=null) {
			InputStream in = file.getInputStream();
			String outFileName = resouce.getFileName();
			outFileName = outFileName.indexOf(".")==-1?outFileName+".mp3":outFileName;
			String outFilePath = uploadLocation +File.separator+ outFileName;
			File outFile = new File(outFilePath);
			OutputStream out = new FileOutputStream(outFile);
			FileUtil.wirteFile(in, out);
			resouce.setFilePath(outFilePath);
		}
		return save(resouce);
	}

	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService#list(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月27日 上午9:44:16
	 */
	@Override
	public List<MediaResouce> list(Map<String, Object> map) {
		return dao.list(map);
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月27日 上午9:44:16
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}
	
}