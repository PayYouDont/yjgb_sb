package com.gospell.chitong.rdcenter.broadcast;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AccidentlevelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AccidenttypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DeviceLogMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicetypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DisplaymethodMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.MenuMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.RoleMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.UserLogMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.UserMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.util.MD5Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {

	@Resource
	private EmergencyinfoMapper emergencyinfodao;
	
	@Resource
	private AccidentlevelMapper accidentleveldao;
	
	@Resource
	private DeviceinfoMapper deviceinfodao;
	
	@Test
	public void test1() {
		System.out.println(emergencyinfodao.selectByPrimaryKey(1));
	}
	@Test
	public void test2() {
		System.out.println(accidentleveldao.selectByPrimaryKey(1));
	}
	
	@Test
	public void test3() {
		System.out.println(deviceinfodao.selectByPrimaryKey(1));
	}
	
	@Resource
	private DevicemodelMapper devicemodeldao;
	
	@Test
	public void test4() {
		System.out.println(devicemodeldao.selectByPrimaryKey(1));
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
		System.out.println(ccidenttypedao.selectByPrimaryKey(1));
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
	@Test
	public void insert() {
		User user = new User();
		user.setName("admin");
		user.setPassword("123456");
		user.setPassword(MD5Util.encrypt(user.getName(), user.getPassword()));
		int i = userdao.insertSelective(user);
		String msg = i>0?"添加成功":"添加失败";
		System.out.println(msg);
	}
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
		System.out.println(menudao.selectByPrimaryKey(1));
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
}
