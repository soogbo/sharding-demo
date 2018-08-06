package xyz.sharding.config;

import io.shardingjdbc.core.keygen.KeyGenerator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author songmm
 */
@Component("keyGeneratorFactory")
public class KeyGeneratorFactory {

    @Resource
    private BeanFactory beanFactory;

    public <T extends KeyGenerator> T getKeyGenerator(Class<T> keyGeneratorClass) {
        return beanFactory.getBean(keyGeneratorClass);
    }

}
