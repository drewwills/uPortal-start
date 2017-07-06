<img alt="uPortal logo" src="docs/images/uPortal-logo.jpg" />

[![Linux Build Status](https://travis-ci.org/Jasig/uPortal-start.svg?branch=master)](https://travis-ci.org/Jasig/uPortal-start)

## About uPortal-start

uPortal-start is the mechanism through which individuals and institutions adopt [Apereo uPortal][],
the leading open source enterprise portal framework built by and for higher education institutions,
K-12 schools and research communities.  **uPortal-start is new for uPortal 5.0**

uPortal-start help you manage:

  - Your uPortal configuration
  - Your uPortal skin
  - Your uPortal data
  - And your uPortal deployments through an integrated suite of CLI tools

### uPortal 5.0 Manual

This `README` provides some high-level information on the uPortal-start component, plus some how-to
examples of performing many of the most common tasks.  The complete uPortal 5.0 Manual is hosted in
GitHUb Pages.

  - [uPortal 5.0 Manual][]

## Using uPortal-start

uPortal-start provides a build system and several CLI tools through Gradle, and it even comes with a
_Gradle Wrapper_ so you don't have to install Gradle to use it.

Invoking the Gradle Wrapper on *nix:

```
    $ ./gradlew {taskname} [{taskname}...]
```

Invoking the Gradle Wrapper on Windows:

```
    > gradlew.bat {taskname} [{taskname}...]
```

_NOTE:  For the sake of brevity, the remaining examples in this document are *nix-only._

### How To Install Tomcat





[Apereo uPortal]: https://www.apereo.org/projects/uportal
[uPortal 5.0 Manual]: https://jasig.github.io/uPortal













#### HSQL (Database) Tasks

- **hsqlStart**:  Starts the embedded HSQLDB uPortal database, which is used in _local development
environments_
- **hsqlStop**:  Stops the embedded HSQLDB uPortal database

#### Tomcat (Web Server) Tasks

- **tomcatInstall**:  Downloads the Apache Tomcat servlet container and performs the necessary
configuration steps, cleaning up the previous installation if necessary
- **tomcatStart**:  Starts the embedded Tomcat servlet container
- **tomcatStop**:  Stops the embedded Tomcat servlet container
- **tomcatClearLogs**:  Deletes all files and sub folders in the Tomcat logs directory (not
recommended for server deployments)

#### Data (Import/Export) Tasks

- **dataInit**:  Drops and recreates the schema in the embedded HSQLDB uPortal database, then loads
both the Base Data Set and the Implementation Data Set
- **dataImport**:  Imports the specified entity file(s) into the embedded HSQLDB uPortal database;
please specify exactly one of -Dfile={path}, -Ddir={path}, -Dmanifest={path}, or -Darchive={path}

#### Miscellaneous Tasks

- **portalClean**:  Deletes the deployed `/uPortal` webapp in Tomcat
