In order to use the maven repo include it in your repositories project

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
            <artifactId>api-client</artifactId>
            <version>0.1</version>
        </dependency>
    </dependencies>