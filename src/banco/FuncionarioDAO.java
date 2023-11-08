package banco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cargo;
import entidades.Funcionario;
import entidades.Senioridade;

public class FuncionarioDAO {
	private BD bd;
	private String men, sql;
	
	float valor_hr, porcentagem_rec;
	int dependentes;
	String sen, cargo;

	/**
	 * Retorna a senioridade do funcionario para futuro cadastro
	 * @return - senioridade
	 */
	public String senioridade () {
		return sen;
	}
	
	/**
	 * Retorna o cargo do funcionario para futuro cadastro
	 * @return - cargo
	 */
	public String cargo () {
		return cargo;
	}
	
	/**
	 * Retorna quantidade de dependentes do funcionario para futuro calculo do salario
	 * @return - quantidade de dependentes
	 */
	public int dependentes () {
		return dependentes;
	}
	
	/**
	 * Retorna valor que funcionario recebe por hora para futuro calculo do salario
	 * @return - valor por hora que o funcionario recebe de acordo com seu cargo
	 */
	public float valor_hr () {
		return valor_hr;
	}
	
	/**
	 * Retorna porcentagem de reconhecimento (convertida para decimal) do funcionario para futuro calculo do salario
	 * @return - porcentagem de reconhecimento do funcionario de acordo com sua senioridade
	 */
	public float porcentagem_rec () {
		porcentagem_rec /= 100;
		return porcentagem_rec;
	}
	
	public FuncionarioDAO() {
		bd = new BD();
	}
	
	/**
	 * Retorna todos os funcionarios cadastrados no banco
	 * @param sql - comando para banco
	 * @return - lista com todas os funcionarios
	 */
	public List<Funcionario> listarTudo(String sql) {
		List<Funcionario> lista = new ArrayList<Funcionario>();
		bd.getConnection();
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) { 
					Funcionario f = new Funcionario(bd.rs.getInt(1), bd.rs.getString(2), bd.rs.getString(3),
							bd.rs.getDate(4), bd.rs.getDate(5), bd.rs.getInt(6));
					lista.add(f);
				}
			}
			catch(SQLException e) {
				System.out.println("Erro: " + e);
			}
			finally {
				bd.close();
			}
			return lista;
		}
	
    /**
	 * Busca todos os funcionarios, seu cargo e sua senioridade no banco
	 */
	public void listarFuncionario(int cod) {
		if(bd.getConnection()) {
			String sql = "SELECT f.nome, c.nome_cargo, s.nivel\r\n"
					+ "FROM cargo c \r\n"
					+ "inner join cargo_funcionario cf ON c.cod_cargo = cf.cod_cargo \r\n"
					+ "inner join funcionario f ON f.cod_func = cf.cod_func \r\n"
					+ "inner join sen_funcionario sf ON sf.cod_func = f.cod_func \r\n"
					+ "inner join senioridade s ON sf.cod_sen = s.cod_sen\r\n"
					+ "where f.cod_func = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, cod);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) { 
					cargo = bd.rs.getString(2);
					sen = bd.rs.getString(3);
				}
			}
			catch(SQLException e) {
				System.out.println("Erro: " + e);
			}
			finally {
				bd.close();
			}
		}
		
		else {
			System.out.println("Falha na conexão!");
		}
	}

	/**
	 * Busca as principais informacoes necessarias do funcionario para o calculo do seu pagamento
	 * @param cod - parametro considerado para encontrar funcionario especifico
	 */
	public void listarDados(int cod) {
		if(bd.getConnection()) {
			String sql = "SELECT f.dependentes, c.valor_por_hr, s.porc_rec\r\n"
					+ "	FROM cargo c \r\n"
					+ "	inner join cargo_funcionario cf ON c.cod_cargo = cf.cod_cargo \r\n"
					+ "	inner join funcionario f ON f.cod_func = cf.cod_func \r\n"
					+ "	inner join sen_funcionario sf ON sf.cod_func = f.cod_func \r\n"
					+ "	inner join senioridade s ON sf.cod_sen = s.cod_sen\r\n"
					+ "	where f.cod_func = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, cod);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) { 
					dependentes = bd.rs.getInt(1);
					valor_hr = bd.rs.getFloat(2);
					porcentagem_rec = bd.rs.getFloat(3);
				}
			}
			catch(SQLException e) {
				System.out.println("Erro: " + e);
			}
			finally {
				bd.close();
			}
		}
		
		else {
			System.out.println("Falha na conexão!");
		}
	}	

	/**
	 * Realiza a gravacao de um funcionario no banco de dados
	 * @param f - o funcionario a ser gravado
	 * @return - mensagem de aviso do processo
	 */
	public String salvar(Funcionario f) {
		
		sql = "INSERT INTO funcionario VALUES (?, ?, ?, ?, ?)"; //nome, cpf, data nasc, data contrat, dependentes
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setString(1, f.getNome());
			bd.st.setString(2, f.getCpf());
			bd.st.setInt(5, f.getDependentes());
			
			//converter de java.util.date para java.sql.date
			java.sql.Date data_nasc = new java.sql.Date(f.getData_nasc().getTime());
			java.sql.Date data_contrat = new java.sql.Date(f.getData_contrat().getTime());
			bd.st.setDate(3, data_nasc);
			bd.st.setDate(4, data_contrat);
			
			bd.st.executeUpdate();
			
			men = "Funcionario "+f.getNome()+" inserido com sucesso!";
		}
		catch(SQLException erro) {
			men = "Falha: verifique se o funcionario já existe... " + erro;
		}
		finally {
			bd.close();
		}
		return men;
	}

	/**
	 * Exclui um funcionario a partir de seu codigo
	 * @param cod - codigo do funcionario a ser excluido
	 * @return - mensagem de confirmacao da exclusao
	 */
	public String excluir (int cod) {
		sql = "DELETE FROM funcionario WHERE cod_func = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, cod);
			if (bd.st.executeUpdate()==1) men = "Excluido com sucesso!";
			else men = "Funcionario não encontrado";
	}
		catch(SQLException erro) {
			men = "Falha: " + erro;
		}
		finally {
			bd.close();
		}
		return men;
	}

	/** 
	 * Localiza um funcionario a partir de seu nome
	 * @param nome nome do funcionario a ser localizado
	 * @return o cargo pesquisado
	 */
	public Funcionario pesquisar (String nome) {
		Funcionario f = new Funcionario();
		sql = "SELECT * funcionario WHERE nome LIKE ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setString(1, "%" + nome + "%");
			bd.rs = bd.st.executeQuery();
			if(bd.rs.next()) {
				f.setCodigo(bd.rs.getInt(1));
				f.setNome(bd.rs.getString(2));
				f.setCpf(bd.rs.getString(3));
				f.setData_nasc(bd.rs.getDate(4));
				f.setData_contrat(bd.rs.getDate(5));
				f.setDependentes(bd.rs.getInt(6));
			} else f = null;
	}
		catch(SQLException erro) {
			f = null;
		}
		finally {
			bd.close();
		}
		return f;
	}
}
