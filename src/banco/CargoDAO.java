package banco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cargo;

public class CargoDAO {

		private BD bd;

		public CargoDAO() {
			bd = new BD();
		}
		
		/**
		 * Busca todos os cargos cadastrados no banco
		 * @param sql - comando de solicitacao de informacoes para o banco
		 * @return - lista com todos os cargos
		 */
		public List<Cargo> listar(String sql) {
			List<Cargo> lista = new ArrayList<Cargo>();
			bd.getConnection();
				try {
					bd.st = bd.con.prepareStatement(sql);
					bd.rs = bd.st.executeQuery();
					
					while(bd.rs.next()) { 
						Cargo c = new Cargo(bd.rs.getInt(1), 
								bd.rs.getString(2), bd.rs.getFloat(3));
						lista.add(c);
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
	}
