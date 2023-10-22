package calculos;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class EntradaDados {
    public static void main(String[] args) {
    //receber dados
		String nome = JOptionPane.showInputDialog("Digite o nome do funcionário");
		String cargo = JOptionPane.showInputDialog("Digite o cargo do(a) " + nome);
		float porc_rec = Float.parseFloat(JOptionPane.showInputDialog("Digite a porcentagem de reconhecimento"))/100;
		float valor_hr = Float.parseFloat(JOptionPane.showInputDialog("Quanto " + nome + " recebe por hora?"));
		float horas_trab = Float.parseFloat(JOptionPane.showInputDialog("Quantas horas " + nome + " trabalhou?"));
		float horas_ext = Float.parseFloat(JOptionPane.showInputDialog("Quantas horas extras " + nome + " trabalhou?"));
		int dep = Integer.parseInt(JOptionPane.showInputDialog("Quantos dependentes " + nome + " tem?"));
		//String d = JOptionPane.showInputDialog("Digite uma data de inicio");
		//Date data = new Date(d);
		
		//calcular informacoes
		float salario_liquido = Calculos.calculo_pagamento(valor_hr, porc_rec, horas_trab, horas_ext, dep);
		float salario_base = Calculos.salario_bruto (valor_hr, porc_rec, horas_trab, horas_ext);
		float valor_fgts = Calculos.fgts (salario_base);
		float valor_inss = Calculos.inss(salario_base);
		float salario_sem_inss = salario_base - valor_inss;
		float valor_ir = Calculos.irrf(salario_sem_inss, dep);
		
		// converter para string com duas casas decimais
		DecimalFormat decimal = new DecimalFormat("0.00");
		String salario_liquidof = decimal.format(salario_liquido);
		String salario_basef = decimal.format(salario_base);
		String valor_fgtsf = decimal.format(valor_fgts);
		String valor_inssf = decimal.format(valor_inss);
		String valor_irf = decimal.format(valor_ir);
		
		//calendario
		//int domingos = Calendario.contagemDomingos(data);
		//int diasUteis = Calendario.contagemDiasUteis(data);

		//imprimir informacoes na tela
		JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nCargo: " + cargo + "\n\nSalário Base: " + salario_basef +
				"\nFGTS: " + valor_fgtsf + "\nINSS: " + valor_inssf + "\nIRRF: " + valor_irf + "\nSalário Liquido: " + salario_liquidof);
		//JOptionPane.showMessageDialog(null, "ENTRE " + d + " E HOJE:\nDias uteis:" + diasUteis + 
		//		"\nDomingos: " + domingos);
	}
}