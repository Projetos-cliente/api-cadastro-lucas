package br.com.cruzvita.cadastrapessoa.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cruzvita.cadastrapessoa.dto.PessoaDTO;
import br.com.cruzvita.cadastrapessoa.model.Pessoa;
import br.com.cruzvita.cadastrapessoa.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	PessoaService pessoasService;
	
	@PostMapping("/cadastra")
	public ResponseEntity<String> cadastraPessoa(@RequestBody PessoaDTO pessoa) {
		return pessoasService.cadastrarPessoa(pessoa);

	}

	@GetMapping("/busca")
	public ResponseEntity<List<Pessoa>> buscaPessoas() {
		return pessoasService.buscarPessoa();
	}

	@DeleteMapping("/exclui/{identificador}")
	public ResponseEntity<String> deletaPessoa(@PathVariable(value = "identificador") String identificador) {
		return pessoasService.deletarPessoa(identificador);
	}
}
