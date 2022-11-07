package br.com.fiap.emotion.dao;

import java.util.List;

public interface AbstractDao<T> {

	void cadastrar(T objeto);

	List<T> consultarTodos();

	void atualizar(T objeto) throws Exception;

	void apagar(T objeto);

}
