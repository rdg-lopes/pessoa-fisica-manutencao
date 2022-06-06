package com.pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.service.PessoaFisicaInputModel;
import com.pessoa.service.PessoaFisicaModel;
import com.pessoa.service.RemovePessoaFisicaService;
import com.pessoa.service.UpdatePessoaFisicaService;

@RestController
@RequestMapping("/v1/PessoaFisica")
public class PessoaFisicaController {

	@Autowired
	private UpdatePessoaFisicaService updatePessoaService;
	@Autowired
	private RemovePessoaFisicaService removePessoaService;
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			 produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaFisicaDTO update(@RequestBody PessoaFisicaInputDTO dto){
		PessoaFisicaInputModel model = new PessoaMapper().map(dto);
		PessoaFisicaModel modelCriado = this.updatePessoaService.updatePessoaFisica(model);
		return new PessoaMapper().map(modelCriado);
	}
	
	@DeleteMapping(path = "/{cpf}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaFisicaDTO delete(@PathVariable("cpf") String cpf) {
		PessoaFisicaModel model = this.removePessoaService.remove(cpf);
		return new PessoaMapper().map(model);
	}
	
}
