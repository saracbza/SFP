package testes;

public class Calculos {
    /**
	 * Executa os métodos de cálculo e retorna o salário liquido após efetuar as deduções. 
	 * @return - pagamento final	
	 */
	public static float calculo_pagamento(float valor_hr, float porc_rec, float horas_trab, float horas_ext, int dep) {
		
		float salario_base = salario_bruto (valor_hr, porc_rec, horas_trab, horas_ext);
		//float valor_fgts = fgts (salario_base);
		float valor_inss = inss(salario_base);
		float salario_sem_inss = salario_base - valor_inss;
		float valor_ir = irrf(salario_sem_inss, dep);

		float salario_liquido = salario_base - (valor_inss + valor_ir);
		
		return salario_liquido;
	}

	/**
	* A partir da quantidade de horas extras trabalhadas, saberá a porcentagem extra aplicada no salário. 	
	* @param horas_ext - quantidade de horas extras trabalhadas
	* @return - a porcentagem extra a ser aplicada no salário.
	*/
	public static float porcentagem_extra (float horas_ext){ //sem trabalho no fds - regra de negócio
		float  porc_extra;
		if (horas_ext < 25)
			porc_extra = 0.5f;
		else if (horas_ext >= 25 && horas_ext <= 40)
			porc_extra = 0.7f;
		else
			porc_extra = 0.85f;
			
		return porc_extra;
	}

	/**
	* Calcula o salário bruto considerando o valor a se receber por hora de trabalho, porcentagem de reconhecimento, horas trabalhadas e a porcentagem a mais por hora extra. 	 
	* @param valor_hr - valor recebido por cada hora de trabalho
	* @param porc_rec - porcentagem que varia de acordo com a senioridade do funcionário
	* @param horas_trab - quantidade de horas trabalhadas
	* @param horas_ext - quantidade de horas extras trabalhadas
	* @return - salário bruto que servirá de base para cálculo dos impostos.
	*/
	public static float salario_bruto (float valor_hr, float porc_rec, float horas_trab, float horas_ext){
		float porc_extra = porcentagem_extra(horas_ext);
		float salario_bruto = (valor_hr*horas_trab) + ((valor_hr*horas_trab)*porc_rec);
		float valor_hora_ext = (valor_hr*horas_ext) + ((valor_hr*horas_ext)*porc_extra);
		salario_bruto += valor_hora_ext;
		
		return salario_bruto;
	}

	/**
	* Calcula o valor do inss a ser deduzido do salário levando em consideração os valores intituidos por lei. 	
	* @param salario_base - salário bruto calculado a partir das horas de trabalho
	* @return - valor do inss.
	*/
	public static float inss (float salario_base){
		float aliquota_inss, valor_inss;
		if (salario_base <= 1320)
			aliquota_inss = 0.075f;
		else if (salario_base >= 2571.30 && salario_base <= 3856.94)
			aliquota_inss = 0.12f;
		else //(salario_base >= 3856.95 && salario_base <= 7507.49)
			aliquota_inss = 0.14f;

		valor_inss = salario_base*aliquota_inss;
		return valor_inss;
	}

	/**
	* Calcula o valor do irrf a ser deduzido do salário levando em consideração os valores intituidos por lei. 	
	* @param salario_sem_inss - cálculo feito do salário bruto com a dedução do inss
	* @param dep - a quantidade de dependentes que o funcionário possui
	* @return - valor do irrf.
	*/
	public static float irrf (float salario_sem_inss, int dep){
		float aliquota_irrf, deducao;
		float valor_dep = 189.59f*dep;
		float base_calculo = salario_sem_inss - valor_dep;

		if (base_calculo <= 2112){
			aliquota_irrf = 0;
			deducao = 0;
	}
		else if (base_calculo >= 2112.01 && base_calculo <= 2826.65){
			aliquota_irrf = 0.075f;
			deducao = 158.40f;
	}
		else if (base_calculo >= 2826.66 && base_calculo <= 3751.05){
			aliquota_irrf = 0.15f;
			deducao = 370.40f;
	}
		else if (base_calculo >= 3751.06 && base_calculo <= 4664.68){
			aliquota_irrf = 0.225f;
			deducao = 651.73f;
	}
		else {
			aliquota_irrf = 0.275f;
			deducao = 884.96f;
		}

		float valor_irrf = (base_calculo*aliquota_irrf) - deducao;
		return valor_irrf;
	}

	/**
	* Calcula o valor do fgts levando em consideração os valores intituidos por lei. 	
	* @param salario_base - salário bruto calculado a partir das horas de trabalho
	* @return - valor do fgts.
	*/
	public static float fgts (float salario_base){
		float aliquota_fgts = 0.08f;
		float valor_fgts = salario_base*aliquota_fgts;

		return valor_fgts;
	}
}
