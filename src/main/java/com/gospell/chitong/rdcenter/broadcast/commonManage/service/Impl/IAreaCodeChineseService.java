package com.gospell.chitong.rdcenter.broadcast.commonManage.service.Impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.AreaCodeChineseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AdministrativeService;
/**
 * 此类方法用于返回区域编码对应的中文名字
* @ClassName: AreaCodeChineseService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年6月6日 上午10:03:26 
*
 */
@Service
public class IAreaCodeChineseService implements AreaCodeChineseService{

	@Resource
	private AdministrativeService adservice;
	/**
	 * 获取区域编码对应父级的中文名字(区级)
	 * @Title: getPcodeChinese 
	 * @Description: TODO(level大于区级的只返回区级,小于的区级的返回省和市) 
	 * @param @param areaCode
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 上午10:04:32
	 */
	public String getPcodeChinese(String codes) {
		String chinese = "";
		String[] split = codes.split(";");
		for (int i = 0; i < split.length; i++) {
			String code = split[i];
			int level = getLeve(code);
			if(level<3) {
				chinese = getChinese(code, level);
			}else {
				chinese = getChinese(code, 3);
			}
		}
		return chinese;
	}
	/**
	 * 返回区域编码对应的中文名字
	 * @Title: getChinese 
	 * @Description: TODO(返回区域编码对应的中文名字) 
	 * @param @param areaCode(区域编码单个或者多个。多个用逗号隔开)
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 上午10:04:32
	 */
	public String getChinese(String areaCode) {
		String chinese = "";
		String[] split = areaCode.split(";");
		for (int i = 0; i < split.length; i++) {
			String code = split[i];
			int level = getLeve(code);
			chinese = getChinese(code, level);
		}
		return chinese;
	}
	/**
	 * 获取区域编码对应的中文名字
	 * @Title: getChinese 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param code（单个区域编码）
	 * @param @param level（区域级别）
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 上午10:05:43
	 */
	public String getChinese(String code,int level) {
		String codeChinese = "";
		for(int i=0;i<level;i++) {
			Map<String,Object> map = new HashMap<>();
			String pcode = "";
			if(i<3) {
				pcode = code.substring(0,(i+1)*2);
			}else{
				pcode = code.substring(0,i*3);
			}
			map.put("codeLike", pcode);
			map.put("codeLevel", i+1);
			Administrative ad = adservice.list(map).get(0);
			codeChinese += ad.getName()+";";
		}
		return codeChinese;
	}
	public static Integer getLeve(String code) {
		//把一个code分成5分对应level等级
		//例如:445103100200拆分后为44,51,03,100,200
		String level1 = code.substring(0,2);
		String level2 =code.substring(2,4);
		String level3 = code.substring(4,6);
		String level4 =code.substring(6,9);
		String level5 = code.substring(9,12);
		if(isEffective(level5)) {
			return 5;
		}else if(isEffective(level4)) {
			return 4;
		}else if(isEffective(level3)) {
			return 3;
		}else if(isEffective(level2)) {
			return 2;
		}else if(isEffective(level1)){
			return 1;
		}
		return 0;
	}
	//判断不是00或者000
	public static boolean isEffective(String str) {
		return !str.equals("00")&&!str.equals("000");
	}
}
