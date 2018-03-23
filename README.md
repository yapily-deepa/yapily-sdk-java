#Yapily Java SDK

This SDK serves both as a module that can be included in any project connecting to the Yapily API and retrieving a normalised model for all
connected banks, as well as an example of how to connect to the API should you choose integrate your own solution.

##Requirements

To connect to the Yapily API, you will need to register your application at [https://dashboard.yapily.com]()

You will need your application credentials to authorise all requests to the API.

##Installation

The SDK is currently available in the Yapily Maven repository and can be included in your project 
by adding it to your dependencies

####Maven

Repository:

```xml
<repositories>
   <!-- other repos-->
   <repository>
       <id>yapily-repo</id>
       <name>yapily-repo</name>
       <url>http://maven.yapily.com/</url>
   </repository>
</repositories>
```

Dependency:

```xml
<dependencies>
   <!-- other dependencies -->
   <dependency>
       <groupId>yapily</groupId>
       <artifactId>sdk</artifactId>
       <version>0.0.1-SNAPSHOT</version>
   </dependency>
</dependencies>
```

####Gradle

Repository:

```groovy
repositories {
    // other repos
    maven {
        url "http://maven.yapily.com/"
    }
}
```

Dependency:

```groovy
compile group: 'yapily', name: 'sdk', version: '0.0.1-SNAPSHOT'
```

####Download JAR

The JAR can be downloaded from a tagged release, or this project can be cloned/downloaded and packaged into a library JAR to be included
in your project with `mvn install`.

##Usage

Sample usage of the SDK can be seen in `example`:

- Retrieve a list of available banks to connect to
- Creating users and retrieving users for an application registered in the Yapily Dashboard
- Retrieving the authorisation URL for a bank and directing the user to fill login credentials
- Returning user account details
- Returning user transaction details

##Further information

For more information on how to get connected, visit the
[Yapily developer resources](https://github.com/yapily/developer-resources) repo.
