spring-wicket-bootstrap
==========================

This project demonstrates various Spring Framework features with a Wicket-Bootstrap front-end. Visit http://wicket.daveburkevt.com to see the running application. The site describes various Spring and Wicket features with links to blog posts at http://nixmash.com which provide additional details. 

![Image of Spring Wicket-Bootstrap Site Home Page](http://nixmash.com/x/pics/github/spricket-home.png)

##Implemented##

*Implementations listed below by version are demonstrated at http://wicket.daveburkevt.com with additional details at http://nixmash.com. They will be found in their corresponding branch, v0.0.1, v0.0.2, etc. The Master branch of this repo contains ongoing development and may not contain features in prior versions.*

##v0.0.1##

- Gradle Build
- Non-web.xml Configuration with ServletContextInitializer
- Populating Java Data Object from External Resource
- Populating Wicket Component with @SpringBean annotation
- Using SpringConfiguration class configures Spring context without XML

##Running / Deployment##

The build.gradle will do the work for you to create a WAR and an exploded WAR in **/build/exploded** by simply typing

```$ gradle war

or *$ ./gradlew war* if using the wrapper.

##Reference Repositories##

Repositories with code which was referenced or used in this project include:

* Michael Laccetti's [wicket-spring-boilerplate](https://github.com/limone/wicket-spring-boilerplate(
* Stefan Kloe's [spring-boot-example-wicket](https://github.com/Pentadrago/spring-boot-example-wicket)
* And of course, Michael Haitz's [wicket-bootstrap](https://github.com/l0rdn1kk0n/wicket-bootstrap)


