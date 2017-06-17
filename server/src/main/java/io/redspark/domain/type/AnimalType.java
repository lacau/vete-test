package io.redspark.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by lacau on 17/06/17.
 */
@Getter
@AllArgsConstructor
public enum AnimalType {

    DOG("D"),
    CAT("C"),
    RAT("R");

    String code;

    public static AnimalType fromCode(String code) {
        return Stream.of(AnimalType.values())
                .filter(value -> value.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", code)));
    }
}
