package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.PlatformRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Platform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService{
	@Resource
	private PlatformRepository repository;
	/*@Override
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
	}*/

	@Override
	public int delete(Integer id) throws Exception {
		repository.deleteById(id);
		return 1;
	}

	@Override
	public int save(Platform record) throws Exception {
		repository.save(record);
		return 1;
	}

	@Override
	public Platform selectById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Platform> findAll() {
		return repository.findAll();
	}
}
