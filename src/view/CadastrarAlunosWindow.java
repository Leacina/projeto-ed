package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import lib.ManipularArquivo;
import model.Aluno;

public class CadastrarAlunosWindow extends WindowFrame {
	Aluno aluno = new Aluno();
	ManipularArquivo aM = new ManipularArquivo();
	
	private static final long serialVersionUID = -4479891238469664919L;

	private JTextField txfNome, txfCod, txfEmail, txfObs, txfEnder, txfNum, txfComplemen, txfBairro;
	private JComboBox<String> cbxGenero, cbxCidade, cbxUf, cbxPais;
	private JButton btnSalvar,btnLimpar;
	private JLabel labes;
	private JFormattedTextField txfData, txfFone, txfCel, txfCep;

	public CadastrarAlunosWindow() {
		super("Cadastrar Aluno");
		criarComponentes();
	}

	public void criarComponentes() {

		// Coluna da esquerda, campos e escritas
		labes = new JLabel("C�digo do Aluno:");
		labes.setBounds(15, 10, 250, 25);
		getContentPane().add(labes);

		txfCod = new JTextField();
		txfCod.setBounds(15, 30, 125, 25);
		getContentPane().add(txfCod);

		labes = new JLabel("Nome:");
		labes.setBounds(15, 60, 250, 25);
		getContentPane().add(labes);

		txfNome = new JTextField();
		txfNome.setBounds(15, 80, 350, 25);
		getContentPane().add(txfNome);

						labes = new JLabel("Sexo:");
						labes.setBounds(175, 110, 250, 25);
						getContentPane().add(labes);
				
						cbxGenero = new JComboBox<String>();
						cbxGenero.addItem("-Selecione-");
						cbxGenero.addItem("Masculino");
						cbxGenero.addItem("Feminino");
						cbxGenero.setBounds(175, 130, 185, 25);
						getContentPane().add(cbxGenero);

		labes = new JLabel("Data de Nascimento:");
		labes.setBounds(15, 110, 230, 25);
		getContentPane().add(labes);

		try {
			MaskFormatter maskDat = new MaskFormatter("##/##/####");
			MaskFormatter maskFone = new MaskFormatter("(##) #####-####");
			MaskFormatter maskCep = new MaskFormatter("#####-###");

			txfData = new JFormattedTextField(maskDat);
			txfData.setBounds(15, 130, 125, 25);
			getContentPane().add(txfData);

			labes = new JLabel("Telefone:");//
			labes.setBounds(15, 160, 125, 25);
			getContentPane().add(labes);

			txfFone = new JFormattedTextField(maskFone);
			txfFone.setBounds(15, 180, 125, 25);
			getContentPane().add(txfFone);

						labes = new JLabel("Celular:");//
						labes.setBounds(175, 160, 185, 25);
						getContentPane().add(labes);
			
						txfCel = new JFormattedTextField(maskFone);
						txfCel.setBounds(175, 180, 185, 25);
						getContentPane().add(txfCel);

			labes = new JLabel("CEP:");
			labes.setBounds(450, 110, 100, 25);
			getContentPane().add(labes);

			txfCep = new JFormattedTextField(maskCep);
			txfCep.setBounds(450, 130, 100, 25);
			getContentPane().add(txfCep);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		labes = new JLabel("Email:");
		labes.setBounds(15, 210, 350, 25);
		getContentPane().add(labes);

		txfEmail = new JTextField();
		txfEmail.setBounds(15, 230, 350, 25);
		getContentPane().add(txfEmail);

		labes = new JLabel("Observa��o:");
		labes.setBounds(15, 260, 350, 25);
		getContentPane().add(labes);

		txfObs = new JTextField();
		txfObs.setBounds(15, 280, 350, 25);
		getContentPane().add(txfObs);

		// coluna da direita, cmpos e escrita

		labes = new JLabel("Endere�o:");
		labes.setBounds(450, 10, 250, 25);
		getContentPane().add(labes);

		txfEnder = new JTextField();
		txfEnder.setBounds(450, 30, 200, 25);
		getContentPane().add(txfEnder);
		
							labes = new JLabel("N�mero:");
							labes.setBounds(675, 10, 50, 25);
							getContentPane().add(labes);
					
							txfNum = new JTextField();
							txfNum.setBounds(675, 30, 75, 25);
							getContentPane().add(txfNum);

		labes = new JLabel("Complemento:");
		labes.setBounds(450, 60, 250, 25);
		getContentPane().add(labes);

		txfComplemen = new JTextField();
		txfComplemen.setBounds(450, 80, 300, 25);
		getContentPane().add(txfComplemen);

							labes = new JLabel("Bairro:");
							labes.setBounds(570, 110, 250, 25);
							getContentPane().add(labes);
					
							txfBairro = new JTextField();
							txfBairro.setBounds(570, 130, 180, 25);
							getContentPane().add(txfBairro);

		labes = new JLabel("Cidade (Outra tela):");
		labes.setBounds(450, 160, 250, 25);
		getContentPane().add(labes);

		cbxCidade = new JComboBox<String>();
		cbxCidade.addItem("-Selecione-");
		cbxCidade.addItem("Vem da outra tela");
		cbxCidade.setBounds(450, 180, 200, 25);
		getContentPane().add(cbxCidade);

		labes = new JLabel("UF (Outra tela):");
		labes.setBounds(450, 210, 250, 25);
		getContentPane().add(labes);

		cbxUf = new JComboBox<String>();
		cbxUf.addItem("-Selecione-");
		cbxUf.addItem("Vem da outra tela");
		cbxUf.setBounds(450, 230, 200, 25);
		getContentPane().add(cbxUf);

		labes = new JLabel("Pa�s (Outra tela):");
		labes.setBounds(450, 260, 250, 25);
		getContentPane().add(labes);

		cbxPais = new JComboBox<String>();
		cbxPais.addItem("-Selecione-");
		cbxPais.addItem("Vem da outra tela");
		cbxPais.setBounds(450, 280, 200, 25);
		getContentPane().add(cbxPais);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(15, 320, 95, 25);
		getContentPane().add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Implementar m�todo limpar
				
			}
		});
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(120, 320, 95, 25);
		getContentPane().add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				aluno.setCodAluno(txfCod.getText());
				aluno.setNomeAluno(txfNome.getText());
				String auxSexo = cbxGenero.getSelectedItem().toString();
				
				char sexo = auxSexo.charAt(0);
				aluno.setSexo(sexo);
				aluno.setDataNascimento(txfData.getText());
				aluno.setTelefone(txfFone.getText());
				aluno.setCelular(txfCel.getText());
				aluno.setCep(txfCep.getText());
				aluno.setEmail(txfEmail.getText());
				aluno.setObservacao(txfObs.getText());
				aluno.setEndereco(txfEnder.getText());
				aluno.setComplemento(txfComplemen.getText());
				aluno.setBairro(txfBairro.getText());
				aluno.setNumero(Integer.parseInt(txfNum.getText()));
				aluno.setCidade(cbxCidade.getSelectedItem().toString());
				aluno.setUf(cbxUf.getSelectedItem().toString());
				aluno.setPais(cbxPais.getSelectedItem().toString());
				
				try {
					aM.inserirDado(aluno);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				setVisible(false);
				//TODO: inseriDado(Aluno);
				// new Teste().setVisible(true);
				// new Teste().setVisible(true);
				// Adaptar para a outra tela
			}

		});

	}

	public static void main(String[] args) {

		new CadastrarAlunosWindow();

	}

}