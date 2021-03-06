package com.raynermdz.converters;

import com.raynermdz.commands.UnitOfMeasureCommand;
import com.raynermdz.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

  private static final String UNIT_OF_MEASURE = "unit of measure";
  private static final Long LONG_VALUE = 1L;

  private UnitOfMeasureToUnitOfMeasureCommand converter;

  @Before
  public void setUp() throws Exception {
    converter = new UnitOfMeasureToUnitOfMeasureCommand();
  }

  @Test
  public void testNullObjectConvert() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObj() throws Exception {
    assertNotNull(converter.convert(new UnitOfMeasure()));
  }

  @Test
  public void convert() throws Exception {
    //given
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(LONG_VALUE);
    uom.setUnitOfMeasure(UNIT_OF_MEASURE);
    //when
    UnitOfMeasureCommand uomc = converter.convert(uom);

    //then
    assertEquals(LONG_VALUE, uomc.getId());
    assertEquals(UNIT_OF_MEASURE, uomc.getUnitOfMeasure());
  }
}