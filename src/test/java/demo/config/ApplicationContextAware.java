package demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Created by Max Malakhov on 9/14/16.
 */
public interface ApplicationContextAware
{
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
