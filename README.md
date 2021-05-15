# Travail Encadré d'études, de Recherches et de Developpement

<p align="center">
  <a href="https://example.com/">
    <img src="https://i.imgur.com/cEn7FNa.png" alt="Logo" width=72 height=128>
  </a>

<h3 align="center">RogueLike MADY</h3>

![Accueil](https://i.imgur.com/hTDKKex.png)

![Game](https://i.imgur.com/wMwNcCK.png)

## <u>Développeurs :</u>

- NOM : COUSSON Antoine - ID Github : MonsieurCo
- NOM : BRAULT Yann - ID Github : Rafhiky
- NOM : SCHMIED Margaux - ID Github : margauxschmied
- NOM : BATISSE Dylann - ID Github : takitsu21

## <u>Introduction :</u>

Le but de ce projet est de développer en groupe un jeu de type <b>RogueLike</b>, en s'appuyant sur des méthodes
utilisées en entreprise telle que la methode agile, pour developper le jeu nous nous somme aidé
de <i>[MAVEN](https://maven.apache.org/).</i>

Un Rogue Like est un sous-genre de jeu vidéo de rôle dans lequel le joueur explore un donjon infesté de monstres qu’il
doit combattre pour gagner de l’expérience et des trésors. Le genre se caractérise notamment par la génération
procédurale de ses niveaux, son système de mort permanente, son gameplay au tour par tour et la représentation des
éléments qui le composent par des symboles ASCII sur une carte constituée de tuiles.

## <u>Contrôle du Joueur :</u>

- Déplacement:
    - `z` monter
    - `s` descendre
    - `q` gauche
    - `d` droite
    - `⇧ + déplacement` dash / roulade dans une direction
- Attaque:
    - `a` attaque de zone autour du joueur
    - `e` attaque un unique monstre
    - `t` attaque un unique monstre à distance
        - `←` `→` sélectionner les différents monstres autour de soi
        - `⏎` pour valider la sélection et attaquer le monstre

- Inventaire: `i` (navigation `z s`, équiper l'objet `⏎`, jeter l'objet `⌫`)
  ![Inventaire](https://i.imgur.com/6JurPRn.png)  

- Quitter n'importe quel menu d'interaction:
  - `ESC` (touche escape)
  - touche initiale d'interaction exemple : 
    si on appuie sur i pour ouvrir l'inventaire on pourra le fermer avec `i` ou `esc`

- Consommer un elixir:
  - `&` elixir de vie
  - `é` elixir de mana



- interaction: `x` (ouverture d'un coffre, achat d'un coffre dans le shop, achat de potions)

- Vente
    - intéragir avec le marchand `!` dans le shop afin de vendre des objets.
    - `z`, `s` pour sélectioner un objet.
    - `⏎` pour confirmer la vente.


![Shop](https://i.imgur.com/5kdfdIf.png)


## <u>Representation du jeu :</u>

- Monstre:
    - `o` Orc
    - `g` Goblin
    - `T` Troll
    - `w` Sorcière
    - `d` Druide noir
    - `B` Boss
- Coffre au trésor <span style="color:magenta">C</span>, vous offre un item aléatoire a équiper, un coffre dans un shop est payant et contient un item prédéfini.
- Potion ou poison de mana <span style="color:orange">M</span>, vous octroie un malus ou bonus de MP.
- Potion ou poison de vie <span style="color:orange">V</span>, vous octroie un malus ou bonus de HP.
- Portail <span style="color:cyan">§</span>, vous téléporte à la prochaine map.
    - Quand un boss est présent (toutes les 3 salles), le portail apparaît une fois le
    boss tué.
- Shop <span style="color:magenta">$</span>, vous téléporte à la map du shop.
- PNJ <span style="color:yellow">!</span>, Henri, le marchand.
- Elixirs de Vie (présent dans le shop) <span style="background-color:red;color:red">C</span>
- Elixirs de mana (présent dans le shop) <span style="background-color:blue;color:blue;">C</span>


## <u>Conseils de jeu :</u>

⚠️ Quand votre partie commence il vous faudra rester focus sur la fenêtre swing qui s'ouvrira. ⚠️

Pour gagner de l'expérience vous devrez tuer des monstres. Cela vous permettra de monter en niveau et d'augmenter vos statistiques. \
Ouvrir des coffres vous permet d'obtenir des équipements que vous pourrez visualiser dans l'inventaire et vendre au marchand. \
Marcher sur des potions vous donnera l'opportunité de gagner des bonus ou des malus de vie ou de mana. \
Pour changer de salle déplacer vous sur les portes et vous serez téléporté dans la salle relié.\
Au cours de votre partie vous croiserez la porte menant au shop où vous pourrez vendre et acheter du materiel (⚠️ si vous sortez du shop vous ne pouvez plus y retourner). \
Le changement de monde s'effectue lorsque vous marché sur le portail (⚠️ quand vous passez un portail vous ne pouvez pas revenir en arrière).

Si au cours de la partie vous avez besoin d'un rappel des déplacements n'hésité pas utilisé le help du menue pause ou de la fenêtre swing.

## <u>Bugs connus</u>

- [X] Problème d'interactions avec le jeu de façon inattendu (on ne peut plus bouger).
- [ ] Les chemins peuvent se croiser entre eux mais sont tout de même utilisables.
- [X] Problème de génération de chemins / portes dans les coins des salles.
- [ ] Génération de la map plus longue de temps en temps car nous avons augmenté sa taille et le nombre de salles présentes dans celle-ci.

## <u>Pour run l'artefact : </u>

Si vous êtes sur Windows avec wsl il vous faudra un serveur X graphique (ex: Xming) pour lancer le programme.

CMD SHELL

```shell
java -jar RogueLikeMADY-1.0-SNAPSHOT.jar
```

<img src="https://i.imgur.com/d9cDliK.png" alt="Logo" width=48 height=48> <img src="https://i.imgur.com/fliRMaY.png" alt="Logo" width=48 height=48> <img src="https://i.imgur.com/zaCLOhf.png" alt="Logo" width=48 height=48>



