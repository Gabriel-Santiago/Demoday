package mandacaru.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="imoveis")
public class Imovel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String endereco;
	private int metros_quadrados_de_terreno;
	private int quantidade_de_quartos;
	private int quantidade_de_banheiros;
	private int quantidade_de_vagas_de_garagem;
	private double preco;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "dono")
	@JsonIgnore
	private Usuario usuario;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getMetros_quadrados_de_terreno() {
		return metros_quadrados_de_terreno;
	}

	public void setMetros_quadrados_de_terreno(int metros_quadrados_de_terreno) {
		this.metros_quadrados_de_terreno = metros_quadrados_de_terreno;
	}

	public int getQuantidade_de_quartos() {
		return quantidade_de_quartos;
	}

	public void setQuantidade_de_quartos(int quantidade_de_quartos) {
		this.quantidade_de_quartos = quantidade_de_quartos;
	}

	public int getQuantidade_de_banheiros() {
		return quantidade_de_banheiros;
	}

	public void setQuantidade_de_banheiros(int quantidade_de_banheiros) {
		this.quantidade_de_banheiros = quantidade_de_banheiros;
	}

	public int getQuantidade_de_vagas_de_garagem() {
		return quantidade_de_vagas_de_garagem;
	}

	public void setQuantidade_de_vagas_de_garagem(int quantidade_de_vagas_de_garagem) {
		this.quantidade_de_vagas_de_garagem = quantidade_de_vagas_de_garagem;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Imovel [id=" + id + ", titulo=" + titulo + ", endereco=" + endereco + ", metros_quadrados_de_terreno="
				+ metros_quadrados_de_terreno + ", quantidade_de_quartos=" + quantidade_de_quartos
				+ ", quantidade_de_banheiros=" + quantidade_de_banheiros + ", quantidade_de_vagas_de_garagem="
				+ quantidade_de_vagas_de_garagem + ", preco=" + preco + ", status=" + status + "]";
	}

	public Imovel(int id, String titulo, String endereco, int metros_quadrados_de_terreno, int quantidade_de_quartos,
			int quantidade_de_banheiros, int quantidade_de_vagas_de_garagem, double preco, String status,
			Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.endereco = endereco;
		this.metros_quadrados_de_terreno = metros_quadrados_de_terreno;
		this.quantidade_de_quartos = quantidade_de_quartos;
		this.quantidade_de_banheiros = quantidade_de_banheiros;
		this.quantidade_de_vagas_de_garagem = quantidade_de_vagas_de_garagem;
		this.preco = preco;
		this.status = status;
		this.usuario = usuario;
	}

	public Imovel() {
		super();
	}
}
