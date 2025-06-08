# This Makefile serves to build multiple Maven projects
# in a single command. It assumes that each project has its own
# directory and a pom.xml file.
# Usage: make all

.PHONY: all book_service book_rental_service usermanagement

all: book_service book_rental_service usermanagement

book_service:
	cd book_Service && mvn clean package -DskipTests

book_rental_service:
	cd book_Rental_Service && mvn clean package -DskipTests

usermanagement:
	cd usermanagement && mvn clean package -DskipTests