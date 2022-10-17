package model;

import java.util.List;

public class TestCon {

	public static void main(String[] args) {
		
	 	ContatoDao dao = new ContatoDao();
		
		Contato c1 = new Contato("Carlos", "Onório", "88974987536");
//		c1.setId(2);
		
//		Teste salvar contato
//		dao.salvarContato(c1);
		
//		Test listar contato
//		List<Contato> contatos = dao.listarContatos();
//		contatos.stream().forEach(System.out::println);
		
//		Test atualizar contato
//		dao.atualizarContato(c1);
		
//		Test deletar contato
//		dao.deletarContato((long) 3);
		
		Contato c2 = dao.buscarContatoPorId(9);
		
		System.out.println(c2);
		
	}

}
