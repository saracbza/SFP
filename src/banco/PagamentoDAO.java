package banco;

import java.sql.SQLException;
import java.util.Date;
import entidades.Pagamento;

public class PagamentoDAO {
	private BD bd;
	
	public PagamentoDAO() {
		bd = new BD();
	}
	
	/**
	 * Lista todos os pagamentos registrados no sistema
	 */
	public void listar() {
		if(bd.getConnection()) {
			String sql = "SELECT * FROM pagamento";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) { 
					Pagamento p = new Pagamento();
					p.setCodigo(bd.rs.getInt(1));
					p.setCodigo_func(bd.rs.getInt(2));
					p.setCargo_senioridade(bd.rs.getString(3));
					p.setSalario(bd.rs.getFloat(4));
					p.setInss(bd.rs.getFloat(5));
					p.setIrrf(bd.rs.getFloat(6));
					p.setFgts(bd.rs.getFloat(7));
					p.setData_pagamento(bd.rs.getDate(8));
										
					System.out.println(p);
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

