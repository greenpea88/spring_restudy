package com.example.springcalculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //붙이면 모든 bean이 import 됨
//@Import({MarketApi.class, DollarCalculator.class}) //필요한 값 주입
class DollarCalculatorTest {

    @MockBean //marketApi는 spring에서 bean으로 관리하고 있음
    private MarketApi marketApi;

    @Autowired //spring이 관리하는 bean 받아오기
    private DollarCalculator dollarCalculator;

    @Test
    public void dollarCalculatorTest(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
        dollarCalculator.init();

        int sum = dollarCalculator.sum(10,10);
        int minus = dollarCalculator.minus(10,10);

        Assertions.assertEquals(60000,sum);
        Assertions.assertEquals(0,minus);
    }

}