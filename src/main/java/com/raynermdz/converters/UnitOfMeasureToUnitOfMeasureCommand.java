package com.raynermdz.converters;

import com.raynermdz.commands.UnitOfMeasureCommand;
import com.raynermdz.models.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasureCommand convert(UnitOfMeasure source) {

    if (source == null) return null;

    final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
    command.setId(source.getId());
    command.setUnitOfMeasure(source.getUnitOfMeasure());

    return command;
  }
}
