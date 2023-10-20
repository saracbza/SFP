package entidades;
import java.util.*;

public class Pagamento {
    private int codigo;
	private int codigo_func;
	private String cargo_senioridade;
	private float salario;
	private float inss;
	private float irrf;
	private float fgts;
	private Date data_pagamento = new Date();
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigo_func() {
		return codigo_func;
	}
	public void setCodigo_func(int codigo_func) {
		this.codigo_func = codigo_func;
	}
	public String getCargo_senioridade() {
		return cargo_senioridade;
	}
	public void setCargo_senioridade(String cargo_senioridade) {
		this.cargo_senioridade = cargo_senioridade;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public float getInss() {
		return inss;
	}
	public void setInss(float inss) {
		this.inss = inss;
	}
	public float getIrrf() {
		return irrf;
	}
	public void setIrrf(float irrf) {
		this.irrf = irrf;
	}
	public float getFgts() {
		return fgts;
	}
	public void setFgts(float fgts) {
		this.fgts = fgts;
	}
	public Date getData_pagamento() {
		return data_pagamento;
	}
	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}
	
}
