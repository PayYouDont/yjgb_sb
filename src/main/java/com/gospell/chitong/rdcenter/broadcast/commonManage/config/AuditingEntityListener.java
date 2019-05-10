package com.gospell.chitong.rdcenter.broadcast.commonManage.config;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.User;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @ClassName AuditingEntityListener
 * @Description TODO
 * @Author pay
 * @DATE 2019/3/20 15:48
 **/
@Configuration
public class AuditingEntityListener implements AuditorAware<String> {
   @Override
   public Optional<String> getCurrentAuditor() {
      User user = ShiroUtils.getUser ();
      return user!=null?Optional.of (user.getName ()):null;
   }
}
