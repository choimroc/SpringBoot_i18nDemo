package com.choimroc.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author choimroc
 * @since 2019/9/29
 */
@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
    public WebConfiguration() {
    }

    /*国际化 start*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        //改为我们自定义的LocaleInterceptor
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleInterceptor();
        //指定参数
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        //指定默认语言为中文
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    @Bean
    public LocalValidatorFactoryBean mvcValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
        //为Validator配置国际化
        localValidatorFactoryBean.setValidationMessageSource(resourceBundleMessageSource());
        return localValidatorFactoryBean;
    }


    @Bean(name = "messageSource")
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        //指定国际化的Resource Bundle地址
        resourceBundleMessageSource.setBasename("i18n/messages");
        //指定国际化的默认编码
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /*国际化 end*/
}
