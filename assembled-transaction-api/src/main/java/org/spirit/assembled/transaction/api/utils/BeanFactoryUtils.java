package org.spirit.assembled.transaction.api.utils;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @description Spring Bean工具类
 * @author qiudequan
 * @createTime 2017年1月10日 下午4:00:18 
 */
@Component
public class BeanFactoryUtils implements BeanFactoryPostProcessor {
  //Spring应用上下文环境
  private static ConfigurableListableBeanFactory beanFactory;

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    BeanFactoryUtils.beanFactory = beanFactory;
  }

  /**
   * @description 获取对象
   * @param name
   * @return Object 一个以所给名字注册的bean的实例
   * @throws org.springframework.beans.BeansException
   * @author qiudequan
   */
  @SuppressWarnings("unchecked")
  public static <T> T getBean(String name) {
    return (T) beanFactory.getBean(name);
  }

  /**
   * @description 获取类型为requiredType的对象
   * @param clz
   * @return
   * @throws org.springframework.beans.BeansException
   * @author qiudequan
   */
  public static <T> T getBean(Class<T> clz) {
    return beanFactory.getBean(clz);
  }

  /**
   * @description 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
   * @param name
   * @return boolean
   * @author qiudequan
   */
  public static boolean containsBean(String name) {
    return beanFactory.containsBean(name);
  }

  /**
   * @description 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
   *              如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
   * @param name
   * @return boolean
   * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
   * @author qiudequan
   */
  public static boolean isSingleton(String name) {
    return beanFactory.isSingleton(name);
  }

  /**
   * @description 根据名称获取其类型
   * @param name
   * @return Class 注册对象的类型
   * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
   * @author qiudequan
   */
  public static Class<?> getType(String name) {
    return beanFactory.getType(name);
  }

  /**
   * @description 如果给定的bean名字在bean定义中有别名，则返回这些别名
   * @param name
   * @return
   * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
   * @author qiudequan
   */
  public static String[] getAliases(String name) {
    return beanFactory.getAliases(name);
  }

}
