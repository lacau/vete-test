package io.redspark.domain.converter;

import io.redspark.domain.type.AnimalType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by lacau on 17/06/17.
 */
@Converter
public class AnimalTypeConverter implements AttributeConverter<AnimalType, String> {

    @Override
    public String convertToDatabaseColumn(AnimalType animalType) {
        return animalType.getCode();
    }

    @Override
    public AnimalType convertToEntityAttribute(String code) {
        return AnimalType.fromCode(code);
    }
}
