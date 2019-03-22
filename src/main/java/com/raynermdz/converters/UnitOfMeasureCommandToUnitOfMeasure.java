package com.raynermdz.converters;

import com.raynermdz.commands.UnitOfMeasureCommand;
import com.raynermdz.models.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasure convert(UnitOfMeasureCommand source) {

    if (source == null) {
      return null;
    }

    final UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(source.getId());
    uom.setUnitOfMeasure(source.getUnitOfMeasure());
    return uom;
  }
}
