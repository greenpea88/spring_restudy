package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//ConstarintValidator<원하는 annotation, 들어가는 값>
public class YearMonthValidator implements ConstraintValidator<YearMonth,String> {

    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        //초기화 시킬 때
        //annotation에 지정된 패턴을 가져옴
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //확인을 함(pattern : yyyyMM01)
        try{
            //localDate는 기본적으로 date까지 포함되어있기 때문에 01 추가
            LocalDate localDate = LocalDate.parse(value+"01", DateTimeFormatter.ofPattern(this.pattern));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
