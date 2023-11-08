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
     * Lista nome, cargo e senioridade dos funcionarios cadastrados
     */

	public void listarFuncionario() {
		if(bd.getConnection()) {
			String sql = "SELECT f.nome, c.nome_cargo, s.nivel \r\n"
					+ "FROM cargo c \r\n"
					+ "inner join cargo_funcionario cf ON c.cod_cargo = cf.cod_cargo \r\n"
					+ "inner join funcionario f ON f.cod_func = cf.cod_func \r\n"
					+ "inner join sen_funcionario sf ON sf.cod_func = f.cod_func \r\n"
					+ "inner join senioridade s ON sf.cod_sen = s.cod_sen";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) { 
					Funcionario f = new Funcionario();
					Cargo c = new Cargo();
					Senioridade s = new Senioridade();
					f.setNome(bd.rs.getString(1));
					c.setNome(bd.rs.getString(2));
					s.setNivel(bd.rs.getString(3));
					
					System.out.println("Nome: "+f.getNome()+"\nCargo: "+c.getNome()+" "+s.getNivel());
					System.out.println("----");
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
}
