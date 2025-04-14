::build maven repo
mvn deploy -DaltDeploymentRepository=lys-mvn-repo::default::file:"D:/git-project/maven-repo/repository/"
mvn clean deploy  直接发布到 sonatype/nexus3:latest