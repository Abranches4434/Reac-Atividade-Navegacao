package com.alunos.principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alunos.principal.dto.AlunoRequestDto;
import com.alunos.principal.dto.AlunoResponseDto;
import com.alunos.principal.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "*") 
// Esse cara vai permitir a comunicação com o React, se vira aí agr meu chapa

public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@PostMapping
	public ResponseEntity<AlunoResponseDto> cadastrarAluno(@Valid @RequestBody AlunoRequestDto dto) {
		return alunoService.inserirAluno(dto);
	}

	@GetMapping
	public ResponseEntity<List<AlunoResponseDto>> listarTodos() {
		return alunoService.selecionarAluno();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoResponseDto> buscarPorId(@PathVariable Long id) {
		return alunoService.selecionarAlunoPeloId(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponseDto> atualizarAluno(@PathVariable Long id, @Valid @RequestBody AlunoRequestDto dto) {
		return alunoService.alterarAlunoPeloId(id, dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
		return alunoService.removerAluno(id);
	}
}