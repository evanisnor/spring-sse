#!/usr/bin/env bash
mvn clean package install
mvn -pl booter spring-boot:run
