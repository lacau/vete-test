package io.redspark.domain.converter;

import io.redspark.domain.type.PersonType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by lacau on 17/06/17.
 */
@Converter
public class PersonTypeConverter implements AttributeConverter<PersonType, String> {
    
    @Override
    public String convertToDatabaseColumn(PersonType personType) {
        return personType.getCode();
    }

    @Override
    public PersonType convertToEntityAttribute(String code) {
        return PersonType.fromCode(code);
    }
}
