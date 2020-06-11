package com.jc.usermanage.util;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/11 11:44
 */
public class ValidationUtils {
    /**
     * 使用hibernate的注解来进行验证
     *
     */
    private final static Validator VALIDATOR = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj 检查的类
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            throw new RuntimeException(String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage()));
        }
    }
}
