package banco;

import java.sql.SQLException;
import entidades.Cargo;

public class CargoDAO {

		private BD bd;

		public CargoDAO() {
			bd = new BD();
		}
		
		/**
		 * Lista todos os cargos cadastrados no sistema
		 */
		public void listar() {
			if(bd.getConnection()) {
				String sql = "SELECT * FROM cargo";
				try {
					bd.st = bd.con.prepareStatement(sql);
					bd.rs = bd.st.executeQuery();
					
					while(bd.rs.next()) { 
						Cargo c = new Cargo();
						c.setCodigo(bd.rs.getInt(1));
						c.setNome(bd.rs.getString(2));
						c.setValor_hr(bd.rs.getFloat(3));
						
						System.out.println(c);
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
