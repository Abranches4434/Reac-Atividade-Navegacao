package com.alunos.principal.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alunos.principal.dto.AlunoRequestDto;
import com.alunos.principal.dto.AlunoResponseDto;
import com.alunos.principal.entity.AlunoEntity;
import com.alunos.principal.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	public ResponseEntity<AlunoResponseDto> inserirAluno(AlunoRequestDto aluno) {
		AlunoEntity alunoEntity = aluno.dtoToEntity();
		alunoEntity = alunoRepository.save(alunoEntity);
		
		URI uri = URI.create("/alunos/" + alunoEntity.getId());
		
		return ResponseEntity.created(uri).body(new AlunoResponseDto(
				alunoEntity.getId(),
				alunoEntity.getMatricula(),
				alunoEntity.getNome(),
				alunoEntity.getEmail(),
				alunoEntity.getSenha()
		));
	}

	public ResponseEntity<AlunoResponseDto> alterarAlunoPeloId(Long id, AlunoRequestDto aluno) {
		if(!alunoRepository.existsById(id)) {
			throw new RuntimeException("Aluno não encontrado");
		}
			
		AlunoEntity alunoEntity = aluno.dtoToEntity();
		alunoEntity.setId(id);
		alunoEntity = alunoRepository.save(alunoEntity);
		
		return ResponseEntity.ok(new AlunoResponseDto(
				alunoEntity.getId(), 
				alunoEntity.getMatricula(),
				alunoEntity.getNome(),
				alunoEntity.getEmail(),
				alunoEntity.getSenha()
		));
	}

	public ResponseEntity<Void> removerAluno(Long id) {
		if(!alunoRepository.existsById(id)) {
			throw new RuntimeException("Aluno não encontrado");
		}
		
		alunoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<List<AlunoResponseDto>> selecionarAluno() {
		List<AlunoEntity> alunos = alunoRepository.findAll();
		List<AlunoResponseDto> dtos = alunos.stream()
				.map(a -> new AlunoResponseDto(a.getId(), a.getMatricula(), a.getNome(), a.getEmail(), a.getSenha()))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(dtos);
	}

	public ResponseEntity<AlunoResponseDto> selecionarAlunoPeloId(Long id) {
		AlunoEntity a = alunoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
		
		return ResponseEntity.ok(new AlunoResponseDto(a.getId(), a.getMatricula(), a.getNome(), a.getEmail(), a.getSenha()));
	}
}