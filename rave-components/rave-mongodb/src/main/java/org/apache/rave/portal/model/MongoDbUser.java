/*
 * Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.rave.portal.model;

import com.google.common.collect.Lists;
import org.apache.rave.portal.model.impl.UserImpl;
import org.apache.rave.portal.repository.PageLayoutRepository;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.List;

/**
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
public class MongoDbUser extends UserImpl {

    private List<String> authorityCodes;
    private List<MongoDbPersonAssociation> friends;

    @XmlTransient @JsonIgnore
    private PageLayoutRepository pageLayoutRepository;

    public MongoDbUser(long id) {
        super(id);
    }

    public MongoDbUser() {
    }

    public List<String> getAuthorityCodes() {
        return authorityCodes;
    }

    public void setAuthorityCodes(List<String> authorityCodes) {
        this.authorityCodes = authorityCodes;
    }

    public List<MongoDbPersonAssociation> getFriends() {
        return friends;
    }

    public void setFriends(List<MongoDbPersonAssociation> friends) {
        this.friends = friends;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        if (authorityCodes != null) {
            for (String code : authorityCodes) {
                grantedAuthorities.add(new SimpleGrantedAuthority(code));
            }
        }
        return grantedAuthorities;
    }

    @Override
    public void addAuthority(Authority authority) {
        if(!authorityCodes.contains(authority.getAuthority())) {
            authorityCodes.add(authority.getAuthority());
        }
    }

    @Override
    public void removeAuthority(Authority authority) {
        if(authorityCodes.contains(authority.getAuthority())) {
            authorityCodes.remove(authority.getAuthority());
        }
    }

    @Override
    public PageLayout getDefaultPageLayout() {
        PageLayout layout = super.getDefaultPageLayout();
        if(layout == null) {
            layout = pageLayoutRepository.getByPageLayoutCode(super.getDefaultPageLayoutCode());
            super.setDefaultPageLayout(layout);
        }
        return layout;
    }

    public void setPageLayoutRepository(PageLayoutRepository pageLayoutRepository) {
        this.pageLayoutRepository = pageLayoutRepository;
    }
}
