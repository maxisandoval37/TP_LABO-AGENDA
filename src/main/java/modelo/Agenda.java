package modelo;

import java.util.List;

import dto.DomicilioDTO;
import dto.EtiquetaDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.SignoZodiacoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.EtiquetaDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.SignoZodiacoDAO;

public class Agenda {
	private PersonaDAO persona;
	private DomicilioDAO domicilio;
	private LocalidadDAO localidad;
	private EtiquetaDAO etiqueta;
	private SignoZodiacoDAO signoZodiaco;

	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
		this.domicilio = metodo_persistencia.createDomicilioDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.etiqueta = metodo_persistencia.createEtiquetaDAO();
		this.signoZodiaco = metodo_persistencia.createSignoZodiacoDAO();
	}

	public void agregarPersona(PersonaDTO nueva_persona) {
		this.persona.insert(nueva_persona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		this.persona.delete(persona_a_eliminar);
	}

	public void editarPersona(int id_a_editar,PersonaDTO persona_nueva) {
		this.persona.update(id_a_editar,persona_nueva);
	}

	public List<PersonaDTO> obtenerPersonas() {
		return this.persona.readAll();
	}
	
	public void agregarDomicilio(DomicilioDTO nuevo_domicilio) {
		this.domicilio.insert(nuevo_domicilio);
	}

	public void borrarDomicilio(DomicilioDTO domicilio_a_eliminar) {
		this.domicilio.delete(domicilio_a_eliminar);
	}

	public void editarDomicilio(int id_a_editar,DomicilioDTO domicilio_nuevo) {
		this.domicilio.update(id_a_editar,domicilio_nuevo);
	}

	public List<DomicilioDTO> obtenerDomicilios() {
		return this.domicilio.readAll();
	}
	
	public Integer obtenerUltimoIdDomicilio() {
		try {
			return obtenerDomicilios().get(obtenerDomicilios().size() - 1).getId();
		} catch (Exception e) {
			return 0;
		}
	}
	public void agregarLocalidad(LocalidadDTO nueva_localidad) {
		this.localidad.insert(nueva_localidad);
	}
	
	public void editarLocalidad(int id_a_editar,LocalidadDTO localidad_nueva) {
		this.localidad.update(id_a_editar, localidad_nueva);
	}
	
	public boolean borrarLocalidad(LocalidadDTO localidad_a_eliminar) {
		return this.localidad.delete(localidad_a_eliminar);
	}
	
	public void insertarLocalidadesGenericas() {
		this.localidad.insertGenericLocations();
	}
	
	public List<LocalidadDTO> obtenerLocalidades(){
		return this.localidad.readAll();
	}

	public void agregarEtiqueta(EtiquetaDTO nuevaEtiqueta) {
		this.etiqueta.insert(nuevaEtiqueta);
	}

	public void borrarEtiqueta(EtiquetaDTO etiqueta_a_eliminar){
		this.etiqueta.delete(etiqueta_a_eliminar);
	}

	public void editarEtiqueta(int id_a_editar,EtiquetaDTO etiqueta_nueva) {
		this.etiqueta.update(id_a_editar,etiqueta_nueva);
	}

	public void insertarEtiquetasGenericas() {
		this.etiqueta.insertGenericTags();
	}

	public List<EtiquetaDTO> obtenerEtiquetas() {
		return this.etiqueta.readAll();
	}
	
	public void insertarSignosGenericos () {
		this.signoZodiaco.insertGenericSigno();
	}
	
	public List<SignoZodiacoDTO> obtenerSignos () {
		return this.signoZodiaco.readAll();
	}

}
