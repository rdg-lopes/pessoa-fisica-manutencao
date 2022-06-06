package com.pessoa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoa.repository.PessoaFisicaEntity;
import com.pessoa.repository.PessoaFisicaRepository;

@Service
public class UpdatePessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	public PessoaFisicaModel updatePessoaFisica(PessoaFisicaInputModel pessoa) {
		Optional<PessoaFisicaEntity> entity = this.pessoaFisicaRepository.findByCpf(pessoa.getCpf());
		if(!entity.isPresent()) {
			throw new ModelNaoExisteException(pessoa);
		}
		entity.get().setNome(pessoa.getNome());
		this.pessoaFisicaRepository.save(entity.get());
		return new PessoaMapper().map(entity.get());
	}
	
}
