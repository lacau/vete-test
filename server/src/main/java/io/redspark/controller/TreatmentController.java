package io.redspark.controller;

import io.redspark.controller.dto.TreatmentDTO;
import io.redspark.domain.Treatment;
import io.redspark.exception.WebException;
import io.redspark.repository.TreatmentRepository;
import io.redspark.service.TreatmentService;
import io.redspark.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static io.redspark.controller.ControllerConstants.TREATMENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = TREATMENT)
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private TreatmentRepository treatmentRepository;

    private MapperUtils<Treatment, TreatmentDTO> treatmentConverter = new MapperUtils<>(Treatment.class, TreatmentDTO.class);

    @RequestMapping(method = POST)
    public ResponseEntity post(@Valid @RequestBody TreatmentDTO treatmentDTO) {
        final TreatmentDTO treatment = treatmentService.add(treatmentDTO);

        return ResponseEntity.status(HttpStatus.OK).body(treatment);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public TreatmentDTO get(@PathVariable("id") final Long id) {
        final Treatment treatment = treatmentRepository.findOne(id);

        if (treatment == null) {
            throw new WebException(HttpStatus.NOT_FOUND, "treatment.not.found");
        }

        return new TreatmentDTO(treatment);
    }

    @RequestMapping(value = "/list", method = GET)
    public List<TreatmentDTO> list() {
        final List<Treatment> treatmentList = treatmentRepository.findAll();

        if (treatmentList == null || treatmentList.isEmpty()) {
            throw new WebException(HttpStatus.NOT_FOUND, "treatment.not.found");
        }

        return treatmentConverter.toDTO(treatmentList);
    }

    @RequestMapping(value = "/list/doctor/{doctorId}", method = GET)
    public List<TreatmentDTO> listByDoctor(@PathVariable("doctorId") final Long doctorId) {
        final List<Treatment> treatmentList = treatmentRepository.searchByDoctorId(doctorId);

        if (treatmentList == null || treatmentList.isEmpty()) {
            throw new WebException(HttpStatus.NOT_FOUND, "treatment.not.found");
        }

        return treatmentConverter.toDTO(treatmentList);
    }
}
