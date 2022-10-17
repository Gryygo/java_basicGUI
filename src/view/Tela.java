package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Contato;
import model.ContatoDao;

public class Tela extends JFrame {

	private JPanel contentPane;
	private JTextField nomeInput;
	private JTextField sobrenomeInput;
	private JTextField telefoneInput;
	private JTable table;
	private ContatoDao dao = new ContatoDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Graphic User Interface");
		lblTitle.setFont(new Font("Gayathri", Font.BOLD, 19));
		lblTitle.setBounds(20, 12, 200, 29);
		contentPane.add(lblTitle);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("FreeSans", Font.BOLD, 14));
		lblNome.setBounds(49, 56, 49, 15);
		contentPane.add(lblNome);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setFont(new Font("FreeSans", Font.BOLD, 14));
		lblSobrenome.setBounds(12, 83, 86, 22);
		contentPane.add(lblSobrenome);

		JLabel lblNewLabel_1 = new JLabel("Telefone:");
		lblNewLabel_1.setFont(new Font("FreeSans", Font.BOLD, 14));
		lblNewLabel_1.setBounds(35, 116, 63, 15);
		contentPane.add(lblNewLabel_1);

		nomeInput = new JTextField();
		nomeInput.setBounds(106, 53, 114, 19);
		contentPane.add(nomeInput);
		nomeInput.setColumns(10);

		sobrenomeInput = new JTextField();
		sobrenomeInput.setColumns(10);
		sobrenomeInput.setBounds(106, 83, 114, 19);
		contentPane.add(sobrenomeInput);

		telefoneInput = new JTextField();
		telefoneInput.setColumns(10);
		telefoneInput.setBounds(106, 113, 114, 19);
		contentPane.add(telefoneInput);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = nomeInput.getText();
				String sobrenome = sobrenomeInput.getText();
				String telefone = telefoneInput.getText();

				Contato contato = new Contato(nome, sobrenome, telefone);

				dao.salvarContato(contato);

				limpaTela();
				JOptionPane.showMessageDialog(btnAdicionar, "Contato Adicionado!");
				listarContatos();
			}
		});
		btnAdicionar.setBounds(12, 153, 114, 25);
		contentPane.add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = table.getSelectedRow();
				
				Long id = (Long) table.getValueAt(num, 0);
				String nome = nomeInput.getText();
				String sobrenome = sobrenomeInput.getText();
				String telefone = telefoneInput.getText();
				
				Contato newContato = new Contato(nome, sobrenome, telefone);
				newContato.setId(id);
				
				int answer = JOptionPane.showConfirmDialog(btnEditar, "Tem certeza que deseja editar esse contato?");
				
				if (answer == 0) {
					dao.atualizarContato(newContato);
					JOptionPane.showMessageDialog(null, "Contato Atualizado!");
					listarContatos();
				}
			}
		});
		btnEditar.setBounds(139, 153, 114, 25);
		contentPane.add(btnEditar);

		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num = table.getSelectedRow();
				long id = (long) table.getValueAt(num, 0);
				
				Contato contato = dao.buscarContatoPorId(id);
				
				nomeInput.setText(contato.getNome());
				sobrenomeInput.setText(contato.getSobrenome());
				telefoneInput.setText(contato.getTelefone());
				
			}
		});
		btnCarregar.setBounds(139, 190, 114, 25);
		contentPane.add(btnCarregar);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = table.getSelectedRow();
				long id = (long) table.getValueAt(num, 0);
				
				int answer = JOptionPane.showConfirmDialog(btnDeletar, "Tem certeza que deseja deletar esse contato?");
				System.out.println(num);
				
				if (answer == 0) {
					dao.deletarContato(id);
					JOptionPane.showMessageDialog(null, "Contato Deletado!");
					listarContatos();
				}
			}
		});
		btnDeletar.setBounds(12, 190, 114, 25);
		contentPane.add(btnDeletar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 12, 314, 240);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Sobrenome", "Telefone" }) {
					Class[] columnTypes = new Class[] { Long.class, String.class, String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		scrollPane.setViewportView(table);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarContatos();
			}
		});
		btnMostrar.setBounds(76, 227, 114, 25);
		contentPane.add(btnMostrar);
	}

	// Limpa os inputs
	public void limpaTela() {
		nomeInput.setText("");
		sobrenomeInput.setText("");
		telefoneInput.setText("");
	}

	// Lista todos os contatos presentes no banco
	public void listarContatos() {
		List<Contato> lista = dao.listarContatos();

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		
		modelo.setRowCount(0);
		limpaTela();

		lista.stream().forEach(contato -> modelo.addRow(
				new Object[] { contato.getId(), contato.getNome(), contato.getSobrenome(), contato.getTelefone() }));
	}
}
