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

As far as possible, **the examples in this `README` are presented in the order in which you will
want to perform them** when you set up a local uPortal dev environment.

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

#### How To See a Complete List of Gradle Tasks

You can view a comprehensive list of Gradle tasks with short descriptions of their purposes by
running the following command:

```
    $ ./gradlew tasks
```

### How To Install Tomcat

uPortal-start comes pre-integrated with the [Apache Tomcat Servlet Container][], which is a
requirement for running uPortal.  Several Tomcat configuration steps must be performed, moreover,
before the uPortal application software will function properly within it.  uPortal-start handles
these configuration tasks for you.

You can download (from [Maven Central][]), install, and properly configure an appropriate Tomcat
container by running the following command:

```
    $ ./gradlew tomcatInstall
```

You can run this command again at any time to reset your Tomcat container to the defaults defined
by uPortal-start.

### How To Start the Embedded Database

uPortal-start also comes pre-integrated with a Relational Database Management System (RDBMS) called
[HSQLDB][].  A supported RDBMS instance is another uPortal requirement.  For uPortal server
deployments, you will want to choose a different RDBMS platform:  most likely Oracle, MS SQL
Server, MySQL, or PostgreSQL.  The integrated HSQLDB instance, however, is recommended for local
dev environments of uPortal.

Use the following command to start the embedded HSQLDB instance:

```
    $ ./gradlew hsqlStart
```

**NOTE:**  the database must be running at all times when uPortal is running, and it also must be
running whenever several of the Import/Export tools are invoked.  (See examples below.)  It is
customary to leave HSQLDB running all day, or as long as you're actively working on uPortal.

You can stop the HSQLDB instance with the following command:

```
    $ ./gradlew hsqlStop
```

### How To Deploy uPortal Technology to Tomcat

When(ever) you perform the `tomcatInstall` task, the Tomcat container will be empty.  You need to
build your uPortal application software and deploy it to Tomcat before you will be able to see it
working.

You can do that with the following command:

```
    $ ./gradlew portalDeploy
```

**NOTE:**  you will need to _run this command again_ any time you make changes to anything inside
the `overlays` folder.

### How To Create and Initialize the Database Schema

uPortal-start provides several Command Line Interface (CLI) tools that allow you to manage the
portal database.  The most important of these is the `dataInit` task.

Use the following command to create the database schema and fill it with _base portal data_ and
your _implementation data set_:

```
    $ ./gradlew dataInit
```

**WARNING!**  This command also _drops the existing database schema_ (beforehand) if necessary.
You probably want to perform this task against the production portal database exactly one time (in
the beginning).



### How To xxx
### How To xxx





[Apereo uPortal]: https://www.apereo.org/projects/uportal
[uPortal 5.0 Manual]: https://jasig.github.io/uPortal
[Apache Tomcat Servlet Container]: https://tomcat.apache.org/
[Maven Central]: https://search.maven.org/
[HSQLDB]: http://hsqldb.org/











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
