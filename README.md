## Travail Encadré D'études, de Recherches et de Developpement
# ROGUELIKE MADY ![logo](https://camo.githubusercontent.com/4d163bf1a7370e0a80b888da3620a8ab2bf5c3dd067d131ada16305edf8308c6/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f2d4a6176612d4646413631313f7374796c653d666c6174266c6f676f3d6a617661266c6f676f436f6c6f723d666666666666)


## <u>Développeurs :</u>

- NOM : COUSSON Antoine - ID Github : MonsieurCo
- NOM : BRAULT Yann - ID Github : Rafhiky
- NOM : SCHMIED Margaux - ID Github : margauxschmied
- NOM : BATISSE Dylann - ID Github : takitsu21



## <u>Introduction :</u>
Le but de ce projet est de développer en groupe un jeu de type <b>RogueLike</b>,
en s'appuyant sur des méthodes utilisées en entreprise telle que la methode agile, 
pour developper le jeu nous nous somme aidé de <i>[MAVEN](https://maven.apache.org/). </i>

Un Rogue Like est est un sous-genre de jeu vidéo de rôle 
dans lequel le joueur explore un donjon infesté de monstres qu’il doit combattre pour gagner de l’expérience et des trésors.
Le genre se caractérise notamment par la génération procédurale de ses niveaux, son système de mort permanente, 
son gameplay au tour par tour et la représentation des éléments qui le composent par des symboles ASCII sur une carte constituée de tuiles.




## <u>Contrôle du Joueur :</u>
 - Dépalcement: ``z q s d ``
 - Attaque: 
    - attaque de zone autour du joueur:``a``
    - attaque un unique monstre:``e``
 - Inventaire: ``i`` (navigation ``z s ``, selection``return``)
 - interraction: ``x``

## <u>Representation du jeu :</u>
- Monstre:
   - Orc:``o``
   - Goblin:``g``
- Coffre:``c``
- Potion ou poison de force:``F``
- Potion ou poison de vie:``V``
- Porte:``P``
- Portail:``§``

## <u>Conseil de jeu :</u>
Pour gagner de l'expérience tuer des monstres. Cela vous permettra de monter en niveau et d'augmenter vos statistiques.
Ouvrir des coffres vous permettra d'obtenir des équipements que vous pourrez visualiser dans l'inventaire.
Marcher sur des potions vous donnera l'opportunité de gagner des bonus ou des malus.
Pour changer de salle déplacer vous sur les portes et vous serez téléporté dans la salle relié.
Le changement de monde s'effectue lorsque vous marché sur le portail.





## <u>Pour run l'artefact : </u>
```
CMD SHELL : "java -jar RogueLikeMADY-1.0-SNAPSHOT.jar"
````
