package com.ddm.climatempo.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;

@Entity
@Table(name="cliente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {
	@Id
	@Column(name="cd_Cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cd_Cliente;
	
	@NotNull
	@Size (min = 3, max = 100)
	private String nm_Cliente;
	
	@NotNull
	private String email;
	
	private String ds_Senha;
	
		
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinTable(name = "cliente_permissao", joinColumns = @JoinColumn(name = "codigo_cliente")
		,inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
	private List<Permissao> permissoes;

	public long getCd_Cliente() {
		return cd_Cliente;
	}

	public void setCd_Cliente(long cd_Cliente) {
		this.cd_Cliente = cd_Cliente;
	}

	public String getNm_Cliente() {
		return nm_Cliente;
	}

	public void setNm_Cliente(String nm_Cliente) {
		this.nm_Cliente = nm_Cliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDs_Senha() {
		return ds_Senha;
	}

	public void setDs_Senha(String ds_Senha) {
		this.ds_Senha = ds_Senha;
	}


	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cd_Cliente ^ (cd_Cliente >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cd_Cliente != other.cd_Cliente)
			return false;
		return true;
	}

}
