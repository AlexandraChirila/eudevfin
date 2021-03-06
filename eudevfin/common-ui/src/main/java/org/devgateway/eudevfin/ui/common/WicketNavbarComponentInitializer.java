/*******************************************************************************
 * Copyright (c) 2014 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *******************************************************************************/
package org.devgateway.eudevfin.ui.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.wicket.Component;
import org.apache.wicket.Page;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.ComponentPosition;

/**
 * Annotation for defining the wicket {@link Navbar} component methods Place
 * this to methods that return {@link Navbar} components, the methods should
 * receive one single parameter, the {@link Page} of the caller and return the
 * {@link Component} that can be added to the {@link Navbar} The logic of this
 * is to have multiple navbar components spread around WAR overlays and the
 * system will find them on runtime and initialize the {@link Navbar}
 * accordingly, so that you can see menus for WARs that are currently overlayed.
 * 
 * @author mihai
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface WicketNavbarComponentInitializer {

	/**
	 * The {@link ComponentPosition} for the returned component. Use
	 * Navbar.ComponentPosition items
	 * 
	 * @return
	 */
	ComponentPosition position();
	
	/**
	 * Use this to provide ordered view of the menu components
	 * @return
	 */
	int order();
	
	/**
	 * Should the annotated method be considered disabled, thus never invoked by the infrastructure
	 * @return
	 */
	boolean disabled() default false;
}
