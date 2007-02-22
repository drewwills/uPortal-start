/*
 * Copyright 2006 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.portal.events;

import java.util.Date;

import org.jasig.portal.security.IPerson;
import org.springframework.context.ApplicationEvent;

/**
 * ApplicationEvent specific to the Portal.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 2.6
 *
 */
public abstract class PortalEvent extends ApplicationEvent {
    
    private static final String GUEST_PREFIX = "GUEST_USER (";
    private static final String EMPTY_STRING = "";
    private static final String NULL_PERSON = "NULL_PERSON";
    private static final String SPACE = " ";

	/** Every PortalEvent is associated with a Person. */
	private final IPerson person;
	
	/** Convenience conversion of long to Date. */
	private Date timeStampAsDate = null;

	public PortalEvent(final Object source, final IPerson person) {
		super(source);
		this.person = person;
	}

	public final IPerson getPerson() {
		return this.person;
	}

	public final Date getTimestampAsDate() {
        if (this.timeStampAsDate == null) {
            this.timeStampAsDate = new Date(getTimestamp());
        }
		return this.timeStampAsDate;
	}

	private String fixNull(String s) {
		return s == null ? EMPTY_STRING : s;
	}

	public String getDisplayName() {
		if (person == null) {
			return NULL_PERSON;
		}

		final String userName = fixNull((String) person
				.getAttribute(IPerson.USERNAME));

		if (person.isGuest()) {
			return GUEST_PREFIX + userName + ')';
		}

		final String firstName = fixNull((String) person
				.getAttribute("givenName"));
		final String lastName = fixNull((String) person.getAttribute("sn"));

		return firstName + SPACE + lastName + SPACE + '(' + userName + ')';
	}
    
    public abstract String getEvent();
}
