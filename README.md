spring-wicket-bootstrap
==========================

This project demonstrates various Spring Framework features with a Wicket-Bootstrap front-end. Visit http://wicket.daveburkevt.com to see the code in action. The site describes various Spring and Wicket features with links to blog posts at http://nixmash.com which provide additional details. 

![Image of Spring Wicket-Bootstrap Site Home Page](http://nixmash.com/x/pics/github/spricket-home.png)

##Implemented##

*Implementations listed below by version are demonstrated at http://wicket.daveburkevt.com with additional details at http://nixmash.com. They will be found in their corresponding branch, v0.0.1, v0.0.2, etc. The Master branch of this repo contains ongoing development and may not contain features in prior versions.*

##v0.0.1##

- Gradle Build file demonstrating creating War and exploded War
- Populating application version and Wicket Configuration Type properties in Gradle
- Non-web.xml configuration with ServletContextInitializer
- Populating Java data object from external resource file
- Populating Wicket component with @SpringBean annotation
- Using SpringConfiguration class to create Spring context without XML

##Running / Deployment##

The build.gradle will do the work for you to create a WAR in **/build/libs** and an exploded WAR in **/build/exploded** by simply typing

```
$ gradle war
```

or *$ ./gradlew war* if using the wrapper. You can then point Tomcat at the **/build/exploded** directory. See my blog post [IDE Build-free Web Apps With Gradle](http://nixmash.com/java/ide-build-free-web-apps-with-gradle/) at **NixMash.com.**

The demo of populating Java Class properties from an External Resource requires a properties file specified in *config.properties:spring.properties.file.* A sample *external.properties* file is located in the /install folder.

##Referenced Repositories##

Repositories with code used in this project or referenced in accompanying blog posts include:

* Michael Laccetti's [wicket-spring-boilerplate](https://github.com/limone/wicket-spring-boilerplate(
* Stefan Kloe's [spring-wicket-bootstrap](https://github.com/Pentadrago/spring-wicket-bootstrap)
* And of course, Michael Haitz's [wicket-bootstrap](https://github.com/l0rdn1kk0n/wicket-bootstrap)


