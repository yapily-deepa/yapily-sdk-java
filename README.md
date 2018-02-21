In order to use the maven repo include the below in your repositories project

    <repositories>
        <repository>
            <id>yapily-repo</id>
            <name>yapily-repo</name>
            <url>http://maven.yapily.com/</url>
        </repository>
    </repositories>
    
Then specify the dependencies you need

    <dependencies>
        <dependency>
            <groupId>com.yapily</groupId>
            <artifactId>sdk</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>

This repo includes a Java SDK to connect to Yapily as well as examples of how to use this SDK
to connect to the available banking APIs and get some customer data. 
