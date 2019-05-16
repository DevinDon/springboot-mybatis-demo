package com.example.aop.service;

import org.springframework.stereotype.Service;

@Service
public class CalcService {

  /**
   * Mathematical operation: addition.
   *
   * @param addends Addend.
   * @return The sum of addends.
   */
  public double add(double... addends) {
    double sum = 0;
    for (double num : addends) {
      sum += num;
    }
    return sum;
  }

  /**
   * Mathematical operation: subtraction.
   *
   * @param minuend     Minuend.
   * @param subtrahends Subtrahends.
   * @return The difference of minuend & subtrahends.
   */
  public double sub(double minuend, double... subtrahends) {
    for (double num : subtrahends) {
      minuend -= num;
    }
    return minuend;
  }

  /**
   * Mathematical operation: multiplication.
   *
   * @param nums Multipliers.
   * @return The product of multipliers.
   */
  public double mul(double... nums) {
    double product = 1;
    for (double num : nums) {
      product *= num;
    }
    return product;
  }

  /**
   * Mathenmatical operation: division.
   *
   * @param divisor   Divisor.
   * @param dividends Dividends.
   * @return The quotient of divisor & dividends.
   */
  public double div(double divisor, double... dividends) {
    for (double num : dividends) {
      divisor /= num;
    }
    return divisor;
  }

}
