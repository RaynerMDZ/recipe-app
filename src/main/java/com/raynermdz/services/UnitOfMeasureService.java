package com.raynermdz.services;

import com.raynermdz.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

  Set<UnitOfMeasureCommand> listAllUoms();
}
