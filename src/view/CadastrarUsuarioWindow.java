package view;

import model.Aluno;
import model.Usuario;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lib.ManipularArquivo;

public class CadastrarUsuarioWindow extends AbstractWindowFrame {
	private static final long serialVersionUID = 1L;

	private JPasswordField txfSenha;
	private JTextField txfCodAluno;
	private JComboBox<String> txfPerfil;
	private JButton btnCadastra;
	private JButton btnLimpar;
	private JLabel saida;
	
	private Usuario usuario;

	public CadastrarUsuarioWindow() {
		super("Cadastrar Usu�rio");
		this.usuario = new Usuario();
		criarComponentes();
	}
	
	public CadastrarUsuarioWindow(Usuario usuario) {
		super("Cadastrar Usu�rio");
		this.usuario = usuario;
		criarComponentes();
		setarValores(usuario);
	}

	private void criarComponentes() {
		saida = new JLabel("C�digo: ");
		saida.setBounds(15, 10, 200, 25);
		getContentPane().add(saida);

		txfCodAluno = new JTextField();
		txfCodAluno.setBounds(15, 30, 200, 25);
		txfCodAluno.setToolTipText("Digite o c�digo");
		getContentPane().add(txfCodAluno);

		saida = new JLabel("Senha:");
		saida.setBounds(15, 60, 200, 25);
		getContentPane().add(saida);

		txfSenha = new JPasswordField();
		txfSenha.setBounds(15, 80, 200, 25);
		txfSenha.setToolTipText("Digite uma senha");
		getContentPane().add(txfSenha);

		saida = new JLabel("Perfil:");
		saida.setBounds(15, 110, 200, 25);
		getContentPane().add(saida);

		txfPerfil = new JComboBox<String>();
		txfPerfil.addItem("-Selecione-");
		txfPerfil.addItem(Usuario.ADMINISTRADOR);
		txfPerfil.addItem(Usuario.CONVIDADO);
		txfPerfil.setBounds(15, 130, 200, 25);
		txfPerfil.setToolTipText("Informe o perfil");
		getContentPane().add(txfPerfil);

		btnLimpar = new JButton(new AbstractAction("Limpar") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});

		btnLimpar.setBounds(15, 170, 95, 25);
		btnLimpar.setToolTipText("Clique aqui para limpar os campos");
		getContentPane().add(btnLimpar);

		btnCadastra = new JButton(new AbstractAction("Cadastrar") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();

				String codUsuario = txfCodAluno.getText();
				usuario.setLogin(codUsuario);

				String senhaUsuario = new String(txfSenha.getPassword());
				usuario.setSenha(senhaUsuario);

				String perfilUsuario = txfPerfil.getSelectedItem().toString();
				usuario.setPerfil(perfilUsuario);

				try {
					ManipularArquivo aM = new ManipularArquivo();
					aM.inserirDado(usuario);
					limparFormulario();
					JOptionPane.showMessageDialog(null,"Usuario cadastrado com sucesso!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastra.setBounds(120, 170, 95, 25);
		getContentPane().add(btnCadastra);
	}

	public void limparFormulario() {
		txfCodAluno.setText("");
		txfSenha.setText("");
		txfPerfil.setSelectedIndex(0);
		
	}
	
	private void setarValores(Usuario usuario) {
		//TODO: setar valores iniciais para edi��o
		txfCodAluno.setText(usuario.getLogin());
	}
}
