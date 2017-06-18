package io.redspark.controller.converter;

import io.redspark.controller.dto.AnimalDTO;
import io.redspark.domain.Animal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by lacau on 17/06/17.
 */
@Component
public class AnimalConverter {

    public List<AnimalDTO> convert(List<Animal> animals) {
        final List<AnimalDTO> destination = new ArrayList<>();
        Optional.ofNullable(animals).ifPresent((a) -> a.forEach((an) -> destination.add(new AnimalDTO(an))));
        return destination;
    }
}
