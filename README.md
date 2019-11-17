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
  implementation 'com.github.stnwtr:indy4j:%VERSION%'
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
  <version>%VERSION%</version>
</dependency>
```

## Examples

### Automatically enrol for random subject and teacher
```java
Indy indy = new Indy(Credentials.from("username", "password"));
indy.login();

indy.getNextEventContexts(1).stream()
    .map(EventContext::loadEvent)
    .filter(event -> event instanceof FutureEvent)
    .map(event -> (FutureEvent) event)
    .forEach(event -> {
      for (int hour : event.getEventContext().getHours()) {
        String teacherId = event.getTeachers(hour).stream()
            .map(Teacher::getId)
            .sorted()
            .findFirst()
            .orElseThrow(NoSuchElementException::new);

        String subject = event.getSubjects().stream()
            .sorted()
            .findFirst()
            .orElseThrow(NoSuchElementException::new);

        String activity = "Differentialgleichung, Buch S. 120, Übungen";

        event.enrol(Entry.normalSchoolDay(hour, teacherId, subject, activity));
      }
    });

indy.logout();
```

### Set past entries to absent
```java
Indy indy = new Indy(Credentials.from("username", "password"));
indy.login();

indy.getAllEventContexts().stream()                     // yyyy-mm-dd
    .filter(eventContext -> eventContext.getDate().equals("2019-11-13"))
    .map(EventContext::loadEvent)
    .filter(event -> event instanceof PastEvent)
    .map(event -> (PastEvent) event)
    .findFirst()
    .ifPresent(event -> {
      for (int hour : event.getEventContext().getHours()) {
        event.changeAbsent(hour);
      }
    });

indy.logout();
```
