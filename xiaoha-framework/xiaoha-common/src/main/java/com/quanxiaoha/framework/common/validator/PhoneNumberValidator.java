package com.quanxiaoha.framework.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-03 11:25
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        // 这里进行一些初始化操作
        // initialize 方法是用来执行初始化操作的。
        // 这个方法在校验器实例化后会被调用，
        // 通常用来读取注解中的参数来设置校验器的初始状态。
        // 在这里，我们没有任何初始化操作，所以方法体是空的。
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        // 校验逻辑：正则表达式判断手机号是否为 11 位数字
        return phoneNumber != null && phoneNumber.matches("\\d{11}");
    }
}
