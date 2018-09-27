/**   
* @Title: MediaResoceVO.java 
* @Package com.gospell.chitong.rdcenter.broadcast.broadcastMange.vo 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年9月26日 下午4:07:36 
*/
package com.gospell.chitong.rdcenter.broadcast.broadcastMange.vo;

import org.springframework.web.multipart.MultipartFile;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;

import lombok.Data;

/** 
* @ClassName: MediaResoceVO 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月26日 下午4:07:36 
*  
*/
@Data
public class MediaResouceVO {
	private Integer id;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String source;
    private Integer status;
    private String introduction;
    private MultipartFile file;
    public MediaResouce getMediaResouce() {
    	MediaResouce resouce = new MediaResouce();
    	if(id!=null) {
    		resouce.setId(id);
    	}
    	resouce.setFileName(fileName);
    	resouce.setFileType(fileType);
    	resouce.setFileSize(fileSize);
    	resouce.setSource(source);
    	resouce.setStatus(status);
    	if(introduction!=null) {
    		resouce.setIntroduction(introduction);
    	}
    	return resouce;
    }
}
