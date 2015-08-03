[![Build Status](https://travis-ci.org/smartcanvas/java-sdk.png?branch=master)](https://travis-ci.org/smartcanvas/java-sdk)
[![Coverage Status](https://coveralls.io/repos/smartcanvas/java-sdk/badge.png?branch=master)](https://coveralls.io/r/smartcanvas/java-sdk?branch=master)
[ ![Download](https://api.bintray.com/packages/smartcanvas/maven/smartcanvas-java-sdk/images/download.svg) ](https://bintray.com/smartcanvas/maven/smartcanvas-java-sdk/_latestVersion)

smartcanvas-java-sdk
=====================

Java client library for Smart Canvas APIs


### Setting up build tools 


#### Gradle

```groovy

repositories {
	jcenter()
    maven {
        url  "http://dl.bintray.com/smartcanvas/maven" 
    }
}

dependencies {
    compile 'com.smartcanvas:smartcanvas-java-sdk:0.1.4'
}
```

#### Maven

```xml
<dependency>
  <groupId>com.smartcanvas</groupId>
  <artifactId>smartcanvas-java-sdk</artifactId>
  <version>0.1.4</version>
</dependency>
```