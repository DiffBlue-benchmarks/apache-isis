/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.core.runtime.systemusinginstallers;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.internal.reflection._Reflect.Discovery;

/**
 * 
 * Package private helper class. Finds PersistenceCapable types.
 *
 */
class PersistenceCapableTypeFinder {

	@SuppressWarnings("unchecked")
	static Set<Class<?>> find(Discovery discovery) {

		final Set<Class<?>> types = new LinkedHashSet<>();

		discovery.getTypesAnnotatedWith(PersistenceCapable.class).stream()
		.forEach(type->{

			if(type.isAnnotation()) {

				// We have an annotation, that is annotated with @PersistenceCapable,
				// this requires special treatment: 
				// Search for any classes annotated with this (meta-)annotation.
				
				discovery.getTypesAnnotatedWith((Class<? extends Annotation>) type).stream()
				.filter(x->!x.isAnnotation())
				.forEach(types::add);

			} else {

				types.add(type);

			}	
		});

		return types;
	}

}
