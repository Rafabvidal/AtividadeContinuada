package br.gov.cesarschool.poo.daogenerico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/*
 * Esta classe representa um DAO genérico, que inclui, exclui, altera, busca por identificador 
 * único e busca todos, qualquer objeto(s) cujo tipo é subtipo de Entidade.
 * 
 * Sugerimos o uso da API de serialização do JAVA, que grava e lê objetos em arquvos. 
 * Lembrar sempre de fechar (em qualquer circunstância) streams JAVA abertas.
 * 
 * As nuances mais detalhadas do funcionamento desta classe está especificada na classe de testes
 * automatizados br.gov.cesarschool.poo.testes.TestesDAOSerializador.    
 * 
 * A classe deve ter a estrutura (métodos e construtores) dada abaixo.
 */

public class DAOSerializadorObjetos {
	private String nomeDiretorio;

	public DAOSerializadorObjetos(Class<?> tipoEntidade) { //pode ser qualquer classe
		this.nomeDiretorio = "." + File.separator + tipoEntidade.getSimpleName();
		File dir = new File(nomeDiretorio);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public boolean incluir(Entidade entidade) {
		File arquivo = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
		if (arquivo.exists()) {
			return false; // Já existe um arquivo com o ID único
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			entidade.setDataHoraInclusao(LocalDateTime.now());
			oos.writeObject(entidade);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterar(Entidade entidade) {
		File arquivo = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
		if (!arquivo.exists()) {
			return false; // O arquivo não existe
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			entidade.setDataHoraUltimaAlteracao(LocalDateTime.now());
			oos.writeObject(entidade);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(String idUnico) {
		File arquivo = new File(nomeDiretorio + File.separator + idUnico);
		return arquivo.exists() && arquivo.delete();
	}

	public Entidade buscar(String idUnico) {
		File arquivo = new File(nomeDiretorio + File.separator + idUnico);
		if (!arquivo.exists()) {
			return null;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
			return (Entidade) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Entidade[] buscarTodos() {
		File dir = new File(nomeDiretorio);
		File[] arquivos = dir.listFiles();
		if (arquivos == null || arquivos.length == 0) {
			return new Entidade[0];
		}
		List<Entidade> lista = new ArrayList<>();
		for (File arquivo : arquivos) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
				lista.add((Entidade) ois.readObject());
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return lista.toArray(new Entidade[0]);
	}


	private String getFilePath(String idUnico) {
		return nomeDiretorio + "/" + idUnico + ".dat";
	}
}

