/*******************************************************************************
 * Copyright (c) 2014 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *******************************************************************************/
/**
 * 
 */
package org.devgateway.eudevfin.auth.common.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author mihai We named this {@link PersistedUser} not to confuse it with
 *         {@link User}
 */
@Entity
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PersistedUser implements Serializable, UserDetails {

	private static final long serialVersionUID = 3330162033003739027L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;

	@Column(unique = true)
	private String username;

	@Column(length = 256)
	private String password;
	
	@Transient
	private String plainPassword;
	
	@Transient
	private String plainPasswordCheck;
	
	private boolean enabled = true;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<PersistedAuthority> persistedAuthorities = new TreeSet<PersistedAuthority>();

	@ManyToOne(fetch = FetchType.EAGER)
	private PersistedUserGroup group;

	/**
	 * this is set by CustomJPAUserDetailsService and do not want to persist it
	 */
	@Transient
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the authorites
	 */
	public Set<PersistedAuthority> getPersistedAuthorities() {
		return persistedAuthorities;
	}

	/**
	 * @param authorites
	 *            the authorites to set
	 */
	public void setPersistedAuthorities(Set<PersistedAuthority> authorites) {
		this.persistedAuthorities = authorites;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the group
	 */
	public PersistedUserGroup getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(PersistedUserGroup group) {
		this.group = group;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the passwordCheck
	 */
	public String getPlainPasswordCheck() {
		return plainPasswordCheck;
	}

	/**
	 * @param passwordCheck the passwordCheck to set
	 */
	public void setPlainPasswordCheck(String passwordCheck) {
		this.plainPasswordCheck = passwordCheck;
	}

	/**
	 * @return the plainPassword
	 */
	public String getPlainPassword() {
		return plainPassword;
	}

	/**
	 * @param plainPassword the plainPassword to set
	 */
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	
}
