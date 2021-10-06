package org.lnu.software.testing.auto.service.controller.model;

import lombok.AllArgsConstructor;
import org.lnu.software.testing.auto.service.dto.model.BaseModelDto;
import org.lnu.software.testing.auto.service.dto.model.ModelDto;
import org.lnu.software.testing.auto.service.service.model.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("models")
public class ModelController {

    private final ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModelDto create(@RequestBody BaseModelDto modelDto) {
        return modelService.create(modelDto);
    }

    @GetMapping
    public List<ModelDto> findAll() {
        return modelService.findAll();
    }

    @GetMapping("{id}")
    public ModelDto find(@PathVariable Long id) {
        return modelService.find(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody BaseModelDto modelDto) {
        modelService.update(id, modelDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        modelService.delete(id);
    }
}

