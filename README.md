# Yapily Java SDK
[![GitHub version](https://d25lcipzij17d.cloudfront.net/badge.svg?id=gh&type=6&v=0.0.1-SNAPSHOT&x2=0)](http://badge.fury.io/gh/boennemann%2Fbadges)

This SDK can be used as a module or an example of how to connect
to any financial institution integrated by Yapily.

## Requirements

To connect to the Yapily API, you will need to register your 
application at [https://dashboard.yapily.com]().

These application credentials can then be used to authorise all
your API requests.

## Installation

The SDK is currently available in the Yapily maven repository and 
can be included in your project 
by adding it to your dependencies

#### Maven

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
       <artifactId>yapily-sdk-java</artifactId>
       <version>0.1.2-SNAPSHOT</version>
   </dependency>
</dependencies>
```

#### Gradle

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
compile group: 'yapily', name: 'yapily-sdk-java', version: '0.1.2-SNAPSHOT'
```

#### Download JAR

The JAR can also be downloaded from a tagged release, 
or this project can be cloned/downloaded and packaged into a 
library JAR to be included in your project.

## Usage

Sample usage of the SDK can be seen in the `examples` folder.

- Retrieve a list of available financial institutions to connect to

```java
Institutions institutionsApi = new Institutions();
List<Institution> institutionList = institutionsApi.listInstitutions();
```

- Creating users and retrieving users for your application registered in the Yapily Dashboard
```java
Users usersApi = new Users();
usersApi.createUser("Bojack");
List<ApplicationUser> users = usersApi.listUsers();
```

- Receiving an authorisation URL your users to log into their institution

```java
Auth auth = new Auth();
URI directUrl = auth.authDirectURL(applicationId, userUuid, institutionId, YOUR_CALLBACK_URL, "account");
```
 
- Returning user account details

```java
Accounts accountsApi = new Accounts();
List<Account> accounts = accountsApi.listAccounts(userUuid, institutionId);
```

- Returning user transaction details

```java
Transactions transactionsApi = new Transactions();
List<Transaction> transactions = transactionsApi.listTransactions(userUuid, accountId, institutionId);
```

- Returning user identity details
```java
Identities identitiesApi = new Identities();
Identity identity = identitiesApi.getIdentity(userUuid, institutionId);
```

## Further information

For more information on how to get connected, visit the
[Yapily developer resources](https://github.com/yapily/developer-resources) repo.
