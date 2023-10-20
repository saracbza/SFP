package entidades;
import java.util.*;

public class Funcionario {
    private int codigo;
	private String nome;
	private String cpf;
	private Date data_nasc = new Date();
	private Date data_contrat = new Date();
	private int dependentes;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}
	public Date getData_contrat() {
		return data_contrat;
	}
	public void setData_contrat(Date data_contrat) {
		this.data_contrat = data_contrat;
	}
	public int getDependentes() {
		return dependentes;
	}
	public void setDependentes(int dependentes) {
		this.dependentes = dependentes;
	}
	
}
