package ttps.spring.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.interfaces.MascotaDAO;
import ttps.spring.model.Mascota;
import ttps.spring.model.Usuario;

@Repository("mascotaDAO")
public class MascotaDAOHibernateJPA extends GenericDAOHibernateJPA<Mascota> implements MascotaDAO {

	public MascotaDAOHibernateJPA() {
		super(Mascota.class);
	}
	
	@Override
	public List<Mascota> getSinVeterinario() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Mascota AS m"
							+" WHERE m.veterinario = null ");

		List<Mascota> resultado = (List<Mascota>)consulta.getResultList();
		
		return resultado;
	}

}
