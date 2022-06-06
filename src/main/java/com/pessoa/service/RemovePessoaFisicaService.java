package com.pessoa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoa.repository.PessoaFisicaEntity;
import com.pessoa.repository.PessoaFisicaRepository;

@Service
public class RemovePessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	public PessoaFisicaModel remove(String cpf) {
		Optional<PessoaFisicaEntity> entity = this.pessoaFisicaRepository.findByCpf(cpf);
		if(!entity.isPresent()) {
			throw new CpfNaoExisteException(cpf);
		}
		this.pessoaFisicaRepository.delete(entity.get());
		return new PessoaMapper().map(entity.get());
	}
	
}
