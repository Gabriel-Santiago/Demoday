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
@Table(name="imagens")
public class Imagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foto_generator")
	@SequenceGenerator(name="foto_generator", sequenceName = "imagens_id_seq", allocationSize=1)
	private int id;
	private String nome;
	private String tipo;
	private byte[] foto;
	
	@ManyToOne
	@JoinColumn(name = "imovel")
	@JsonIgnore
	private Imovel imovel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Imagem(int id, String nome, byte[] foto, Imovel imovel) {
		super();
		this.id = id;
		this.nome = nome;
		this.foto = foto;
		this.imovel = imovel;
	}

	public Imagem() {
		super();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
