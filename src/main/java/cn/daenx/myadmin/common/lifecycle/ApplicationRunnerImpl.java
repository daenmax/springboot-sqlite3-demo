package cn.daenx.myadmin.common.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动后，初始化
 */
@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) {
        log.info("【生命周期】项目已启动");
    }

}
