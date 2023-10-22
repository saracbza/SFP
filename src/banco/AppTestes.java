package banco;

public class AppTestes {

	public static void main(String[] args) {
		//CargoDAO cargo = new CargoDAO();
		FuncionarioDAO funcionario = new FuncionarioDAO();
		//SenioridadeDAO senioridade = new SenioridadeDAO();

		//cargo.listar();
		//funcionario.listarTudo();
		//senioridade.listar();
        funcionario.listarFuncionario();
	}
}