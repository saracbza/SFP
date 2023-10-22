package banco;

import java.sql.SQLException;
import entidades.Cargo;
import entidades.Funcionario;
import entidades.Senioridade;

public class FuncionarioDAO {
	private BD bd;
	
	public FuncionarioDAO() {
		bd = new BD();
	}
	
	/**
	 * Lista todos os funcionarios cadastrados no sistema
	 */
	public void listarTudo() {
		if(bd.getConnection()) {
			String sql = "SELECT * FROM funcionario";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) { 
					Funcionario f = new Funcionario();
					f.setCodigo(bd.rs.getInt(1));
					f.setNome(bd.rs.getString(2));
					f.setCpf(bd.rs.getString(3));
					f.setData_nasc(bd.rs.getDate(4));
					f.setData_contrat(bd.rs.getDate(5));
					f.setDependentes(bd.rs.getInt(6));
					
					System.out.println(f);
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
     * Lista nome, cargo e senioridade dos funcionarios cadastrados
     */

	public void listarFuncionario() {
		if(bd.getConnection()) {
			String sql = "SELECT f.nome AS Funcionário, c.nome_cargo AS Cargo, s.nivel AS Senioridade \r\n"
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
