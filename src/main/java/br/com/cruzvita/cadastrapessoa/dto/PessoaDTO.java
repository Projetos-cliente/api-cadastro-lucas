package br.com.cruzvita.cadastrapessoa.dto;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import lombok.Data;
@Data
public class PessoaDTO {

	
	private String nome;
	private String identificador;
	private String tipoIdentificador;
	
	
	public Boolean validaCpf(String identificador) {
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.assertValid(identificador);
		return true;
	}
	
	public Boolean validaCnpj(String identificador) {
		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.assertValid(identificador);
		return true;
	}
	

}
