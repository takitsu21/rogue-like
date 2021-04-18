# Travail Encadré d'études, de Recherches et de Developpement 

<p align="center">
  <a href="https://example.com/">
    <img src="https://i.imgur.com/cEn7FNa.png" alt="Logo" width=72 height=128>
  </a>

<h3 align="center">RogueLike MADY</h3>

![Game](https://i.imgur.com/FDKUPoZ.png)

## <u>Développeurs :</u>

- NOM : COUSSON Antoine - ID Github : MonsieurCo
- NOM : BRAULT Yann - ID Github : Rafhiky
- NOM : SCHMIED Margaux - ID Github : margauxschmied
- NOM : BATISSE Dylann - ID Github : takitsu21

## <u>Introduction :</u>

Le but de ce projet est de développer en groupe un jeu de type <b>RogueLike</b>, en s'appuyant sur des méthodes
utilisées en entreprise telle que la methode agile, pour developper le jeu nous nous somme aidé
de <i>[MAVEN](https://maven.apache.org/). </i>

Un Rogue Like est un sous-genre de jeu vidéo de rôle dans lequel le joueur explore un donjon infesté de monstres
qu’il doit combattre pour gagner de l’expérience et des trésors. Le genre se caractérise notamment par la génération
procédurale de ses niveaux, son système de mort permanente, son gameplay au tour par tour et la représentation des
éléments qui le composent par des symboles ASCII sur une carte constituée de tuiles.

## <u>Contrôle du Joueur :</u>

- Déplacement:
    - `z` monter
    - `s` descendre
    - `q` gauche
    - `d` droite
- Attaque:
    - attaque de zone autour du joueur `a`
    - attaque un unique monstre `e`
- Inventaire: `i` (navigation `z s`, équiper l'objet `enter`, jeter l'objet `backspace`)
- interaction: `x` (ouverture d'un coffre)

![Inventaire](https://i.imgur.com/vUfnbbn.png)

## <u>Representation du jeu :</u>

- Monstre:
    - Orc `o`
    - Goblin `g`
- Coffre au trésor <span style="color:magenta">C</span>, vous offre un item aléatoire a équiper.
- Potion ou poison de force <span style="color:orange">F</span>, vous octroie un malus ou bonus d'ATK.
- Potion ou poison de vie <span style="color:orange">V</span>, vous octroie un malus ou bonus de d'HP.
- Portail <span style="color:cyan">§</span>, vous téléporte à la prochaine map.
- Coming soon...
    - Nouveaux :
      - Monstres
      - Items
      - Effets visuels
      - Stats des équipements à ajouter au joueur (ATK, HP, etc...)
    - Official Soundtrack

## <u>Conseil de jeu :</u>

Pour gagner de l'expérience vous devrez tuer des monstres. Cela vous permettra de monter en niveau et d'augmenter vos statistiques.
Ouvrir des coffres vous permet d'obtenir des équipements que vous pourrez visualiser dans l'inventaire. Marcher sur
des potions vous donnera l'opportunité de gagner des bonus ou des malus de statistiques. Pour changer de salle déplacer vous sur les
portes et vous serez téléporté dans la salle relié. Le changement de monde s'effectue lorsque vous marché sur le
portail.

Il vous faudra cliquer sur la fenêtre swing qui s'ouvrira pour pouvoir utiliser les touches.

## <u>Bugs connus</u>

- Problème de génération de chemins / portes dans les coins des salles.
- Problème d'interactions avec le jeu de façon inattendu (on ne peut plus bouger).

## <u>Pour run l'artefact : </u>

Si vous êtes sur Windows il vous faudra un serveur X graphique (Xming) pour lancer le programme.

CMD SHELL
```shell
java -jar RogueLikeMADY-1.0-SNAPSHOT.jar
```
<img src="https://i.imgur.com/d9cDliK.png" alt="Logo" width=48 height=48> <img src="https://i.imgur.com/fliRMaY.png" alt="Logo" width=48 height=48> <img src="https://i.imgur.com/zaCLOhf.png" alt="Logo" width=48 height=48>



