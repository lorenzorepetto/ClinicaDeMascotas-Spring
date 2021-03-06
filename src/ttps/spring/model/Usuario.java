package ttps.spring.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( { "mascotas", "mascotas_atendidas", "eventos" } )
@Entity
public class Usuario {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String nombre;
	private String apellido;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fecha_nacimiento;

	private String telefono;
	private Boolean activo;	
	
	@Column(nullable=true) //puede estar vacío si no es veterinario
	private String nombre_consultorio;
	
	@Column(nullable=true)
	private String domicilio_consultorio;
	
	@Column(nullable=true)
	private String foto;
	
	@Column(nullable=true)
	private String matricula;

	@OneToMany(mappedBy="duenio")
	List<Mascota> mascotas;
	

	@OneToMany(mappedBy="veterinario")
	List<Mascota> mascotas_atendidas;
	

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(	name="Usuario_tiene_rol",
				joinColumns=@JoinColumn(name="id_usuario",
										referencedColumnName="id"),
				inverseJoinColumns=@JoinColumn(name="id_rol",
											   referencedColumnName="id"))
	List<Rol> roles;
	
	
	@OneToMany(mappedBy="usuario_creador")
	@LazyCollection(LazyCollectionOption.TRUE)
	List<Evento> eventos;
	
	
	//Constructores
	public Usuario() {
		super();
		//inicializo colecciones
		this.mascotas = new ArrayList<Mascota>();
		this.mascotas_atendidas = new ArrayList<Mascota>();
		this.roles = new ArrayList<Rol>();
		this.eventos = new ArrayList<Evento>();
	}
	
	
//	Constructor con datos
	public Usuario(String email, String password, String nombre, String apellido, Date fecha_nacimiento,
			String telefono, Boolean activo, String foto) {
		super();
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.telefono = telefono;
		this.activo = activo;
		this.foto = foto;
		
		//inicializo colecciones
		this.mascotas = new ArrayList<Mascota>();
		this.mascotas_atendidas = new ArrayList<Mascota>();
		this.roles = new ArrayList<Rol>();
		this.eventos = new ArrayList<Evento>();
	}
	

	
	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
	public List<Evento> getEventos() {
		return eventos;
	}


	//Getters y Setters
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	

	public List<Rol> getRoles() {
		return roles;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public String getNombre_consultorio() {
		return nombre_consultorio;
	}


	public void setNombre_consultorio(String nombre_consultorio) {
		this.nombre_consultorio = nombre_consultorio;
	}


	public String getDomicilio_consultorio() {
		return domicilio_consultorio;
	}


	public void setDomicilio_consultorio(String domicilio_consultorio) {
		this.domicilio_consultorio = domicilio_consultorio;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public List<Mascota> getMascotas() {
		return mascotas;
	}



	public List<Mascota> getMascotas_atendidas() {
		return mascotas_atendidas;
	}
	
	

	//Metodos
	
	
	public void agregarEvento(Evento evento) {
		this.eventos.add(evento);
	}
	
	
	public void eliminarEvento(Evento evento) {
		this.eventos.remove(evento);	
	}
	
	public void eliminarMascota (Mascota mascota) {
		this.mascotas.remove(mascota);	
	}
	
	public void agregarMascota( Mascota mascota ) {
		this.mascotas.add(mascota);
	}
	
	public void agregarMascotaAtendida( Mascota mascota ) {
		this.mascotas_atendidas.add(mascota);
	}
	
	public void buscarMascota(Mascota m) {
		
	}
	
	public List<String> getRolesNombres() {
		List<String> roles = new ArrayList<String>();
		for (Rol r : this.getRoles()) {
			roles.add(r.getNombre());
		}
		return roles;
	}
	
	public void agregarRol(Rol rol) {
		this.roles.add(rol);
	}
	
	public void quitarRol(Rol rol) {
		this.roles.remove(rol);
	}

	
}
