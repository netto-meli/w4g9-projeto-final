package com.mercadolibre.fernando_netto_projeto_final.unit.beans;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mercadolibre.fernando_netto_projeto_final.beans.RandomSampleBean;
import com.mercadolibre.fernando_netto_projeto_final.dtos.SampleDTO;
import org.junit.jupiter.api.Test;

class RandomSampleBeanTest {

  @Test
  void randomPositiveTestOK() {
    RandomSampleBean randomSample = new RandomSampleBean();

    SampleDTO sample = randomSample.random();

    assertTrue(sample.getRandom() >= 0);
  }
}
