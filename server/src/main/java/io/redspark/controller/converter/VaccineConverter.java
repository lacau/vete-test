package io.redspark.controller.converter;

import io.redspark.controller.dto.VaccineDTO;
import io.redspark.domain.Vaccine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by lacau on 17/06/17.
 */
@Component
public class VaccineConverter {

    public List<VaccineDTO> convert(List<Vaccine> vaccines) {
        final List<VaccineDTO> destination = new ArrayList<>();
        Optional.ofNullable(vaccines).ifPresent((s) -> s.forEach((so) -> destination.add(new VaccineDTO(so))));
        return destination;
    }
}
