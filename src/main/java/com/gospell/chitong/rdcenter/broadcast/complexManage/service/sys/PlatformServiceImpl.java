package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.PlatformVO;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.PlatformService;
import com.gospell.chitong.rdcenter.broadcast.util.ProepertyUtil;	

@Service
public class PlatformServiceImpl implements PlatformService{

	@Override
	public int saveAndUpdate(PlatformVO vo) throws Exception {
		Class<? extends PlatformVO> cls = vo.getClass();
		Field[] fields = cls.getDeclaredFields();
		Map<String,Object> map = new HashMap<>();
		for(int i=0;i<fields.length;i++) {
			Field f = fields[i];
			f.setAccessible(true);
			String key = "server."+f.getName();
			Object value = f.get(vo);
			if(value!=null) {
				map.put(key, value);
			}
		}
		String propertyPath = "config/server.properties";
		return ProepertyUtil.writeToProperties(map, propertyPath);
	}
}
