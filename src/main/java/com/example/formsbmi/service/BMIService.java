package com.example.formsbmi.service;

import org.springframework.stereotype.Service;

@Service
public class BMIService {


    public double udregnBMI(double højde, double vægt){
        return vægt/((højde/100)*(højde/100));
    }
}


