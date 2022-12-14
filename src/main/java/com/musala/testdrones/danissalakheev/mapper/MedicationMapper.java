package com.musala.testdrones.danissalakheev.mapper;

import com.musala.testdrones.danissalakheev.dto.MedicationDto;
import com.musala.testdrones.danissalakheev.dto.MedicationOutDto;
import com.musala.testdrones.danissalakheev.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MedicationMapper {

    public static final MedicationMapper INSTANCE = Mappers.getMapper(MedicationMapper.class);

    @Mapping(target = "imageBytes", ignore = true)
    public abstract MedicationOutDto map(Medication medication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", source = "medicationDto.imageBytes")
    public abstract Medication map(MedicationDto medicationDto);

}
