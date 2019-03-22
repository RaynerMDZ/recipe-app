package com.raynermdz.converters;

import com.raynermdz.commands.UnitOfMeasureCommand;
import com.raynermdz.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

  private static final String UNITOFMEASURE = "Unit Of Measure";
  private static final Long LONG_VALUE = 1L;

  UnitOfMeasureCommandToUnitOfMeasure converter;

  @Before
  public void setUp() throws Exception {
    converter = new UnitOfMeasureCommandToUnitOfMeasure();
  }

  @Test
  public void testNullParameter() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception {
    assertNotNull(converter.convert(new UnitOfMeasureCommand()));
  }

  @Test
  public void convert() {

    // Given
    UnitOfMeasureCommand command = new UnitOfMeasureCommand();
    command.setId(LONG_VALUE);
    command.setUnitOfMeasure(UNITOFMEASURE);

    // When
    UnitOfMeasure uom = new UnitOfMeasure();

    // Then
    assertNotNull(uom);
    assertEquals(LONG_VALUE, uom.getId());
    assertEquals(UNITOFMEASURE, uom.getUnitOfMeasure());
  }
}