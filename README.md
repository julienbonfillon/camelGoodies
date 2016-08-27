# camelGoodies
Some Camel use examples ...

the components/mail has been generated using the maven archetype generator based on camel Archetype implementation org.apache.camel.archetypes.camel-archetype-component

[Official Documentation](http://camel.apache.org/creating-a-new-camel-component.html)

mvn archetype:generate                   \
  -DarchetypeGroupId=org.apache.camel.archetypes  \
  -DarchetypeArtifactId=camel-archetype-component \
  -DarchetypeVersion=2.17.0  \
  -DgroupId=org.pushitio.eip.camel.components                 \
  -DartifactId=mailer     \
  -Dversion=1.0-SNAPHSOT

  the projects/standalone project has been generated using using the maven archetype generator based on camel Archetype implementation org.apache.camel.archetypes.camel-archetype-spring

[Official Documentation](http://camel.apache.org/creating-a-new-spring-based-camel-route.html)


  mvn archetype:generate                   \
  -DarchetypeGroupId=org.apache.camel.archetypes  \
  -DarchetypeArtifactId=camel-archetype-spring   \
  -DarchetypeVersion=2.17.0             \
  -DgroupId=org.pushitio.eip.camel.projects.default                  \
  -DartifactId=standalone   \
  -Dversion=1.0-SNAPHSOT

  just start the project using the command #mvn camel:run
