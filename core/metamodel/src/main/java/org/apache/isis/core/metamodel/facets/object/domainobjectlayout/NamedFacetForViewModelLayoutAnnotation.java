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
package org.apache.isis.core.metamodel.facets.object.domainobjectlayout;

import java.util.List;
import java.util.Objects;

import com.google.common.base.Strings;

import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.ViewModelLayout;
import org.apache.isis.core.metamodel.facetapi.FacetHolder;
import org.apache.isis.core.metamodel.facets.all.named.NamedFacet;
import org.apache.isis.core.metamodel.facets.all.named.NamedFacetAbstract;
import org.apache.isis.core.metamodel.facets.object.domainservicelayout.NamedFacetForDomainServiceLayoutAnnotation;

public class NamedFacetForViewModelLayoutAnnotation extends NamedFacetAbstract {

    public static NamedFacet create(
            final List<ViewModelLayout> viewModelLayouts,
            final FacetHolder holder) {

        return viewModelLayouts.stream()
                .map(ViewModelLayout::named)
                .map(Strings::emptyToNull)
                .filter(Objects::nonNull)
                .findFirst()
                .map(named -> new NamedFacetForViewModelLayoutAnnotation(named, holder))
                .orElse(null);
    }

    private NamedFacetForViewModelLayoutAnnotation(final String value, final FacetHolder holder) {
        super(value, /*escaped*/ true, holder);
    }
}
