package com.alunos.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alunos.principal.entity.AlunoEntity;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long>{
	boolean existsById();
}
