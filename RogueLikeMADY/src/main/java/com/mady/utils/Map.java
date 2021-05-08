package com.mady.utils;


import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.*;
import com.mady.utils.entities.factories.monster.AbstractMonster;
import com.mady.utils.entities.factories.monster.Boss;
import com.mady.utils.entities.factories.monster.MonsterFactory;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int nbSalles;
    private final Case[][] map;
    private final List<Salle> salles = new ArrayList<>();
    protected final int BASE_HEIGHT;
    protected final int BASE_WIDTH;
    private final List<Entities> entities = new ArrayList<>();
    private final List<PairPos> chemins = new ArrayList<>();
    private final Pause pause = new Pause();
    private Salle salleBoss;
    private Player player;
    private Boss boss;
    private int nbMaxTrap = 3;
    private int securite;
    private boolean addBoss;


    public Map(int nbSalles, int BASE_HEIGHT, int BASE_WIDTH, boolean addBoss) {
        this.nbSalles = nbSalles;
        this.BASE_HEIGHT = BASE_HEIGHT;
        this.BASE_WIDTH = BASE_WIDTH;
        this.map = new Case[BASE_HEIGHT][BASE_WIDTH];
        this.addBoss=addBoss;
    }


    public Map(int nbSalles, boolean addBoss) {
        this(nbSalles, 24, 128, addBoss);
    }

    /**
     * @return the map with rooms, paths, entities and items
     */
    public boolean createMap() {
        boolean bRoom;
        boolean bPath = true;
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                map[i][j] = new Case(" ");
            }
        }
        bRoom = generateRooms();
        if (bRoom) {
            bPath = selectLien();
        }
        return !bRoom || !bPath;
    }


    public void addEntityItemPortal() {
        salleBoss = chooseSalle();
        while(salleBoss.equals(player.getSalle())){
            salleBoss = chooseSalle();
        }
        generatePortal();
        generateTrap();
        generateEntities();
        generateItems();
        generatePortalshop();
    }



    public Salle chooseSalle() {
        return salles.get(Util.r.nextInt(salles.size()));
    }

    /**
     * @param s
     * @return the position of the player in each new map
     */
    public Position randomPosPlayerInSalle(Salle s) {
        Position pos = s.getFreePlaceInsideRoom();
        return pos.incrementPos(s.getPos());
    }

    /**
     * Case de la map a la position (x, y)
     * @param x ligne
     * @param y colonne
     * @return Case de la map
     */
    public Case getCase(int x, int y) {
        return map[x][y];
    }

    /**
     * Case de la map a la position (x, y)
     * @param pos position x, y
     * @return Case de la map
     */
    public Case getCase(Position pos) {
        return map[pos.getX()][pos.getY()];
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @param player display the player on the map
     */
    public void addPlayerToMap(Player player) {
        setPlayer(player);
        map[player.getPosition().getX()][player.getPosition().getY()].setEntity(player);
        player.setSalle(findRoom(player.getPos()));
    }

    /**
     * @param p
     * @return a bool.
     * generation of one room in the map.
     * Security is here to prevent a infinite loop on the research for place for a room on the map.
     * If the security is exceeded, it indicates that we are in an infinite loop and we need to regenerate all the rooms
     */
    private boolean generateRoom(Position p) {
        int x = p.getX();
        int y = p.getY();
        Salle s = new Salle(p);
        securite = 50;
        while (securite > 0 && !checkFreeArea(p, s.getlignes(), s.getcolonnes())) {
            securite -= 1;
            p = p.getRandomPos(BASE_HEIGHT, BASE_WIDTH);
            s = new Salle(p);
            x = p.getX();
            y = p.getY();
        }
        if (securite > 0) {
            for (int i = 0; i < s.getlignes(); i++) {
                for (int j = 0; j < s.getcolonnes(); j++) {
                    map[i + x][j + y] = s.getRepresentation()[i][j];
                }
            }
            salles.add(s);
        }
        return securite >= 0;
    }

    /**
     * @param x position en x
     * @param y position en y
     * @return wether or not the position is in the map.
     */
    public boolean isInside(int x, int y) {
        return (x >= 0 && x < BASE_HEIGHT)
                && (y >= 0 && y < BASE_WIDTH);
    }

    /**
     * @return if the generation of all the rooms is a success or not.
     */
    private boolean generateRooms() {
        boolean b = true;
        Position p = new Position(0, 0);
        for (int i = 0; i < nbSalles; i++) {
            b = b && generateRoom(p.getRandomPos(BASE_HEIGHT, BASE_WIDTH));
        }
        return b;
    }

    /**
     * @param p        position de la salle a verifier
     * @param lignes   nb lignes de la salle
     * @param colonnes nb de colonnes de la salle
     * @return a bool
     * permet de savoir si on a un espace minimal de 3 entre les salles pour qu'elles ne soient pas côte à côte.
     */
    private boolean checkFreeArea(Position p, int lignes, int colonnes) {
        boolean result = true;
        int x = p.getX() > 2 ? p.getX() - 3 : p.getX();
        int y = p.getY() > 2 ? p.getY() - 3 : p.getY();
        lignes = lignes != BASE_HEIGHT ? lignes + 5 : lignes;
        colonnes = colonnes != BASE_WIDTH ? colonnes + 5 : colonnes;
        if (isInside(lignes + x, colonnes + y)) {
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < colonnes; j++) {
                    if (!isInside(i + x, j + y) || !map[i + x][j + y].isMap()) {
                        result = false;
                        break;
                    }
                }
            }
        } else {
            result = false;
        }
        return result;
    }


    /**
     * @return a bool.
     * fonction qui va permettre de déterminer comment les salles seront reliées. Une salle est reliée à la salle
     * qui est la plus proche d'elle.
     */
    private boolean selectLien() {
        boolean b = true;
        Salle s = salles.get(0);
        ArrayList<Boolean> relier = new ArrayList<>(salles.size());
        for (int i = 0; i < salles.size(); i++) {
            relier.add(false);
        }
        relier.set(0, true);
        while (relier.contains(false)) {
            double distance = Integer.MAX_VALUE;
            Salle salleselect = s;
            for (Salle s2 : salles) {
                double distance2 = Math.sqrt(Math.pow(s2.getPos().getX() - s.getPos().getX(), 2)
                        + Math.pow(s2.getPos().getY() - s.getPos().getY(), 2));
                if (!s.equals(s2) && distance2 < distance && !relier.get(salles.indexOf(s2))) {
                    distance = distance2;
                    salleselect = s2;
                }
            }
            b = b && relie(s, salleselect);
            s = salleselect;
            relier.set(salles.indexOf(salleselect), true);
        }
        return b;
    }

    /**
     * @param s1 salle de depart
     * @param s2 salle d'arrivé
     * @return a bool
     * fonction qui recherche le chemin le plus optimisé entre 2 salles. La recherche entre une salle A et B, part
     * du centre de la salle A et arrive au centre de la salle B.
     */
    private boolean relie(Salle s1, Salle s2) {
        Position pos1 = s1.findMiddle();
        Position pos2 = s2.findMiddle();
        AStar aStar = new AStar();
        int[][] res = aStar.search(this, 0, pos1, pos2, s1, s2);
        return setupPaths(res);
    }

    /**
     * @param solvedPath chemin a implementer
     * @return a bool
     * impression sur la map des chemins et des portes en fonction des chemins qui ont été trouvés.
     */
    private boolean setupPaths(int[][] solvedPath) {
        List<Position> portes = new ArrayList<>();
        for (int i = 0; i < solvedPath.length; i++) {
            for (int j = 0; j < solvedPath[i].length; j++) {
                if (solvedPath[i][j] != -1 && !map[i][j].isSalle()) {
                    if (map[i][j].isWall()) {
                        map[i][j].setRepr("P");
                        portes.add(new Position(i, j));
                    } else {
                        map[i][j].setRepr("'");
                    }
                    map[i][j].setCt(CaseType.PATH);
                }
            }
        }
        if (portes.size() == 2) {
            chemins.add(new PairPos(portes.get(0), portes.get(1)));
            return true;
        }
        return false;
    }

    public int getNbSalles() {
        return nbSalles;
    }

    public Case[][] getMap() {
        return map;
    }

    public int getBASE_HEIGHT() {
        return BASE_HEIGHT;
    }

    public int getBASE_WIDTH() {
        return BASE_WIDTH;
    }

    public Pause getPause() {
        return pause;
    }

    /**
     * generation des différentes entités selon un nombre pré-défini
     */
    private void generateEntities() {
        int nbMonstersByRoom;
        for (Salle salle : salles) {
            if (salle.equals(salleBoss) && addBoss) {
                addBoss();
            } else {
                nbMonstersByRoom = Util.r.nextInt(6) + 1;
                addEntity(nbMonstersByRoom, salle);
            }
        }
    }

    private void addBoss() {
        Position pos = randomPosPlayerInSalle(salleBoss);
        securite=20;
        while (securite>0 && (nextToDoor(pos) || map[pos.getX()][pos.getY()].isPortal() || map[pos.getX()][pos.getY()].isOccupied())) {
            securite-=1;
            pos = randomPosPlayerInSalle(salleBoss);
        }
        if(securite>0) {
            boss = new Boss(pos, salleBoss);
            map[pos.getX()][pos.getY()].setEntity(boss);
            entities.add(boss);
        }
    }

    /**
     * @param nbMonsters on ajoute à la map les entités générées. Celles-ci ne peuvent pas être placées devant les portes
     */
    public void addEntity(int nbMonsters, Salle salle) {
        //Salle salle = salles.get(idx);
        for (int i = 0; i < nbMonsters; i++) {

            Position pos = randomPosPlayerInSalle(salle);
            securite = 20;
            while (securite>0 && (nextToDoor(pos) || map[pos.getX()][pos.getY()].isPortal() || map[pos.getX()][pos.getY()].isOccupied()
                    || map[pos.getX()][pos.getY()].isPlayer())) {
                securite-=1;
                pos = randomPosPlayerInSalle(salle);
            }
            if (securite > 0) {
                Entities entity = MonsterFactory.getInstance().generate(
                        Util.r.nextInt(MonsterFactory.nbMonsters), pos, salle);
                map[pos.getX()][pos.getY()].setEntity(entity);
                entities.add(entity);
            }
        }
    }


    /**
     * @param nbItem Ajout à la map des items. Ici aussi le nombre est pré-défini et ceux-ci ne peuvent pas être palcés devant
     *               les portes
     */
    private void addItems(int nbItem) {
        for (int i = 0; i < nbItem; i++) {
            Position pos = randomPosPlayerInSalle(chooseSalle());
            securite=20;
            while (securite>0 && (nextToDoor(pos) || map[pos.getX()][pos.getY()].isPortal())) {
                securite-=1;
                pos = randomPosPlayerInSalle(chooseSalle());
            }
            if(securite>0) {
                Item item = ItemFactory.getInstance().generate(pos, Util.getRandomItem(), player);
                map[pos.getX()][pos.getY()].setItem(item);
            }
        }
    }

    private void generateItems() {
        int nbMaxItems = Util.r.nextInt(2) + 5;
        addItems(nbMaxItems);
    }

    private void generateTrap() {
        int nbTrap = Util.r.nextInt(nbMaxTrap);
        addTrap(nbTrap);
    }

    private void addTrap(int nbTrap) {
        for (int i = 0; i < nbTrap; i++) {
            Position pos = randomPosPlayerInSalle(chooseSalle());
            securite=20;
            while (securite>0 && (nextToDoor(pos) || map[pos.getX()][pos.getY()].isPortal() || map[pos.getX()][pos.getY()].isOccupied())) {
                securite-=1;
                pos = randomPosPlayerInSalle(chooseSalle());
            }
            if(securite>0) {
                Item item = new Trap(pos, player.getLvl(), player.getMultiplicateur(), this);
                map[pos.getX()][pos.getY()].setItem(item);
            }
        }
    }


    /**
     * @param c nettoyage d'une case après qu'une entité l'est quitée.
     */
    public void clearCase(Case c) {
        c.setAttackBoss(false);
        c.setItem(null);
        c.setEntity(null);
        if (c.isPath()) {
            c.setRepr("P");
        }
    }

    public void clearCase(Position pos) {
        map[pos.getX()][pos.getY()] = new Case(CaseType.SALLE);
    }

    /**
     * @param e l'entité a déplacer
     * @param p la position où deplacer l'entité
     * @return a bool.
     * implémentation du mouvement des entités.
     * Gestion du mouvment basique de case à case et gestion des mouvements entre les salles.
     */
    public boolean move(Entities e, Position p) {
//        System.out.printf("test move");
        Position firstPos = e.getPosition();
        Position newPos = firstPos.incrementPos(p);
        Case oldCase = this.map[firstPos.getX()][firstPos.getY()];
        Case newCase = this.map[newPos.getX()][newPos.getY()];
        boolean success = false;
        /* Mouvment basique*/

        if (newCase.isFreeCase() && (newCase.isSalle() || newCase.isPrice())) {
            clearCase(oldCase);
            newCase.setEntity(e);
            e.setPos(newPos);
            success = true;
        }
        else if (e instanceof Player) {
            if (oldCase.isPath() && newCase.isSalle() && !newCase.isOccupied()) {
                clearCase(oldCase);
                newCase.setEntity(e);
                e.setPos(newPos);
                success = true;
            }
            else if (oldCase.isPath() && newCase.isPath()) {
                /*Gestion du mouvement de salle à salle*/
                Position newPos2 = findDoor(newPos);
                if (newPos2 == null) {
                    newPos2 = findDoor(firstPos);
                }
                assert newPos2 != null;
                newCase = this.map[newPos2.getX()][newPos2.getY()];
                clearCase(oldCase);
                newCase.setEntity(e);
                e.setPos(newPos2);
                newPos = newPos2;
                success = true;
            }
            else if (newCase.isPath()) {
                Position newPos2 = findDoor(newPos);
                assert newPos2 != null;
                newCase = this.map[newPos2.getX()][newPos2.getY()];
                clearCase(oldCase);
                newCase.setEntity(e);
                e.setPos(newPos2);
                newPos = newPos2;
                success = true;
            }
            else if (newCase.getItem() != null && !(newCase.getItem() instanceof Chest)) {
                clearCase(oldCase);
                ((Player) e).useItem(newCase);
                newCase.setEntity(e);
                e.setPos(newPos);
                success = true;
            }
            else if (newCase.getItem() instanceof PaidChest) {
                ((PaidChest) newCase.getItem()).showItem();
            }
            else if (newCase.isPortal() ||newCase.isShop() ||newCase.isShopLeave()) {
                clearCase(oldCase);
                newCase.setEntity(e);
                e.setPos(newPos);
                success = true;
            }
        }
        if (success) {
            e.setNbDeplacement(e.getNbDeplacement() + 1);
            if (boss != null && e instanceof Player && this.map[newPos.getX()][newPos.getY()].isAttackBoss()) {
                boss.attack((Player) e);
            }
        }

        return success;
    }

    private Salle findRoom (Position pos){
        for (Salle salle:salles){
            if (salle.inSalle(pos)){
                return salle;
            }
        }
        System.out.println("marche pas");
        return null;
    }

    /**
     * @param newPos recherche la porte associer a cette position
     * @return position
     * cherche une porte associée à une nouvelle position
     */
    private Position findDoor(Position newPos) {
        for (PairPos chemin : chemins) {
            if (chemin.getP1().equals(newPos)) {
                player.setSalle(findRoom(chemin.getP2()));
                return chemin.getP2();
            }
            if (chemin.getP2().equals(newPos)) {
                player.setSalle(findRoom(chemin.getP1()));
                return chemin.getP1();
            }
        }
        return null;
    }

    /**
     * @param pos la position a tester
     * @return a bool.
     * regarde si une postion donnée est à côté d'une porte
     */
    private boolean nextToDoor(Position pos) {
        return findDoor(new Position(pos.getX() - 1, pos.getY())) != null
                || findDoor(new Position(pos.getX() + 1, pos.getY())) != null
                || findDoor(new Position(pos.getX(), pos.getY() - 1)) != null
                || findDoor(new Position(pos.getX(), pos.getY() + 1)) != null;
    }

    /**
     * generation des escaliers qui permettent d'évoluer entre salles. Celui-ci ne peut pas être placé devant une porte.
     */
    private void generatePortal() {
        Position pos = randomPosPlayerInSalle(salleBoss);
        while (nextToDoor(pos)) {
            pos = randomPosPlayerInSalle(salleBoss);
        }
        map[pos.getX()][pos.getY()] = new Case("§", CaseType.PORTAL);
    }

    /**
     * generation des escaliers qui permettent d'entrer dans un SHOP. Celui-ci ne peut pas être placé devant une porte.
     */
     private void generatePortalshop(){
         Salle salle;
         do {
             salle = chooseSalle();
         }
         while (salle.equals(salleBoss));

         Position pos = randomPosPlayerInSalle(salle);

         while (nextToDoor(pos)) {
             pos = randomPosPlayerInSalle(salle);
         }
         map[pos.getX()][pos.getY()] = new Case("$", CaseType.SHOPPORTAL);


     }



    /**
     * Affiche la map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Ansi.colorize(String.format("HP : %d/%d | ", player.getHitPoints(), player.getMaxHitPoints())
                , Attribute.RED_TEXT())).append(Ansi.colorize(String.format("MP %d/%d | ", player.getMP(),
                player.getMaxMp()), Attribute.BLUE_TEXT()))
                .append(Ansi.colorize(String.format("Lvl %d ", player.getLvl()), Attribute.YELLOW_TEXT()))
                .append(Ansi.colorize(String.format("[%d/%d EXP] | ", player.getExp(), player.getExpMax()),
                        Attribute.MAGENTA_TEXT()))
                .append(Ansi.colorize(String.format("%d MADY Coins\n", player.getCoins()),
                        Attribute.BRIGHT_YELLOW_TEXT()));

        for (int i = 0; i <= BASE_WIDTH + 1; i++) {
            sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT()));
        }
        sb.append("\n");

        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                if (i == BASE_HEIGHT - 1 && j == 0) {
                    sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT()));
                } else if (j == 0) {
                    sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT()));
                    sb.append(map[i][j].toString());
                } else if (i == BASE_HEIGHT - 1) {
                    sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT()));
                } else {
                    sb.append(map[i][j].toString());
                }
            }
            if (i == BASE_HEIGHT - 1) {
                sb.append(Ansi.colorize("\"\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT())).append("\n");
            } else {
                sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT())).append("\n");
            }
        }
        sb.append(Util.currentAction);
        Util.currentAction = new StringBuilder();
        return sb.toString();
    }

    public List<Entities> getEntities() {
        return entities;
    }

    /**
     * @return une entité ou null si pas de mosntres autour.
     * recherche de monstres autour du player afin de faire une attaque monocible.
     * l'attaque en diagonale est possible.
     * la recherche commence sur la diagonale supérieure gauche.
     * retourne le premier monstre trouvé.
     */
    public Entities closeCheckAround() {
        Position playerPos = getPlayer().getPosition();

        if (isInside(playerPos.getX() - 1, playerPos.getY()) && map[playerPos.getX() - 1][playerPos.getY()].getEntity() instanceof AbstractMonster) {
            return map[playerPos.getX() - 1][playerPos.getY()].getEntity();
        }

        if (isInside(playerPos.getX() + 1, playerPos.getY()) && map[playerPos.getX() + 1][playerPos.getY()].getEntity() instanceof AbstractMonster) {
            return map[playerPos.getX() + 1][playerPos.getY()].getEntity();
        }

        if (isInside(playerPos.getX(), playerPos.getY() - 1) && map[playerPos.getX()][playerPos.getY() - 1].getEntity() instanceof AbstractMonster) {
            return map[playerPos.getX()][playerPos.getY() - 1].getEntity();
        }

        if (isInside(playerPos.getX(), playerPos.getY() + 1) && map[playerPos.getX()][playerPos.getY() + 1].getEntity() instanceof AbstractMonster) {
            return map[playerPos.getX()][playerPos.getY() + 1].getEntity();
        }


        return null;
    }

    /**
     * @return une lsite d'entités.
     * comme pour la recherche de l'attaque unique, on commence avec la diagonle supérieure gauche.
     * Chaque entité trouvée sera ajoutée à la liste retournée.
     */
    public List<Entities> zoneCheckAround() {
        Position playerPos = getPlayer().getPosition();
        List<Entities> monstersAround = new ArrayList<>();

        if (isInside(playerPos.getX() - 1, playerPos.getY())
                && map[playerPos.getX() - 1][playerPos.getY()].getEntity() instanceof AbstractMonster) {
            //&& !monstersAround.contains(map[playerPos.getX() - 1][playerPos.getY()].getEntity())) {
            monstersAround.add(map[playerPos.getX() - 1][playerPos.getY()].getEntity());
        }

        if (isInside(playerPos.getX() + 1, playerPos.getY())
                && map[playerPos.getX() + 1][playerPos.getY()].getEntity() instanceof AbstractMonster
                && !monstersAround.contains(map[playerPos.getX() + 1][playerPos.getY()].getEntity())) {
            monstersAround.add(map[playerPos.getX() + 1][playerPos.getY()].getEntity());
        }

        if (isInside(playerPos.getX(), playerPos.getY() - 1)
                && map[playerPos.getX()][playerPos.getY() - 1].getEntity() instanceof AbstractMonster
                && !monstersAround.contains(map[playerPos.getX()][playerPos.getY() - 1].getEntity())) {
            monstersAround.add(map[playerPos.getX()][playerPos.getY() - 1].getEntity());
        }

        if (isInside(playerPos.getX(), playerPos.getY() + 1)
                && map[playerPos.getX()][playerPos.getY() + 1].getEntity() instanceof AbstractMonster
                && !monstersAround.contains(map[playerPos.getX()][playerPos.getY() + 1].getEntity())) {
            monstersAround.add(map[playerPos.getX()][playerPos.getY() + 1].getEntity());
        }

        return monstersAround;
    }

    public Salle getSalleBoss() {
        return salleBoss;
    }
}
