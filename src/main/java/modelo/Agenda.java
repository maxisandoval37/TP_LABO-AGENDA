package modelo;

import java.util.List;
import dto.EtiquetaDTO;
import dto.PersonaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.EtiquetaDAO;
import persistencia.dao.interfaz.PersonaDAO;

public class Agenda {
	private PersonaDAO persona;
	private EtiquetaDAO etiqueta;

	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
		this.etiqueta = metodo_persistencia.createEtiquetaDAO();
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

}
