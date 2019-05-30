package com.raynermdz.repositories;

import com.raynermdz.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
  public void findByDescription() throws Exception {

    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon");

    assertEquals("Teaspoon", uomOptional.get().getUnitOfMeasure());
  }

  @Test
  public void findByDescriptionCup() throws Exception {

    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUnitOfMeasure("Cup");

    assertEquals("Cup", uomOptional.get().getUnitOfMeasure());
  }

}
