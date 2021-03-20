package modelo;

import java.util.List;
import dto.PersonaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PersonaDAO;

public class Agenda {
	private PersonaDAO persona;

	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
	}

	public void agregarPersona(PersonaDTO nuevaPersona) {
		this.persona.insert(nuevaPersona);
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

}
