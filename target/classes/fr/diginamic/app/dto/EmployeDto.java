package com.example.demo.dot;

import lombok.Data;

@Data
public class EmployeDto extends UserDto {
    private float daysoffBalance;
    private float emplRttBalance;
}
