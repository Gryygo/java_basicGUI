package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {

	private Connection con;

	public ContatoDao() {
		new ConnectionFactory();
		con = ConnectionFactory.getConnection();
	}

	public void salvarContato(Contato contato) {
		String sql = "insert into contato(nome,sobrenome,telefone) values (?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getSobrenome());
			ps.setString(3, contato.getTelefone());

			ps.execute();
			ps.close();

			System.out.println("Contato Salvo!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Contato> listarContatos() {
		List<Contato> lista = new ArrayList<>();

		String sql = "select * from contato";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Contato contato = new Contato();

				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setSobrenome(rs.getString("sobrenome"));
				contato.setTelefone(rs.getString("telefone"));

				lista.add(contato);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void atualizarContato(Contato contato) {
		String sql = "update contato set nome=?, sobrenome=?, telefone=? where id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getSobrenome());
			ps.setString(3, contato.getTelefone());
			ps.setLong(4, contato.getId());

			ps.execute();
			ps.close();

			System.out.println("Contato Atualizado!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarContato(Long id) {
		String sql = "delete from contato where id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);

			ps.execute();
			ps.close();

			System.out.println("Contato Deletado!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Contato buscarContatoPorId(long id) {

		Contato contato = new Contato();
		String sql = "select * from contato where id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setSobrenome(rs.getString("sobrenome"));
				contato.setTelefone(rs.getString("telefone"));

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contato;
	}
}
