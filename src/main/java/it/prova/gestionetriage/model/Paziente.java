package it.prova.gestionetriage.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paziente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private Date dataRegistrazione;
	private String codiceFiscale;
	private StatoPaziente statoPaziente;
	private Dottore dottore;

	public Paziente() {
		// TODO Auto-generated constructor stub
	}

	public Paziente(Long id, String nome, String cognome, Date dataRegistrazione, String codiceFiscale,
			StatoPaziente statoPaziente, Dottore dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public Paziente(String nome, String cognome, Date dataRegistrazione, String codiceFiscale,
			StatoPaziente statoPaziente, Dottore dottore) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Dottore getDottore() {
		return dottore;
	}

	public void setDottore(Dottore dottore) {
		this.dottore = dottore;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoPaziente getStatoPaziente() {
		return statoPaziente;
	}

	public void setStatoPaziente(StatoPaziente statoPaziente) {
		this.statoPaziente = statoPaziente;
	}

}
