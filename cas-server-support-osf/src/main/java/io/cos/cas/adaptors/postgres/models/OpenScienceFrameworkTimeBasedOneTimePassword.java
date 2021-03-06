/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.cos.cas.adaptors.postgres.models;

import org.apache.commons.codec.binary.Base32;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.DatatypeConverter;

/**
 * The Open Science Framework Two Factor User Settings.
 *
 * @author Michael Haselton
 * @author Longze Chen
 * @since 4.1.0
 */
@Entity
@Table(name = "addons_twofactor_usersettings")
public class OpenScienceFrameworkTimeBasedOneTimePassword {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private OpenScienceFrameworkUser owner;

    @Column(name = "totp_secret", nullable = false)
    private String totpSecret;

    @Column(name = "is_confirmed", nullable = false)
    private Boolean confirmed;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    /** Default Constructor. */
    public OpenScienceFrameworkTimeBasedOneTimePassword() {}

    public Integer getId() {
        return id;
    }

    public String getTotpSecret() {
        return totpSecret;
    }

    public Boolean isConfirmed() {
        return confirmed;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    /**
     * Returns the TOTP secret in encoded as Base32.
     *
     * @return the encoded secret
     */
    public String getTotpSecretBase32() {
        final byte[] bytes = DatatypeConverter.parseHexBinary(totpSecret);
        return new Base32().encodeAsString(bytes);
    }

    @Override
    public String toString() {
        return String.format("TimeBasedOneTimePassword [id=%s, owner=%s]", id, owner.getUsername());
    }
}
