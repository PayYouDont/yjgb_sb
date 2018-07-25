
package com.gospell.chitong.rdcenter.broadcast;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.TaskMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Task;

/** 
* @ClassName: TransactionalTest 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月24日 上午11:24:10 
*  
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTest {
	@Resource
	private TaskMapper taskdao;
	
	
	@Test
	@Transactional
	public void test() {
		Task task1 = new Task();
		task1.setCronExpression("2	0/11 * * * * ?");
		Task task2 = new Task();
		task2.setCronExpression("2	0/12 * * * * ?");
		Task task3 = new Task();
		task3.setDescription("事务测试");
		Task task4 = new Task();
		task4.setCronExpression("2	0/14 * * * * ?");
		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);
		tasks.add(task3);
		tasks.add(task4);
		for (Task task : tasks) {
			taskdao.insertSelective(task);
		}
	}
}
