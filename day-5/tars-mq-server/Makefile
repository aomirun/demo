# tars api config
HOST=127.0.0.1:3000
TOKEN=0b0e10fa5d722bc1582b7a5f56cab9b6eb985612
APPNAME=example
MODULENAME=mqserver
COMMENT=developer-auto-upload

# java local debug config
CONFNAME=$(APPNAME).$(MODULENAME).config.conf
JARNAME=$(MODULENAME).jar
JVMERRORNAME=jvm_error.log
CONF_PATH=target/classes/
JAR_PATH=target/
LOG_PATH=target/$(MODULENAME)/logs/

CONF=$(CONF_PATH)$(CONFNAME)
JAR=$(JAR_PATH)$(JARNAME)
LOG=$(LOG_PATH)$(JVMERRORNAME)

.phone: test
test:
	mvn test -Dconfig=$(CONF)

.phone: run
run:
	@echo "=========================================================================================="
	@echo "CONF		=		$(CONF)"
	@echo "JAR		=		$(JAR)"
	@echo "LOG		=		$(LOG)"
	@echo "=========================================================================================="
	@mkdir -p $(CONF_PATH)
	@mkdir -p $(JAR_PATH)
	@mkdir -p $(LOG_PATH)
	java -Dconfig=$(CONF) -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Xms2000m -Xmx2000m -Xmn1000m -Xss1000k -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSParallelRemarkEnabled -XX:+CMSScavengeBeforeRemark -verbosegc -XX:+PrintGCDetails -XX:ErrorFile=$(LOG) -jar $(JAR)

.phone: build
build:
	mvn clean package -DskipTests

.phone: test-build
build-test:
	mvn clean package

.phone: upload
upload:
	curl http://$(HOST)/api/upload_and_publish?ticket=$(TOKEN) -Fsuse=@$(JAR) -Fapplication=$(APPNAME) -Fmodule_name=$(MODULENAME) -FCOMMENT=$(COMMENT)

.phone: build-upload
build-upload: build upload

.phone: test-build-upload
test-build-upload: test-build upload

.phone: help
help: 
	@echo "===============A common Makefile for java maven tars programs==============" 
	@echo "Copyright (C) 2021 aomi.run" 
	@echo "The following targets aresupport:" 
	@echo 
	@echo " test             			- mvn test" 
	@echo " run              			- tars local run" 
	@echo " build        	 			- mvn clean package -DskipTests" 
	@echo " test-build       			- mvn clean package" 
	@echo " upload        	 			- upload jar package to tars node" 
	@echo " build-upload				- build and upload" 
	@echo " test-build-upload			- test-build and upload" 
	@echo " help             			- print help information" 
	@echo 
	@echo "To make a target, do 'make [target]'" 
	@echo "=============================== Version 1.7.2 =============================" 
