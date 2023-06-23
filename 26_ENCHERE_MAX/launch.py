import os
import subprocess

def update_login_bd():
    chemin_absolu_dossier = os.path.dirname(os.path.abspath(__file__))
    file_path = os.path.join(chemin_absolu_dossier, "loginBD.txt")
    
    print("Appuyer sur Entrée pour conserver la valeur par défaut.")

    # Charger les valeurs par défaut
    with open(file_path, "r") as file:
        lines = file.readlines()
    nom_serveur_default = lines[9].strip().rstrip(',')
    nom_bd_default = lines[10].strip().rstrip(',')
    login_default = lines[11].strip().rstrip(',')
    password_default = lines[12].strip().rstrip(',')


    # Demander les nouvelles données à l'utilisateur
    nom_serveur = input(f"Entrez le nom du serveur (défault : {nom_serveur_default}): ") or nom_serveur_default
    nom_bd = input(f"Entrez le nom de la base de données (défault :{nom_bd_default}): ") or nom_bd_default
    login = input(f"Entrez le login (défault :{login_default}): ") or login_default
    password = input(f"Entrez le mot de passe (défault :{password_default}): ") or password_default

    # Mettre à jour le fichier texte avec les nouvelles données
    try:
        lines[9] = nom_serveur + ",\n"
        lines[10] = nom_bd + ",\n"
        lines[11] = login + ",\n"
        lines[12] = password + ",\n"

        with open(file_path, "w") as file:
            file.writelines(lines)

        print("Les informations de connexion ont été mises à jour avec succès.")

        # Exécuter le script Bash launch.sh
        subprocess.run(["bash", "cheminJavaFx.sh"])
        
    except FileNotFoundError:
        print("Le fichier loginBD.txt n'a pas été trouvé.")
    except Exception as e:
        print("Une erreur s'est produite :", str(e))

# Appeler la fonction pour mettre à jour les données du fichier
update_login_bd()
