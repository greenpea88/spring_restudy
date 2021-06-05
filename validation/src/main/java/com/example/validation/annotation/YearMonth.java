package com.example.validation.annotation;

import com.example.validation.validator.YearMonthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어떤 class를 가지고 검사할 것인지 지정
@Constraint(validatedBy = YearMonthValidator.class)
//annotation을 지정할 수 있는 타입
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
//어느 시점까지 annotation의 메모리를 가져갈지 결정
@Retention(RetentionPolicy.RUNTIME)
public @interface YearMonth {
    //annotation에서 사용할 수 있는 속성들

    String message() default "{yyyyMM의 형식에 맞지 않습니다}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String pattern() default "yyyyMMdd";
}
