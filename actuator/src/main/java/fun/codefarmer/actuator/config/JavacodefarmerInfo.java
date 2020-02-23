package fun.codefarmer.actuator.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码优先级高于properties配置
 * java 代码设置info
 * @ @ClassName JavacodefarmerInfo
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/23 19:33
 **/
@Component
public class JavacodefarmerInfo implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String,Object> info = new HashMap();
        info.put("email", "wangsong0210@gmail.com");
        builder.withDetail("author", info);
    }
}
