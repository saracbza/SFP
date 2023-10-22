package entidades;

public class Cargo {
    private int codigo;
	private String nome;
	private float valor_hr;
	
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
	public float getValor_hr() {
		return valor_hr;
	}
	public void setValor_hr(float valor_hr) {
		this.valor_hr = valor_hr;
	}

	public String toString() {
		return "["+codigo+","+nome+","+valor_hr+"]";
	}
}
