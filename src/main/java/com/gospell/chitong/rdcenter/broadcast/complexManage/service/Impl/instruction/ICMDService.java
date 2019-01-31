package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.instruction;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.BaseDao;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdFreqMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdParameterMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdRebackPeriodMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdRebackTypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdResourceCodeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdTimeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdVolumeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMD;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDFreq;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDParameter;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDRebackPeriod;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDRebackType;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDResourceCode;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDTime;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDVolume;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CMDService;
import com.gospell.chitong.rdcenter.broadcast.util.ReflecUtil;
import com.gospell.chitong.rdcenter.broadcast.util.StringUtil;

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
	public int delete(Integer id, Class<? extends CMD> clazz) throws Exception {
		BaseDao<?, Integer> dao = getDao(clazz);
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int save(CMD record, Class<? extends CMD> clazz) throws Exception {
		BaseDao<CMD, Integer> dao = getDao(clazz);
		if (record.getId() == null) {
			return dao.insertSelective(record);
		}
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public CMD selectById(Integer id, Class<? extends CMD> clazz) {
		BaseDao<CMD, Integer> dao = getDao(clazz);
		if (dao == null) {
			return null;
		}
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<CMD> list(Map<String, Object> map, Class<? extends CMD> clazz) {
		BaseDao<CMD, Integer> dao = getDao(clazz);
		if (dao == null) {
			return null;
		}
		return dao.list(map);
	}

	@Override
	public int count(Map<String, Object> map, Class<? extends CMD> clazz) {
		BaseDao<CMD, Integer> dao = (BaseDao<CMD, Integer>) getDao(clazz);
		if (dao == null) {
			return -1;
		}
		return dao.count(map);
	}

	@SuppressWarnings("unchecked")
	public BaseDao<CMD, Integer> getDao(Class<? extends CMD> clazz) {
		BaseDao<?, Integer> dao = null;
		if (clazz == CMDTime.class) {
			dao = timeDao;
		} else if (clazz == CMDFreq.class) {
			dao = freqDao;
		} else if (clazz == CMDParameter.class) {
			dao = parameterDao;
		} else if (clazz == CMDRebackPeriod.class) {
			dao = rebackPeriodDao;
		} else if (clazz == CMDRebackType.class) {
			dao = rebackTypeDao;
		} else if (clazz == CMDResourceCode.class) {
			dao = resourceCodeDao;
		} else if (clazz == CMDVolume.class) {
			dao = volumeDao;
		}
		if (dao != null) {
			return (BaseDao<CMD, Integer>) dao;
		}
		return null;
	}
	@Override
	public Class<? extends CMD> getCMDClass(String cmdType) {
		cmdType = StringUtil.UpCaseFirstLetter(cmdType);
		String clssStr = "com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMD";
		clssStr += cmdType;
		try {
			@SuppressWarnings("unchecked")
			Class<? extends CMD> clazz = (Class<? extends CMD>) getClass().getClassLoader().loadClass(clssStr);
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param request
	 * @param clazz
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CMDService#save(javax.servlet.http.HttpServletRequest, java.lang.Class) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月23日 下午5:14:32
	 */
	@Override
	public int save(HttpServletRequest request, Class<? extends CMD> clazz) throws Exception {
		Object obj = clazz.newInstance();
		List<Field> field = ReflecUtil.getFields(clazz);
		for (Field f : field) {
			String name =  f.getName();
			String value = request.getParameter(name);
			if(!StringUtils.isEmpty(value)) {
				if(f.getType()==Integer.class) {
					f.set(obj, Integer.valueOf(value));
				}else if(f.getType()==String.class){
					f.set(obj, value);
				}else if(f.getType()==java.util.Date.class) {
					f.set(obj, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(value));
				}
			}
		}
		CMD cmd = (CMD)obj;
		return save(cmd, clazz);
	}
}
