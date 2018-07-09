package com.gospell.chitong.rdcenter.broadcast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AccidentlevelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AccidenttypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DeviceLogMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DeviceParamValMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicemodelparamMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicetypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DisplaymethodMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.MenuMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.RoleMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.UserLogMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.UserMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DeviceParamVal;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Menu;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

	@Resource
	private EmergencyinfoMapper emergencyinfodao;
	
	@Resource
	private AccidentlevelMapper accidentleveldao;
	
	@Resource
	private DeviceinfoMapper deviceinfodao;
	
	@Test
	public void test1() {
		Page page = new Page();
		Map<String,Object> map = page.getMap();
		map.put("status", 6);
		map.put("order", "ASC");
		map.put("sort", "id");
		map.put("areaCode", "445103000000");
		List<Emergencyinfo> list = emergencyinfodao.list(map);
		int count=0;
		for(Emergencyinfo e:list) {
			count++;
			System.out.println(e.getDisplayLanguage());
		}
		System.out.println(count);
		//System.out.println(list);
	}
	@Test
	public void test2() {
		System.out.println(accidentleveldao.selectByPrimaryKey(1));
	}
	
	@Test
	public void test3() {
		Page page = new Page();
		Map<String,Object> map = page.getMap();
		List<Deviceinfo> list = deviceinfodao.list(map);
		for (Deviceinfo info : list) {
			System.out.println(info.getDeviceModel());
		}
	}
	
	@Resource
	private DevicemodelMapper devicemodeldao;
	
	@Test
	public void test4() {
		List<Devicemodel> list = devicemodeldao.list(new HashMap<>());
		System.out.println(list);
	}
	@Resource
	private DevicetypeMapper devicetypedao;
	
	@Test
	public void test5() {
		System.out.println(devicetypedao.selectByPrimaryKey(1));
	}
	@Resource
	private AccidenttypeMapper ccidenttypedao;
	
	@Test
	public void test6() {
		//System.out.println(ccidenttypedao.selectByPrimaryKey(1));
		//String path =  "C:\\Users\\pay\\Desktop\\资料\\国家应急平台体系信息资源分类与编码规范.docx";
	}
	
	@Resource
	private DisplaymethodMapper displaymethoddao;
	
	@Test
	public void test7() {
		System.out.println(displaymethoddao.selectByPrimaryKey(1));
	}
	@Resource
	private AdministrativeMapper administrativedao;
	
	@Test
	public void test8() {
		System.out.println(administrativedao.selectByPrimaryKey(1));
	}
	@Resource
	private UserMapper userdao;
	
	@Test
	public void test9() {
		System.out.println(userdao.findByName("admin"));
	}
	/*@Test
	public void insert() {
		User user = new User();
		user.setName("admin");
		user.setPassword("123456");
		user.setPassword(MD5Util.encrypt(user.getName(), user.getPassword()));
		int i = userdao.insertSelective(user);
		String msg = i>0?"添加成功":"添加失败";
		System.out.println(msg);
	}*/
	@Resource
	private RoleMapper roledao;
	
	@Test
	public void test10() {
		System.out.println(roledao.selectByPrimaryKey(1));
	}
	@Resource
	private MenuMapper menudao;
	
	@Test
	public void test11() {
		//System.out.println(menudao.selectByPrimaryKey(1));
		//List<Menu> list = menudao.findByPid(6);
		List<Menu> list = menudao.findByPid(null);
		//Logger logger = LoggerFactory.getLogger(this.getClass());
		System.out.println(list);
		/*Menu menu = new Menu();
		menu.setText("test");
		menu.setPid(6);
		menu.setMenucaptionen("测试");
		int i = menudao.insertSelective(menu);
		String msg = i>0?"添加成功":"添加失败";
		System.out.println(msg);*/
	}
	@Resource
	private UserLogMapper userlogdao;
	
	@Test
	public void test12() {
		System.out.println(userlogdao.selectByPrimaryKey(1));
	}
	@Resource
	private DeviceLogMapper devicelogdao;
	
	@Test
	public void test13() {
		System.out.println(devicelogdao.selectByPrimaryKey(1));
	}
	@Resource
	private AdministrativeMapper asdao;
	@Test
	public void test14() {
		Map<String,Object> map = new HashMap<String, Object>();
		//map.put("codeLike","445103100");
		List<Administrative> list = asdao.list(map);
		for(Administrative a:list) {
			System.out.println(a.getCode());
		}
	}
	@Resource
	private DevicemodelparamMapper dmpdao;
	
	@Test
	public void test15() {
		Map<String,Object> map = new HashMap<String, Object>();
		//map.put("codeLike","445103100");
		List<Devicemodelparam> list = dmpdao.list(map);
		System.out.println(list);
	}
	@Resource
	private DeviceParamValMapper dpvDao;
	@Test
	public void test16() {
		Map<String,Object> map = new HashMap<String, Object>();
		//map.put("codeLike","445103100");
		List<DeviceParamVal> list = dpvDao.list(map);
		System.out.println(list);
	}
}