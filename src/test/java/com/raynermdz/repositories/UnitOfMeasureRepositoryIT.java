package com.raynermdz.repositories;

import com.raynermdz.Models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void findByUnitOfMeasure() throws Exception {
    Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon");
    assertEquals("Teaspoon", unitOfMeasure.get().getUnitOfMeasure());
  }

  @Test
  public void findByUnitOfMeasureCup() throws Exception {
    Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUnitOfMeasure("Cup");
    assertEquals("Cup", unitOfMeasure.get().getUnitOfMeasure());
  }
}