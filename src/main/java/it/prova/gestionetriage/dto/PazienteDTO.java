package it.prova.gestionetriage.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;

public class PazienteDTO {

	private Long id;
	private String nome;
	private String cognome;
	private Date dataRegistrazione;
	private String codiceFiscale;
	private StatoPaziente statoPaziente;
	
	@JsonIgnoreProperties(value = { "pazienteAttualmenteInVisita" })
	private DottoreDTO dottore;

	public PazienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public PazienteDTO(String nome, String cognome, Date dataRegistrazione, String codiceFiscale,
			StatoPaziente statoPaziente, DottoreDTO dottore) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public PazienteDTO(Long id, String nome, String cognome, Date dataRegistrazione, String codiceFiscale,
			StatoPaziente statoPaziente, DottoreDTO dottore) {
		super();
		this.id = id;
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

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public StatoPaziente getStatoPaziente() {
		return statoPaziente;
	}

	public void setStatoPaziente(StatoPaziente statoPaziente) {
		this.statoPaziente = statoPaziente;
	}

	public DottoreDTO getDottore() {
		return dottore;
	}

	public void setDottore(DottoreDTO dottore) {
		this.dottore = dottore;
	}

	public Paziente buildPazienteModel() {

		if (this.dottore == null)
			return new Paziente(this.id, this.nome, this.cognome, this.dataRegistrazione, this.codiceFiscale,
					this.statoPaziente);

		return new Paziente(this.id, this.nome, this.cognome, this.dataRegistrazione, this.codiceFiscale,
				this.statoPaziente, this.dottore.buildDottoreModel());
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente input) {

		if (input.getDottore() == null)
			return new PazienteDTO(input.getId(), input.getNome(), input.getCognome(), input.getDataRegistrazione(),
					input.getCodiceFiscale(), input.getStatoPaziente(), null);

		return new PazienteDTO(input.getId(), input.getNome(), input.getCognome(), input.getDataRegistrazione(),
				input.getCodiceFiscale(), input.getStatoPaziente(),
				DottoreDTO.buildDottoreDTOFromModel(input.getDottore()));
	}

}
