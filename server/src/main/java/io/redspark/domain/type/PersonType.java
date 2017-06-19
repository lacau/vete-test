package io.redspark.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by lacau on 17/06/17.
 */
@Getter
@AllArgsConstructor
public enum PersonType {

    DOCTOR("D"),
    CLIENT("C");

    String code;

    public static PersonType fromCode(String code) {
        return Stream.of(PersonType.values())
                .filter(value -> value.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", code)));
    }
}
