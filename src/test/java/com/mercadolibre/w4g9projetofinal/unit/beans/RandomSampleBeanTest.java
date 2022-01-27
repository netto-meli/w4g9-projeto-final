package com.mercadolibre.w4g9projetofinal.unit.beans;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mercadolibre.w4g9projetofinal.beans.RandomSampleBean;
import com.mercadolibre.w4g9projetofinal.dtos.SampleDTO;
import org.junit.jupiter.api.Test;

class RandomSampleBeanTest {

  @Test
  void randomPositiveTestOK() {
    RandomSampleBean randomSample = new RandomSampleBean();

    SampleDTO sample = randomSample.random();

    assertTrue(sample.getRandom() >= 0);
  }
}
