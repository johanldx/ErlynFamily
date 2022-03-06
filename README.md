# Erlyn Family - Plugin Minecraft

## Le plugin Minecraft 1.12.2 pour les survies moddés entre amis

*Erlyn Family* est un plugin Minecraft développé via la version 1.12.2 du jeu afin d'ajouter les commandes essentiels à un petit serveur Minecraft sans être trop loud en ressources et en stockage en étant optimisé pour le moddé ! De plus, le fichier de configuration ainsi que les permissions du plugins sont très basique pour permettre à tout le monde de configurer le plugin.
*Erlyn Family* est l'alternative parfaite à *Essentials* pour votre serveur Moddé !

Je l'ai développé seul pour un serveur privé entre amis car je ne trouvais aucun plugin simple qui comportais des commandes axées pour les survies moddés.

## Les Fonctionnalités

### Messages de connexion/déconnexion
Message personnalisé dans la barre d'action lors de la connexion et la déconnexion d'un joueur sur le serveur.

## Delay
Possibilité de définir le delay de téléportation.

### Message dans le chat global
Possibilité d'envoyer des message en couleur dans le chat global. De plus, il a été épuré afin d'être plus élégant !

### Respawn
Possibilité de réapparaire au spawn automatiquement lors de la réapparition.

### Mort
Message personnalisés et configurable lors de la mort d'un joueur. Possibilité de définir le pourcentage de drop de la tête d'un joueur tué par un autre.

## Les Commandes

### Spawn
La première commande à utiliser ! La commande **/setspawn** vous permet de définir un spawn différent pour chaque monde présent sur le serveur. La commande **/spawn** vous permet de vous téléporter au spawn du monde dans lesquelle vous vous trouvez.

### Annonce
La commande **/annonce [message]** vous permet de faire une annonce pour tout les joueurs présents sur le serveur.
  
### Wiki
La commande **/wiki** vous permet d'avoir accès à la liste de tout les wikis des mods utilisés sur le serveur. Les noms des mods ainsi que les liens des wikis peuvent être mis à jour dans le fichier de configuration.
  
### Message privé
La commande **/msg [player] [message]** à été repensé afin d'être plus estétique et qu'il soit plus rapide de répondre à un message que sur Minecraft vanilla.

### Stats
La commande **/stats [player]** permet d'obtenir des stats sur un joueur connecté sur le serveur comme son temps de jeu, nombre de mobs tué etc...

### 🌟 Kiss 🌟
La commande **/kiss [player]** vous permet de donner de votre vie à un autre joueur connecté. Il vous restera au minimum un demi-coeur de vie. Cet acte héroique est accompagné d'un message pour tout les joueurs <3

## Installation

