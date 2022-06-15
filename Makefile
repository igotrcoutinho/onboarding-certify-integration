SHELL=/bin/bash

# Script Args
ENVS ?= default

# Configuration
MICRONAUT_VERSION := 3.0.2
APPLICATION_NAME := $(shell cat pom.xml | grep -oPm1 "(?<=<artifactId>)[^<]+")
MICRONAUT_ENVIRONMENTS := $(ENVS)
JAVA_ARGS := -Xmx384m -Xms128m

all:
	@-mvn clean compile exec:exec

build:
	@-mvn -Djar.finalName=$(APPLICATION_NAME) clean install

run-build:
	@-java $(JAVA_ARGS) -jar target/$(APPLICATION_NAME).jar

clean:
	@-mvn clean

install-micronaut:
	@-\
	curl -s https://get.sdkman.io | bash &&\
	source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&\
	sdk update &&\
	sdk install micronaut $(MICRONAUT_VERSION) &&\
	echo -e "Instalação finalizada com sucesso.\nAbra um novo terminal ou digite:\n    $$ source ~/.bashrc"
	@-sed -i '/MICRONAUT_ENVIRONMENTS/d' ~/.bashrc
	@-echo "export MICRONAUT_ENVIRONMENTS=\"$(MICRONAUT_ENVIRONMENTS)\"" >> ~/.bashrc
	@-source ~/.bashrc
