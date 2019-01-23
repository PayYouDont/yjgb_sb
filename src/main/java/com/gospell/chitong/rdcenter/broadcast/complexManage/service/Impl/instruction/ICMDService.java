package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.instruction;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.BaseDao;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.*;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.*;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CMDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ICMDService implements CMDService {

    @Resource
    private CmdTimeMapper timeDao;
    @Resource
    private CmdFreqMapper freqDao;
    @Resource
    private CmdParameterMapper parameterDao;
    @Resource
    private CmdRebackPeriodMapper rebackPeriodDao;
    @Resource
    private CmdRebackTypeMapper rebackTypeDao;
    @Resource
    private CmdResourceCodeMapper resourceCodeDao;
    @Resource
    private CmdVolumeMapper volumeDao;

    @Override
    public int delete(Integer id,Class<? extends CMD> clazz) throws Exception {
        BaseDao<?,Integer> dao = getDao ( clazz );
        return dao.deleteByPrimaryKey ( id );
    }

    @Override
    public int save(CMD record,Class<? extends CMD> clazz) throws Exception {
        BaseDao<CMD,Integer> dao = (BaseDao<CMD,Integer> )getDao ( clazz );
        if(record.getId ()==null){
            return dao.insertSelective ( record );
        }
        return dao.updateByPrimaryKeySelective ( record );
    }

    @Override
    public CMD selectById(Integer id,Class<? extends CMD> clazz) {
        BaseDao<CMD,Integer> dao = (BaseDao<CMD,Integer> )getDao ( clazz );
        if(dao == null){
            return null;
        }
        return dao.selectByPrimaryKey ( id );
    }

    public BaseDao<?,Integer> getDao(Class<? extends CMD> clazz){
       if(clazz==CMDTime.class){
           return timeDao;
       }else if (clazz == CMDFreq.class){
           return  freqDao;
       }else if(clazz == CMDParameter.class){
           return parameterDao;
       }else if(clazz == CMDRebackPeriod.class){
           return rebackPeriodDao;
       }else if(clazz == CMDRebackType.class){
           return rebackTypeDao;
       }else if(clazz == CMDResourceCode.class){
           return resourceCodeDao;
       }else if(clazz == CMDVolume.class){
           return volumeDao;
       }
        return null;
    }

}
