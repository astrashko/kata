# Kata13: Counting Code Lines

A utility that counts the number of lines of actual code in a Java source file

## Required tools:

* JDK (8 or higher)
* Maven (version 4), main build tool

## Build:

mvn clean install

## Run:

cd kata</br>
mvn exec:java -Dexec.mainClass="CodeLines" -Dexec.args="Hello.java"</br>
mvn exec:java -Dexec.mainClass="CodeLines" -Dexec.args="root"