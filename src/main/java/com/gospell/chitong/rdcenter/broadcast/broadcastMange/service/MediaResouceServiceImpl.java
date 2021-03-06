
package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.MediaResouceMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.vo.MediaResouceVO;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: IMediaResouceService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月25日 下午4:49:06 
*  
*/
@Service
@Getter
public class MediaResouceServiceImpl implements MediaResouceService{
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
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#(java.lang.Integer)
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
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#(java.lang.Object)
	 * @throws
	 * @author peiyongdong
	 * @date 2018年9月25日 下午4:49:41
	 */
	@Override
	public int save(MediaResouce record) throws Exception {
		if(record.getId()!=null) {
			if (ShiroUtils.getUser()!=null){
				record.setUpdateBy(ShiroUtils.getUser().getName());
			}
			return dao.updateByPrimaryKeySelective(record);
		}
		if (ShiroUtils.getUser()!=null){
			record.setCreateBy(ShiroUtils.getUser().getName());
		}
		return dao.insertSelective(record);
	}

	/** 
	 * <p>Title: selectById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#(java.lang.Integer)
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
			String outFilePath = uploadLocation + File.separator+"EBM_media"+File.separator+ outFileName;
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
		List<MediaResouce> mediaResouceList = dao.list(map);
		List<MediaResouce> mediaResouces = new ArrayList<>();
        String uploadPath = uploadLocation + File.separator+"EBM_media";
		File[] files = new File(uploadPath).listFiles();
		mediaResouceList.forEach(mediaResouce -> {
			String resouceName = mediaResouce.getFileName();
			for (int i=0;i<files.length;i++){
				String fileName = files[i].getName();
				if (resouceName.equals(fileName)){//只加载资源所在目录中还存在的资源
					mediaResouces.add(mediaResouce);
					break;
				}
			}
		});
		return mediaResouces;
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

    @Override
    public Integer saveByTarPath(String tarPath) throws Exception{
        File mp3File = TarUtil.readTar(tarPath,".mp3");
        String uploadPath = uploadLocation + File.separator+"EBM_media";
        String mp3Path = FileUtil.copyFile(mp3File,uploadPath,null);
        FileUtil.delete (mp3File.getParentFile ());
        Map<String,Object> map = new HashMap<> ();
        map.put("fileName",mp3File.getName ());
        map.put("fileSize",mp3File.length ());
        List<MediaResouce> mediaResouceList = list(map);
        MediaResouce mediaResouce;
        if (mediaResouceList!=null&&mediaResouceList.size()>0){
            mediaResouce = mediaResouceList.get(0);
        }else{
            mediaResouce = new MediaResouce();
            mediaResouce.setFileType("mp3");
        }
        mediaResouce.setFileSize(mp3File.length ());
        mediaResouce.setFileName(mp3File.getName ());
        mediaResouce.setFilePath(mp3Path);
        mediaResouce.setSource("上级转发");
        save(mediaResouce);
	    return mediaResouce.getId ();
    }
}
