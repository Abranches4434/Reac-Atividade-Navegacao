package com.alunos.principal.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.alunos.principal.dto.AlunoRequestDto;
import com.alunos.principal.dto.AlunoResponseDto;
import com.alunos.principal.entity.AlunoEntity;
import com.alunos.principal.repository.AlunoRepository;

public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	public ResponseEntity<AlunoResponseDto> inserirAluno(AlunoRequestDto aluno) {
		AlunoEntity alunoEntity = aluno.dtoToEntity();
		
		alunoEntity = alunoRepository.save(alunoEntity);
		
		URI uri = URI.create("/alunos/" + alunoEntity.getId());
		
		return ResponseEntity.created(uri).body(new AlunoResponseDto(alunoEntity.getId(),
																	alunoEntity.getMatricula(),
																	alunoEntity.getNome(),
																	alunoEntity.getEmail(),
																	alunoEntity.getSenha()));
	}
	public ResponseEntity<AlunoResponseDto> alterarAlunoPeloId(Long id, AlunoRequestDto aluno) {
		AlunoEntity alunoEntity;
		
		if(!alunoRepository.existsById(id))
			throw new RuntimeException("Não encontrado"); //Douglas, troque pela sua exception
			
		alunoEntity = aluno.dtoToEntity();
		alunoEntity.setId(id);
		alunoEntity= alunoRepository.save(alunoEntity);
		
		return ResponseEntity.ok(new AlunoResponseDto(alunoEntity.getId(), 
													  alunoEntity.getMatricula(),
													  alunoEntity.getNome(),
													  alunoEntity.getEmail(),
													  alunoEntity.getSenha()));
	}
	public ResponseEntity removerAluno(Long id, AlunoRequestDto aluno) {
		
		if(!alunoRepository.existsById(id))
			throw new RuntimeException("Não encontrado"); //Douglas, troque pela sua exception
		
		alunoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	public void selecionarAluno() {}
	public void selecionarAlunoPeloId() {}
}
