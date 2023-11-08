package banco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cargo;

public class CargoDAO {

		private BD bd;
		private String men, sql;

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

		 /**
		 * Realiza a gravacao de um cargo no banco de dados
		 * @param c - o cargo a ser gravado
		 * @return - mensagem de aviso do processo
		 */
		public String salvar(Cargo c) {
			sql = "INSERT INTO cargo VALUES (?, ?)"; //nome e valor por hora
			try {
				bd.getConnection();
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, c.getNome());
				bd.st.setFloat(2, c.getValor_hr());
				bd.st.executeUpdate();
				
				men = "Cargo "+c.getNome()+" inserido com sucesso!";
			}
			catch(SQLException erro) {
				men = "Falha: verifique se o cargo já existe... " + erro;
			}
			finally {
				bd.close();
			}
			return men;
		}

		 /**
		 * Exclui um cargo a partir de seu codigo
		 * @param cod - codigo do cargo a ser excluido
		 * @return - mensagem de confirmacao da exclusao
		 */
		public String excluir (int cod) {
			sql = "DELETE FROM cargo WHERE cod_cargo = ?";
			try {
				bd.getConnection();
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, cod);
				if (bd.st.executeUpdate()==1) men = "Excluido com sucesso!";
				else men = "Cargo não encontrado";
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
		 * Localiza um cargo a partir de seu nome
		 * @param nome - nome do cargo a ser localizado
		 * @return - o cargo pesquisado
		 */
		public Cargo pesquisar (String nome) {
			Cargo c = new Cargo();
			sql = "SELECT * cargo WHERE nome_cargo LIKE ?";
			try {
				bd.getConnection();
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, "%" + nome + "%");
				bd.rs = bd.st.executeQuery();
				if(bd.rs.next()) {
					c.setCodigo(bd.rs.getInt(1));
					c.setNome(bd.rs.getString(2));
					c.setValor_hr(bd.rs.getFloat(3));
				} else c = null;
		}
			catch(SQLException erro) {
				c = null;
			}
			finally {
				bd.close();
			}
			return c;
		}

		 /**
		 * Editar um cargo a partir do seu codigo
		 * @param c - informacoes novas para atualizacao
		 * @param cod - codigo do cargo a ser alterado
		 * @return - mensagem de confirmacao da alteracao
		 */
		public String editar(Cargo c, int cod) {
			sql = "UPDATE cargo set nome_cargo= ?, valor_por_hr=?\r\n"
				+ "WHERE cod_cargo = ?";
			try {
				bd.getConnection();
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, c.getNome());
				bd.st.setFloat(2, c.getValor_hr());
				bd.st.setFloat(3, cod);
				bd.st.executeUpdate();
				
				men = "Cargo "+c.getNome()+" alterado com sucesso!";
			}
			catch(SQLException erro) {
				men = "Falha: " + erro;
			}
			finally {
				bd.close();
			}
			return men;
		}	
	}
