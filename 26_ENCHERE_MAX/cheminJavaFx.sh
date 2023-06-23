#!/bin/bash

lancer() {
    java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml -jar 26_ENCHERE_MAX.jar
}

# Appeler la fonction pour exécuter la commande Java
lancer