1. Installer la dépendance [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/).
2. Installer le plugin [Erlyn Family](https://github.com/ldxdev/erlyn-family-plugin-mc/releases/tag/jar).
3. Configurer le fichier config.yml

```yml
# @@@@@@@@@  &@@@@@@@@#   (@@@.     @@@@    %@@@& &@@@@    /@@@,     *@@@@@@@@@      @@@@@      @@@@@      #@@@@, .@@@#  @@@&     @@@@*   .@@@@
# @@@@.      &@@@***@@@@/ (@@@.      @@@@  &@@@,  &@@@@@*  /@@@,     *@@@/          @@@@@@&     @@@@@@    (@@@@@, .@@@(  @@@&      #@@@# ,@@@&
# @@@@@@@@%  &@@@   %@@@* (@@@.       %@@@@@@@    &@@@@@@@ /@@@,     *@@@@@@@#     @@@% @@@%    @@@@@@@  /@@@@@@, .@@@(  @@@&       .@@@@@@@*
# @@@@%%%%/  &@@@@@@@@,   (@@@.        ,@@@@@     &@@@ .@@@@@@@,     *@@@&&&&(    @@@@  .@@@(   @@@@,@@@*@@&/@@@, .@@@(  @@@&         @@@@@
# @@@@.....  &@@@ ,@@@@   (@@@,         &@@@      &@@@   %@@@@@,     *@@@*       @@@@@@@@@@@@*  @@@@ (@@@@@ /@@@, .@@@(  @@@&         .@@@(
# @@@@@@@@@  &@@@   @@@@. (@@@@@@@%     &@@@      &@@@     @@@@,     *@@@*      @@@@      &@@@. @@@@  %@@@  /@@@, .@@@(  @@@@@@@@     .@@@(

# Version BETA 1.1.0, Développé par !ldx

# ==============================================================
# ==============================================================

# ==============================================================
# Configuration global
# ==============================================================

# Localisation lors du respawn du joueur
# => "spawn" (spawn) / "" (désactivé)
location_respawn: "spawn"

# Probalilité du drop de la tête d'un joueur tué en PVP
# => Pourcentage (nombre entier compris entre 0 et 100)
probability_drop_death: 10

# Delay par défaut lors des téléportations
teleportation_delay: 5

# ==============================================================
# Messages des événements
# ==============================================================

# Message de bienvenue lors de la première connexion du joueur
# => Message (nombre de lignes illimité)
welcome:
  - "&cBienvenue dans la Erlyn Family !"
  - "&cNous sommes heureux de te compter parmis nous."
  - "&cBonne aventure à toi <3"

# Message lors de la connexion d'un joueur sur le serveur
# => {PLAYER} (pseudo du joueur)
join_message: "&5{PLAYER} &7a rejoint la partie"

# Message lors de la déconnexion d'un joueur sur le serveur
# => {PLAYER} (pseudo du joueur)
quit_message: "&5{PLAYER} &7a quitté la partie"

# Paterne des messages privés
# => {SENDER} (envoyeur), {RECEIVER} (récepteur), {MESSAGE} (message)
msg_patern_message: "&6{SENDER} &7-> &6{RECEIVER} &7{MESSAGE}"

# Paterne des message dans le chat
# {PLAYER} (joueur), {MESSAGE} (message)
chat_patern_message: "&6{PLAYER} &7{MESSAGE}"

# Message lors d'un kill
# {PLAYER} (joueur), {KILLED} (tué), {WEAPON} (arme)
kill_message: "&c{PLAYER} &7a tué &c{KILLED} &7avec un(e) &c{WEAPON}"

# Messages lor sde la mort d'un joueur
# {PLAYER} (joueur)
death_messages:
  - "&4Oups... {PLAYER} est mort !"
  - "&4Une minute de silence pour {PLAYER}"
  - "&4Toute mes condoléances à la famille de {PLAYER}"
  - "&4{PLAYER} le but n'est pas mourir le premier !"
  - "&4Encore un {PLAYER} parti trop tôt..."
  - "&4{PLAYER} s'est fait dégommer !"
  - "&4{PLAYER} n'est pas très doué mdr!"


# ==============================================================
# Messages des commandes
# ==============================================================

# Paterne du message d'annonce global
# => {MESSAGE} (message de l'annonce)
annonce_patern_message: "&4/!\\ &c{MESSAGE}"

# Messages lors de la définition du spawn
teleportation_spawn_message_set: "&7Point de téléportation vers le &cspawn &7définie avec succès !"

# Message avant la téléportation au spawn
teleportation_spawn_message_teleport: "&7Téléportation vers le &cspawn&7 en cours..."

# Message lors de l'annulation de la téléportation
teleportation_global_message_playermove: "&7Oups, tu as &cbougé&7, téléportation annulé !"

# Message lors du bon déroulé de la téléportation
teleportation_global_message_done: "&7Téléportation &créussite &7!"

# Message lorsque le joueur execute la commande /dm sans arguments
msg_message_cmd: "&7Tu dois spécifier &cun joueur &7et &cun message &7!"

# Message lorsque le joueur execute la commande /r sans arguments
reply_message_cmd: "&7Tu dois précifier &cun message &7!"

# Message lors de la régénération d'un joueur
# => {SENDER} (envoyeur), {RECEIVER} (récepteur)
kiss_message: "&d{SENDER} &7envoie un bisous à &d{RECEIVER} &7<3"

# Noms des mods du serveur
# en relation avec la liste des liens des wikis des mods
wikis_mods:
  - "AIOT Botania"
  - "Aroma1997's Dimensional World"
  - "Blood Magic"
  - "Dark Utilites"
  - "Environemental Tech"
  - "Ex Compressum"
  - "Extra Planets"
  - "Flux Networks"
  - "Food Expansion"
  - "MRCrayfish's Furniture Mod"
  - "Hunting Dimension"
  - "Immersive Engineering"
  - "Immersive Petroleum"
  - "Tesla Core lib"
  - "Industrial Foregoing"
  - "Mystical Agriculture"
  - "Mystical Agradditions"
  - "HaverstCraft"
  - "Inventory Pets"

# Liens des wikis des mods du serveur
# en relation avec la liste des noms des mods du serveur
wikis_links:
  - "https://ftb.fandom.com/wiki/AIOT_Botania"
  - "https://ftb.fandom.com/wiki/Aroma1997%27s_Dimensional_World"
  - "https://ftb.fandom.com/wiki/Blood_Magic/fr"
  - "https://ftb.fandom.com/wiki/Dark_Utilities"
  - "https://ftb.fandom.com/wiki/Environmental_Tech"
  - "https://ftb.fandom.com/wiki/Ex_Compressum"
  - "https://ftbwiki.org/Extra_Planets"
  - "https://ftb.fandom.com/wiki/Flux_Networks"
  - "https://www.minecraft-france.fr/mod-food-expansion/"
  - "https://mrcrayfishs-furniture-mod.fandom.com/wiki/MrCrayfish%27s_Furniture_Mod_Wiki"
  - "https://ftb.fandom.com/wiki/Hunting_Dimension_(dimension)"
  - "https://ftb.fandom.com/wiki/Immersive_Engineering"
  - "https://ftb.fandom.com/wiki/Immersive_Petroleum"
  - "https://ftb.fandom.com/wiki/Tesla_Core_Lib"
  - "https://ftb.fandom.com/wiki/Industrial_Foregoing"
  - "https://ftb.fandom.com/wiki/Mystical_Agriculture"
  - "https://ftb.fandom.com/wiki/Mystical_Agradditions#:~:text=Mystical%20Agradditions%20is%20an%20addon,with%20their%20own%20unique%20modifiers."
  - "https://harvestcraftmod.fandom.com/wiki/HarvestCraft_Wiki"
  - "https://inventory-pets.fandom.com/wiki/Inventory_Pets_Wiki"

# ==============================================================
# Messages d'erreurs
# ==============================================================

error_message_sender: "&7Oups, tu n'es pas &cun joueur &7!"
error_message_unexpected: "&7Oups, un &cerreur inattendue &7est arrivée !"
error_message_spawn_notset: "&7Auncun &cspawn &7n'est encore définie !"
error_message_broadcast_length: "&7Tu dois spécifier &cun message &7!"
error_message_playervalid: "&7Tu dois spécifier &cun joueur connecté &7!"
error_message_playerisyou: "&7Tu dois spécifier &cun autre joueur connecté &7!"
error_message_reply_notfound: "&7Personne n'attend de &créponse &7de ta part !"

# ==============================================================
# ==============================================================

# ,@@@@@@@@@.       /@@@@@      /@@@@@@@@@,         @@@@    @@@@      &@@@@@@@@&     ,@@@@/    @@@@(
# .@@@@##@@@@@     *@@@@@@&     /@@@@@@@@@@@*       @@@@    @@@@      %@@@@@@@@@@@@    #@@@@ #@@@@
# .@@@@   &@@@/   .@@@&,@@@%    /@@@#   %@@@(       @@@@    @@@@      %@@@,    *@@@@     @@@@@@@,
# .@@@@@@@@@@,   .@@@@  (@@@(   /@@@@@@@@@@.        &@@@    @@@@      %@@@,     @@@@     *@@@@@#
# .@@@&          @@@@@@@@@@@@*  /@@@# @@@@.                 @@@@      %@@@,   #@@@@,    @@@@%@@@@,
# .@@@@         @@@@      @@@@, /@@@#  @@@@%       *@@@@,   @@@@@@@@# %@@@@@@@@@@(    #@@@@   &@@@@
```

## Développement

*Erlyn Family* est développé sous **Spigot 1.12.2-R0.1-SNAPSHOT** et **PlaceholderAPI 2.10.9**. Le JDK utilisé pour le développement est en version **1.8.0_302**.

Merci d'avoir lu <3
