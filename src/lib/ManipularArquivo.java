package lib;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Aluno;
import model.Cidade;
import model.Usuario;

public class ManipularArquivo {

	private static String USUARIOS_PATH = ".\\src\\data\\usuarios.txt";
	private static String CIDADES_PATH = ".\\src\\data\\cidades.txt";
	private static String ALUNOS_PATH = ".\\src\\data\\alunos.txt";
	
	private static String SEPARATOR = ",";
	
	private BufferedReader lerArq;

	public ManipularArquivo() {
	}

	public void inserirDado(Cidade cidade) throws IOException {
		cidade.setId(pegarProximoId("cidades"));
		
		inserirDadosNoArquivo("cidades", cidade.getId() + SEPARATOR + cidade.getCidade() + SEPARATOR + cidade.getUf() + SEPARATOR + cidade.getPais());
	}

	public void inserirDado(Usuario usuario) throws IOException {
		usuario.setId(pegarProximoId("usuarios"));

		inserirDadosNoArquivo("usuarios", usuario.getId() + SEPARATOR + usuario.getLogin() + SEPARATOR + usuario.getSenha() + SEPARATOR + usuario.getPerfil());
	}

	public void inserirDado(Aluno aluno) throws IOException {
		aluno.setId(pegarProximoId("alunos"));

		inserirDadosNoArquivo("alunos", aluno.getId() + SEPARATOR + aluno.getCodAluno() + SEPARATOR + aluno.getNomeAluno() + SEPARATOR + aluno.getSexo() + SEPARATOR
				+ aluno.getDataNascimento() + SEPARATOR + aluno.getTelefone() + SEPARATOR + aluno.getCelular() + SEPARATOR
				+ aluno.getEmail() + SEPARATOR + aluno.getObservacao() + SEPARATOR + aluno.getEndereco() + SEPARATOR
				+ aluno.getComplemento() + SEPARATOR + aluno.getCep() + SEPARATOR + aluno.getBairro() + SEPARATOR + aluno.getCidade()
				+ SEPARATOR + aluno.getUf() + SEPARATOR + aluno.getPais());
	}
	
	public Usuario pegarUsuarioPorLoginSenha(String login, String senha) throws Exception {
		try {
			FileReader arq = new FileReader(getDestinoArquivo("usuarios"));
			lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();
			
			//Se a linha for null, significa que o arquivo de dados est� vazio (Uma exeption � retornada)
			if (linha == null) {
				throw new Exception("Nenhum usu�rio cadastrado");
			}
			
			while (linha != null) {
				String[] verificaLinha = linha.split(SEPARATOR);
				
				if (login.equals(verificaLinha[1]) && senha.equals(verificaLinha[2])) {
					return new Usuario(Integer.parseInt(verificaLinha[0]), verificaLinha[1], verificaLinha[2], verificaLinha[3]);
				}
				
				linha = lerArq.readLine();
			}
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		
		return null;
	}
	
	private void inserirDadosNoArquivo(String area, String dados) {
		try {
			FileWriter arq = new FileWriter(getDestinoArquivo(area), true);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println(dados);
			arq.close();
		} catch (IOException e) {
			System.err.printf("N�o foi poss�vel gravar o arquivo: %s.\n", e.getMessage());
		}
		
	}
	
	private int pegarProximoId(String area) {
		try {
			return contarLinhas(getDestinoArquivo(area)) + 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//Retorna o caminho para o arquivo de dados
	private String getDestinoArquivo(String area) {
		switch (area) {
			case "usuarios":
				return new File(USUARIOS_PATH).getAbsolutePath();
			case "cidades":
				return new File(CIDADES_PATH).getAbsolutePath();
			case "alunos":
				return new File(ALUNOS_PATH).getAbsolutePath();
		}
		
		return null;
	}
	
	//Recupera dados de um TXT de acordo com o �ndice e o id da informa��o desejada.
	//Observa��o: * Para recuperar informa��es de uma �nica linha, passar o id da respectiva linha.
	//            * Para recuperar informa��es de todas as linhas, passar o id como '-1'. 
	public ArrayList<String> recuperarDados(int indicesInformacao[], String txtAlvo, String id) {
		try {			
			FileReader arq = new FileReader(getDestinoArquivo(txtAlvo));
			lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			ArrayList<String> dadosRecuperados = new ArrayList<>();
			
			//Recupera as informa��es de todas as linhas.
			if ("-1".equals(id)) {
				while (linha != null) {
					String[] verificaLinha = linha.split(",");
					for (int i : indicesInformacao) {
					dadosRecuperados.add(verificaLinha[i]);
					}
					linha = lerArq.readLine();
			  }
			} 
			//Recupera as informa��es de uma linha espec�fica.
			else {
				while (linha != null) {
					String[] verificaLinha = linha.split(",");
					for (int i : indicesInformacao) {
						if(verificaLinha[0].equals(id)) {
					       dadosRecuperados.add(verificaLinha[i]);
						}
					}
					linha = lerArq.readLine();
			  }
			}			 
			 
			 return dadosRecuperados;
		} catch (IOException e) {
			System.err.printf("Erro na leitura do arquivo: %s.\n", e.getMessage());
		}
		return null;
	}
	
	//M�todo para a substitui��o de informa��es em qualquer TXT.
	public void substituirInformacao(String txtAlvo, String textoAntigo, String textoNovo) {
		try {
	        BufferedReader arquivo = new BufferedReader(new FileReader(getDestinoArquivo(txtAlvo)));
	        String linha;
	        StringBuffer inputBuffer = new StringBuffer();
	        
	        //Alimenta o Buffer 'inputBuffer' com os dados do arquivo original.
	        while ((linha = arquivo.readLine()) != null) {
	            inputBuffer.append(linha);
	            inputBuffer.append('\n');
	        }
	        
	        //Transforma o conte�do do Buffer em uma String.
	        String inputStr = inputBuffer.toString();

	        arquivo.close();	        
	        
	        //Substitui a informa��o.
	        inputStr = inputStr.replace(textoAntigo, textoNovo);
	        
	        //Grava o inputStr com a nova informa��o sobre o arquivo original.
	        FileOutputStream fileOut = new FileOutputStream(getDestinoArquivo(txtAlvo));
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();
	        
		} catch(Exception e) {
	        System.err.println("Problema na leitura do arquivo.");
	    }
	}
	
	//Pega a quantidade de linhas no arquivo (c�digo de terceiros)
	//TODO: encontrar forma melhor de fazer esta fun��o
	private int contarLinhas(String filename) throws IOException {
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(filename));
			
			try {
		        byte[] c = new byte[1024];

		        int readChars = is.read(c);
		        if (readChars == -1) {
		            // bail out if nothing to read
		            return 0;
		        }

		        // make it easy for the optimizer to tune this loop
		        int count = 0;
		        while (readChars == 1024) {
		            for (int i=0; i<1024;) {
		                if (c[i++] == '\n') {
		                    ++count;
		                }
		            }
		            readChars = is.read(c);
		        }

		        // count remaining characters
		        while (readChars != -1) {
		            for (int i=0; i<readChars; ++i) {
		                if (c[i] == '\n') {
		                    ++count;
		                }
		            }
		            readChars = is.read(c);
		        }

		        return count == 0 ? 1 : count;
		    } finally {
		        is.close();
		    }
		} catch(FileNotFoundException e) {
			return 0;
		}
	}
}
