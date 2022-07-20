package br.com.cruzvita.cadastrapessoa.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cruzvita.cadastrapessoa.dto.PessoaDTO;
import br.com.cruzvita.cadastrapessoa.model.Pessoa;
import br.com.cruzvita.cadastrapessoa.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<String> cadastrarPessoa(PessoaDTO pessoa) {

		if (verificaCpfCnpj(pessoa) == false) {
			return new ResponseEntity<>("CPF/CNPJ não existente", HttpStatus.BAD_REQUEST);
		}

		Pessoa pessoas = modelMapper.map(pessoa, Pessoa.class);
		if(verificaExistenciaPessoa(pessoas.getIdentificador())) {
			return new ResponseEntity<>("Cadastro já existente", HttpStatus.ACCEPTED);

		}

		pessoaRepository.save(pessoas);

		return new ResponseEntity<>("Cadastro Salvo com Sucesso", HttpStatus.ACCEPTED);
	}

	public ResponseEntity<List<Pessoa>> buscarPessoa() {
		return new ResponseEntity<>(pessoaRepository.findAll(), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> deletarPessoa(String identificador) {
		List<Pessoa> pessoa = pessoaRepository.findByIdentificadores(identificador);
		for (Pessoa p1  : pessoa) {
			pessoaRepository.deleteById(p1.getId());

		}
		return new ResponseEntity<>("Pessoa deletada com sucesso", HttpStatus.ACCEPTED);
	}

	private boolean verificaCpfCnpj(PessoaDTO pessoa) {

		if (pessoa.getIdentificador().length() == 11) {
			if (pessoa.validaCpf(pessoa.getIdentificador())) {
				pessoa.setTipoIdentificador("CPF");
				return true;
			}
		} else if (pessoa.getIdentificador().length() == 14) {
			if (pessoa.validaCnpj(pessoa.getIdentificador())) {
				pessoa.setTipoIdentificador("CNPJ");
				return true;
			}
		}
		return false;
	}

	private boolean verificaExistenciaPessoa(String identificador) {

		if (pessoaRepository.findByIdentificador(identificador) != null) {
			return true;
		}
		return false;
	}

}
