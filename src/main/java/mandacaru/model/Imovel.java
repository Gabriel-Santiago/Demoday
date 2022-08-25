package mandacaru.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="imoveis")
public class Imovel {
	
	@Id
	@GeneratedValue(generator="sequence",strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="sequence",sequenceName = "imoveis_id_seq", allocationSize=1)
	private int id;
	private String titulo;
	private String endereco;
	private double metros_quadrados_de_terreno;
	private int quantidade_de_quartos;
	private int quantidade_de_banheiros;
	private int quantidade_de_vagas_de_garagem;
	private double preco;
	private String status;
	private String documento;
	private String processo;
	
	
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

	public double getMetros_quadrados_de_terreno() {
		return metros_quadrados_de_terreno;
	}

	public void setMetros_quadrados_de_terreno(double metros_quadrados_de_terreno) {
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
	
	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	@Override
	public String toString() {
		return "Imovel [id=" + id + ", titulo=" + titulo + ", endereco=" + endereco + ", metros_quadrados_de_terreno="
				+ metros_quadrados_de_terreno + ", quantidade_de_quartos=" + quantidade_de_quartos
				+ ", quantidade_de_banheiros=" + quantidade_de_banheiros + ", quantidade_de_vagas_de_garagem="
				+ quantidade_de_vagas_de_garagem + ", preco=" + preco + ", status=" + status + ", documento="
				+ documento + ", processo=" + processo + ", usuario=" + usuario + "]";
	}
	
	public Imovel(int id, String titulo, String endereco, double metros_quadrados_de_terreno, int quantidade_de_quartos,
			int quantidade_de_banheiros, int quantidade_de_vagas_de_garagem, double preco, String status,
			String documento, String processo, Usuario usuario) {
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
		this.documento = documento;
		this.processo = processo;
		this.usuario = usuario;
	}

	public Imovel() {
		super();
	}
}
