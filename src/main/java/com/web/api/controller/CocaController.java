package com.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.server.ResponseStatusException;

import com.web.api.model.Coca;
import com.web.api.repository.CocaRepository;

@RestController
@RequestMapping("/coca")
public class CocaController {

	@Autowired
	private CocaRepository cocaRepository;

	@GetMapping
	public List<Coca> get() {
		return cocaRepository.findAll();
	}

	@GetMapping("/{id}")
    public Coca getById(@PathVariable Long id) {
		var cocaOptional = cocaRepository.findById(id);

        if (cocaOptional.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return cocaOptional.get();
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Coca insert(@RequestBody Coca coca) {
		return cocaRepository.save(coca);
	}

	@DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        var cocaOptional = cocaRepository.findById(id);

        if (cocaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        cocaRepository.delete(cocaOptional.get());
    }

	@PutMapping("/{id}")
	public Coca replaceCocaById(@PathVariable Long id, @RequestBody Coca newCoca) {
		var cocaOptional = cocaRepository.findById(id);

		if (cocaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

		newCoca.setId(id);

		return cocaRepository.save(newCoca);
	}

}
