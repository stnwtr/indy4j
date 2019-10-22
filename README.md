# indy4j
IndY "wrapper" written in Java.

## Clone and set up
Clone the repository by typing `git clone https://github.com/stnwtr/indy4j.git` in the terminal.

Create a `credentials.json` file in `./indy4j/src/test/resources/` containing
```json
{
  "username": "indy username",
  "password": "indy password"
}
```

## Installation
No release yet.

### Gradle
```groovy
allprojects {
  repositories {
    maven {
      url 'https://jitpack.io'
    }
  }
}

dependencies {
  implementation: 'com.github.stnwtr:indy4j:version'
}
```

### Maven
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.stnwtr</groupId>
  <artifactId>indy4j</artifactId>
  <version>version</version>
</dependency>
```

## Examples
Not yet.