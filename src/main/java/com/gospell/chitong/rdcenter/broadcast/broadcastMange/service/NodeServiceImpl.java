package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.NodeMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_Signature;
import com.gospell.chitong.rdcenter.broadcast.util.*;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class NodeServiceImpl implements NodeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private NodeMapper dao;
    @Resource
    private ServerProperties prop;

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
     *
     * @param nodes
     * @return
     * @throws
     * @author peiyongdong
     * @date 2018年6月14日 下午2:16:32
     * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService#checkNodesToJsonStr(java.util.List)
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
    public Map<String, Object> receiveTar(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        MultipartHttpServletRequest mhsRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iter = mhsRequest.getFileNames();
        String fileName = "";
        while (iter.hasNext()) {
            fileName = iter.next();
        }
        MultipartFile mfile = mhsRequest.getFile(fileName);
        String basePath = prop.getTarInPath();
        //将接收到的tar包写入指定tar文件夹
        String tarPath = FileUtil.copyFile(mfile.getInputStream(), basePath, mfile.getOriginalFilename());
        boolean isTar = FileToolsUtil.getFileType(tarPath).indexOf("tar") != -1;
        map.put("isTar", isTar);
        if (!isTar) {
            return map;
        }
        String outPath = prop.getTarOutPath();
        //解析接收到的tar包并生成对应的回复tar包
        map.putAll(TarUtil.getTarByPath(tarPath, outPath));
        return map;
    }

    /**
     * 验证签名文件并生成对应的回执文件
     * @param tarPath
     * @return
     */
    @Override
    public EBD_EBDResponse createResponse(String tarPath) {
        String outPath = prop.getTarOutPath();
        TarUtil.getTarByPath(tarPath, outPath);
        //读取xml内容并生成实体类
        File ebdFile = TarUtil.readTar(tarPath);
        EBD ebd = XMLUtil.readXMLToBean(ebdFile);
        EBD_EBDResponse response = new EBD_EBDResponse();
        if(!(ebd instanceof EBD_ConnectionCheck)) {//心跳文件不验证签名
            //读取签名文件，并验证
            File signFile = TarUtil.readTar(tarPath,"sign");
            // 获取Signature
            EBD_Signature signature = (EBD_Signature) XMLUtil.readXMLToBean(signFile);
            String sign = signature.getSignature().getSignatureValue();
            //验证签名
            try {
                byte[] inData = FileUtil.readFile(ebdFile);
                if(!SignatureUtil.verifySignature(inData,sign)) {
                    response.getEBD().getEBDResponse().setResultCode(""+EBD_EBDResponse.SIGN_VERIF_FAILED);
                    response.getEBD().getEBDResponse().setResultDesc("签名验证未通过");
                }
            }catch(Exception e) {
                logger.error(e.getMessage(),e);
                response.getEBD().getEBDResponse().setResultCode(""+EBD_EBDResponse.OTHER_ERROR);
                response.getEBD().getEBDResponse().setResultDesc("签名错误");
            }
        }
        return response;
    }
    /**
     * 根据tar包获取tar包内容对应实体类
     *
     * @param tarfile
     * @return EBM
     */
    /*@Override
    public EBD getEbmFromTar(File tarfile) {
        String filepath = tarfile.getPath();
        //读取xml内容并生成实体类
        File ebdFile = TarUtil.readTar(filepath);
        EBD ebd = XMLUtil.readXMLToBean(ebdFile);
        if (ebd instanceof EBD_EBM) {
            EBD_EBM EBD_EBM = (EBD_EBM) ebd;
            EBD_EBM.EBM ebm = EBD_EBM.getEBD().getEBM();
            String ebmId = ebm.getEBMID();
            Map<String, Object> map = new HashMap<>();
            *//*map.put("ebmId",ebmId);
            List<EBD_EBM_EmerRelation> relations = ApplicationContextRegister.getBean(EBD_EBM_EmerRelationService.class).list(map);
           *//*
            if (ebm.getMsgContent() != null) {
                EBD_EBM.Auxiliary auxiliary = ebm.getMsgContent().getAuxiliary();
                if (auxiliary != null) {//播放请求且有MP3
                    String resurceName = auxiliary.getAuxiliaryDesc();
                    map = new HashMap<>();
                    map.put("fileName", resurceName);
                    map.put("fileSize", auxiliary.getSize());
                    MediaResouceServiceImpl mediaResouceService = ApplicationContextRegister.getBean(MediaResouceServiceImpl.class);
                    List<MediaResouce> mediaResouces = mediaResouceService.list(map);
                    MediaResouce mediaResouce;
                    if (mediaResouces != null && mediaResouces.size() > 0) {
                        mediaResouce = mediaResouces.get(0);
                    } else {
                        mediaResouce = new MediaResouce();
                        mediaResouce.setFileName(resurceName);
                        mediaResouce.setSource("上级转发");
                        mediaResouce.setFileType("mp3");
                        mediaResouce.setStatus(1);//审核通过
                    }
                    File resouceFile = TarUtil.readTar(filepath, ".mp3");
                    String mediaPath = mediaResouceService.getUploadLocation();
                    String resoucePath = FileUtil.copyFile(resouceFile.getPath(), mediaPath, resurceName);
                    mediaResouce.setFilePath(resoucePath);
                    mediaResouce.setFileSize(resouceFile.length());
                    try {
                        mediaResouceService.save(mediaResouce);
                    } catch (Exception e) {
                        logger.info(e.getMessage(), e);
                    }
                }
            }
        }
        return ebd;
    }*/
}
