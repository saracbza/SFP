package entidades;

public class Senioridade {
    private int codigo;
	private String nivel;
	private float porcentagem_rec;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public float getPorcentagem_rec() {
		return porcentagem_rec;
	}
	public void setPorcentagem_rec(float porcentagem_rec) {
		this.porcentagem_rec = porcentagem_rec;
	}

	public String toString() {
		return "["+codigo+","+nivel+","+porcentagem_rec+"]";
	}
}
