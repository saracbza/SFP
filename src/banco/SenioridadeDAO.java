package banco;

import java.sql.SQLException;
import entidades.Senioridade;

public class SenioridadeDAO {

	private BD bd;

	public SenioridadeDAO() {
		bd = new BD();
	}
	
	/**
	 * Lista todos as senioridades cadastradas no sistema
	 */
	public void listar() {
		if(bd.getConnection()) {
			String sql = "SELECT * FROM senioridade";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) { 
					Senioridade s = new Senioridade();
					s.setCodigo(bd.rs.getInt(1));
					s.setNivel(bd.rs.getString(2));
					s.setPorcentagem_rec(bd.rs.getFloat(3));
						
					System.out.println(s);
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