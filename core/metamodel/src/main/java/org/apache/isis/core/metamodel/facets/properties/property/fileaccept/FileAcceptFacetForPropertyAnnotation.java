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

package org.apache.isis.core.metamodel.facets.properties.property.fileaccept;

import java.util.List;
import java.util.Objects;

import com.google.common.base.Strings;

import org.apache.isis.applib.annotation.Property;
import org.apache.isis.core.metamodel.facetapi.FacetHolder;
import org.apache.isis.core.metamodel.facets.objectvalue.fileaccept.FileAcceptFacet;
import org.apache.isis.core.metamodel.facets.objectvalue.fileaccept.FileAcceptFacetAbstract;

public class FileAcceptFacetForPropertyAnnotation extends FileAcceptFacetAbstract {

    public static FileAcceptFacet create(
            final List<Property> properties,
            final FacetHolder holder) {

        return properties.stream()
                .map(Property::fileAccept)
                .map(Strings::emptyToNull)
                .filter(Objects::nonNull)
                .findFirst()
                .map(fileAccept -> new FileAcceptFacetForPropertyAnnotation(fileAccept, holder))
                .orElse(null);
    }

    private FileAcceptFacetForPropertyAnnotation(final String value, final FacetHolder holder) {
        super(value, holder);
    }

}
