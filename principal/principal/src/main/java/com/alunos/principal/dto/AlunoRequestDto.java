package com.alunos.principal.dto;

import com.alunos.principal.entity.AlunoEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlunoRequestDto(
		@NotBlank(message="Matrícula deve ser preenchida")
		@Size(max=90,min=1, message="A matrícula deve ter entre 1 e 90 caracteres")
		String matricula,
		@NotBlank(message="Nome deve ser preenchido")
		@Size(max=90,min=1, message="O nome deve ter entre 1 e 90 caracteres")
		String nome,
		@Email(message="Email deve ser válido")
		@NotBlank(message="Email deve ser preenchido")
		@Size(max=90,min=1, message="O e-mail deve ter entre 1 e 90 caracteres")
		String email,
		@NotBlank(message="Senha deve ser preenchida")
		@Size(max=90,min=1, message="A senha deve ter entre 1 e 90 caracteres")
		String senha
		){

		public AlunoEntity dtoToEntity() {
			AlunoEntity aluno = new AlunoEntity(this.matricula, this.nome, this.email, this.senha);
			
			return aluno;
		}
}
