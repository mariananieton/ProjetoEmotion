package br.com.fiap.emotion.service;

import br.com.fiap.emotion.dao.EmpresaDao;
import br.com.fiap.emotion.exception.NotUniqueCnpjException;
import br.com.fiap.emotion.model.Empresa;

public class EmpresaService {

	private static final EmpresaDao empresaDao = new EmpresaDao();

	public void cadastrarEmpresa(Empresa empresa) throws NotUniqueCnpjException {
		Empresa empresaExistente = empresaDao.buscaEmpresaPorCnpj(empresa.getCnpj());
		if (empresaExistente != null){
			throw new NotUniqueCnpjException("Esse CNPJ já está cadastrado!");
		}
		empresaDao.cadastrar(empresa);
	}
}
