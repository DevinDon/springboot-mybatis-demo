package com.example.aop.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalcServiceTest {

  private double deviation = 0.001;

  @Autowired
  private CalcService service;

  @Test
  public void add() {
    Assert.assertEquals("add: 1 = 1", this.service.add(1), 1, deviation);
    Assert.assertEquals("add: 1 + 2 + 3 = 6", this.service.add(1, 2, 3), 6, deviation);
    Assert.assertEquals("add: -1 + (-2) = -3", this.service.add(-1, -2), -3, deviation);
  }

  @Test
  public void sub() {
    Assert.assertEquals("sub: 1 - 0 = 1", this.service.sub(1, 0), 1, deviation);
    Assert.assertEquals("sub: 10 - 5 - 4 = 1", this.service.sub(10, 5, 4), 1, deviation);
    Assert.assertEquals("sub: 1 - 5 = -4", this.service.sub(1, 5), -4, deviation);
  }

  @Test
  public void mul() {
    Assert.assertEquals("mul: 1 = 1", this.service.mul(1), 1, deviation);
    Assert.assertEquals("mul: 1 * 2 * 3 = 6", this.service.mul(1, 2, 3), 6, deviation);
    Assert.assertEquals("mul: -2 * (-3) = 6", this.service.mul(-2, -3), 6, deviation);
  }

  @Test
  public void div() {
    Assert.assertEquals("div: 10 / 2 = 5", this.service.div(10, 2), 5, deviation);
    Assert.assertEquals("div: 1 / 2 = 0.5", this.service.div(1, 2), 0.5, deviation);
    Assert.assertEquals("div: -10 / 2 = -5", this.service.div(-10, 2), -5, deviation);
  }

}
