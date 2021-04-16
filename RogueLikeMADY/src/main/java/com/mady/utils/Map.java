package com.mady.utils;


import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.Chest;
import com.mady.utils.entities.factories.items.Item;
import com.mady.utils.entities.factories.items.ItemFactory;
import com.mady.utils.entities.factories.monster.AbstractMonster;
import com.mady.utils.entities.factories.monster.MonsterFactory;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private Frame frame;
    private final int nbSalles;
    private final Case[][] map;
    private final List<Salle> salles = new ArrayList<>();
//    private final MonsterFactory instance = new MonsterFactory();

    private final int BASE_HEIGHT;
    private final int BASE_WIDTH;
    private Player player;

    private List<PairPos> chemins = new ArrayList<>();
    private final List<Entities> entities = new ArrayList<>();


    public static void main(String[] args) {
        Frame frame = new Frame();
        Map map = new Map(15, 30, 200, frame);
        map.createMap();
        Salle salle= map.chooseSalle();
        Player player = new Player(map.randomPosPlayerInSalle(salle), 10, 5, 1, "@", salle);
        map.addPlayerToMap(player);
        System.out.println(map);
    }


    public Map(int nbSalles, int BASE_HEIGHT, int BASE_WIDTH, Frame frame) {
        this.frame=frame;
        this.nbSalles = nbSalles;
        this.BASE_HEIGHT = BASE_HEIGHT;
        this.BASE_WIDTH = BASE_WIDTH;
        this.map = new Case[BASE_HEIGHT][BASE_WIDTH];
    }

    public Map(int nbSalles, Frame frame) {
        this(nbSalles, 16, 128, frame);
    }

    public boolean createMap() {
        boolean bRoom;
        boolean bPath;
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                map[i][j] = new Case(" ");
            }
        }

        bRoom=generateRooms();
        bPath=selectLien();
        generatePortal();
        generateEntities();
        generateItems();
        return !bRoom || !bPath;
    }


    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }
    public Salle chooseSalle() {
        Salle s = salles.get(Util.r.nextInt(salles.size()));
        return s;
    }

    public Position randomPosPlayerInSalle(Salle s) {
        Position pos = s.getFreePlaceInsideRoom();
        return pos.incrementPos(s.getPos());
    }


    public Case getCase(Position p) {
        return this.map[p.getX()][p.getY()];
    }
    public Case getCase(int x, int y) {
        return this.map[x][y];
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addPlayerToMap(Player player) {
        setPlayer(player);
        map[player.getPosition().getX()][player.getPosition().getY()].setRepr(player.getRepr());
    }

    private boolean generateRoom(Position p) {
        int x = p.getX();
        int y = p.getY();
        Salle s = new Salle(p);
        int securite=20;
        while (securite>=0 && !checkFreeArea(p, s.getlignes(), s.getcolonnes())){
            securite-=1;
            p = p.getRandomPos(BASE_HEIGHT, BASE_WIDTH);
            s = new Salle(p);
            x = p.getX();
            y = p.getY();
        }
        if(securite>=0) {
            for (int i = 0; i < s.getlignes(); i++) {
                for (int j = 0; j < s.getcolonnes(); j++) {
                    map[i + x][j + y] = s.getRepresentation()[i][j];

                }
            }
            salles.add(s);
        }
        return securite>=0;
    }


    public boolean isInside(int x, int y) {
        return (x >= 0 && x < BASE_HEIGHT)
                && (y >= 0 && y < BASE_WIDTH);
    }


    private boolean generateRooms() {
        boolean b=true;
        Position p = new Position(0, 0);
        for (int i = 0; i < nbSalles; i++) {
            b=b && generateRoom(p.getRandomPos(BASE_HEIGHT, BASE_WIDTH));
        }
        return b;
    }

    private boolean checkFreeArea(Position p, int lignes, int colonnes) {
        boolean result = true;
        int x = p.getX() > 2 ? p.getX() - 3 : p.getX();
        int y = p.getY() > 2 ? p.getY() - 3 : p.getY();
        lignes = lignes != BASE_HEIGHT ? lignes + 5 : lignes; //pour garantir 3 cases entre les salles + les walls
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


    private boolean selectLien() {
        boolean b=true;
        Salle s = salles.get(0);
        ArrayList<Boolean> relier = new ArrayList<>(salles.size());
        for (int i = 0; i < salles.size(); i++) {
            relier.add(false);
        }

        relier.set(0, true);

        while (relier.contains(false)) {
            Double distance = (double) Integer.MAX_VALUE;
            Salle salleselect = s;
            for (Salle s2 : salles) { //on cherche la salle la plus proche de s
                Double distance2 = Math.sqrt(Math.pow(s2.getPos().getX() - s.getPos().getX(), 2)
                        + Math.pow(s2.getPos().getY() - s.getPos().getY(), 2));
                if (!s.equals(s2) && distance2 < distance && !relier.get(salles.indexOf(s2))) {
                    distance = distance2;
                    salleselect = s2;

                }
            }
//            map[s.getPos().getX()][s.getPos().getY()].setRepr(String.valueOf(salles.indexOf(s)));
//            map[salleselect.getPos().getX()][salleselect.getPos().getY()].setRepr(String.valueOf(salles.indexOf(salleselect)));
//            System.out.println(String.valueOf(salles.indexOf(s)) + "-" + String.valueOf(salles.indexOf(salleselect)));
            b= b && relie(s, salleselect);
            s = salleselect;
            relier.set(salles.indexOf(salleselect), true);
        }
        return b;
    }


    private boolean relie(Salle s1, Salle s2) {

        Position pos1 = s1.findMiddle();
        Position pos2 = s2.findMiddle();
        /*if (s1.getPos().getX() < s2.getPos().getX()) {
            if (s1.getPos().getY() < s2.getPos().getY()) {
                x1 = s1.getlignes() - 1;
                y1 = s1.getcolonnes() / 2;
                x2 = s2.getlignes() / 2;
                y2 = 0;
                //gauche+bas
            } else {
                x1 = s1.getlignes() / 2;
                y1 = 0;
                x2 = 0;
                y2 = s2.getcolonnes() / 2;

                //gauche+haut
            }
        } else {
            if (s1.getPos().getY() < s2.getPos().getY()) {
                x1 = s1.getlignes() / 2;
                y1 = s1.getcolonnes() - 1;
                x2 = s2.getlignes() - 1;
                y2 = s2.getcolonnes() / 2;

                //droit+bas
            } else {
                x1 = 0;
                y1 = s1.getcolonnes() / 2;
                x2 = s2.getlignes() / 2;
                y2 = s2.getcolonnes() - 1;

                //droit+haut
            }
        }
        map[x1 + s1.getPos().getX()][y1 + s1.getPos().getY()] = new Case("'", CaseType.PATH);
        map[x2 + s2.getPos().getX()][y2 + s2.getPos().getY()] = new Case("'", CaseType.PATH);*/

        AStar aStar = new AStar();
        int[][] res = aStar.search(this, 0, pos1, pos2, s1, s2); //on cherche un chemin partant du centre des 2 salles


        return setupPaths(res);
    }

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
        if(portes.size()==2) {
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

    public List<Salle> getSalles() {
        return salles;
    }

    public int getBASE_HEIGHT() {
        return BASE_HEIGHT;
    }

    public int getBASE_WIDTH() {
        return BASE_WIDTH;
    }

    private void generateEntities() {

//        addPlayerToMap(player);
        int nbMonstersByRoom;
        for (int i = 0; i < nbSalles; i++) {
            nbMonstersByRoom = Util.r.nextInt(4);
            addEntity(nbMonstersByRoom);
        }

    }

    private void addEntity(int nbMonsters) {
        for (int i = 0; i < nbMonsters; i++) {
            Salle salle = chooseSalle();
            Position pos = randomPosPlayerInSalle(salle);
            while(nextToDoor(pos) || map[pos.getX()][pos.getY()].isPortal()){ //l'entity ne peux pas etre generé devant une porte
                pos = randomPosPlayerInSalle(salle);
            }

            Entities entity = MonsterFactory.getInstance().generate(
                    Util.r.nextInt(MonsterFactory.nbMonsters), pos, salle);
            map[pos.getX()][pos.getY()].setEntity(entity);
            entities.add(entity);
        }
    }

    //ajout des items dans les salles de la map

    private void addItems(int nbItem) {
        for (int i = 0; i < nbItem; i++) {
            Position pos = randomPosPlayerInSalle(chooseSalle());
            while(nextToDoor(pos) || map[pos.getX()][pos.getY()].isPortal()){ //l'item ne peux pas etre generé devant une porte
                pos = randomPosPlayerInSalle(chooseSalle());
            }
            Item item = ItemFactory.getInstance().generate(pos, Util.getRandomItem());
            map[pos.getX()][pos.getY()].setItem(item);
        }
    }

    private void generateItems() {
        int nbMaxItems = Util.r.nextInt(2) + 5;
        addItems(nbMaxItems);
    }


    /*mouvement des entities */
    public void clearCase(Case c) {

        c.setItem(null);
        c.setEntity(null);
        if (c.isPath()) {
            c.setRepr("P");
        }

    }

    public void clearCase(Position pos) {
        map[pos.getX()][pos.getY()] = new Case(CaseType.SALLE);
    }


    public boolean move(Entities e, Position p) {

        Position firstPos = e.getPosition();
        Position newPos = firstPos.incrementPos(p);
        Case oldCase = this.map[firstPos.getX()][firstPos.getY()];
        Case newCase = this.map[newPos.getX()][newPos.getY()];
        if (newCase.isFreeCase() && newCase.isSalle()) { //mouvement le plus basique
            clearCase(oldCase);
            newCase.setEntity(e);
            e.setPos(newPos);
            return true;
        }
        if (e instanceof Player) {
            if (oldCase.isPath() && newCase.isSalle() && !newCase.isOccupied()) {
                clearCase(oldCase);
                newCase.setEntity(e);
                e.setPos(newPos);

                return true;
            }
            if (oldCase.isPath() && newCase.isPath()) {
                Position newPos2 = findDoor(newPos); // gère les cas où l'on se déplace de chemin en chemin
                if (newPos2 == null) { //gère les retour en arrière dans un couloir
                    newPos2 = findDoor(firstPos);
                }
                newCase = this.map[newPos2.getX()][newPos2.getY()];
                clearCase(oldCase);
                newCase.setEntity(e);
                e.setPos(newPos2);
                return true;

            }
            if (newCase.isPath()) {
                Position newPos2 = findDoor(newPos);
                newCase = this.map[newPos2.getX()][newPos2.getY()];
                clearCase(oldCase);
                newCase.setEntity(e);
                e.setPos(newPos2);
                return true;

            }

            if (newCase.getItem() != null && !(newCase.getItem() instanceof Chest)){
//                System.out.println("vie " +((Player) e).getHitPoints()+"\n"+((Player) e).getDamages());
                System.out.printf("stat item :\n\t"+newCase.getItem().getName()+"\n\tforce: "+newCase.getItem().getDamages()+"\n");
                clearCase(oldCase);
                ((Player) e).useItem(newCase);
//                System.out.println("new stat palyer : ");
//                System.out.println("\tvie " +((Player) e).getHitPoints()+"\n\tforce: "+((Player) e).getDamages());
                newCase.setEntity(e);
                e.setPos(newPos);
                return true;
            }
            if(newCase.isPortal()){
                clearCase(oldCase);
                newCase.setEntity(e);
                e.setPos(newPos);
                return true;
            }

        }

        return false;
    }




    //cherche une porte associé a la position newpos
    private Position findDoor(Position newPos) {
//        System.out.println(newPos.toString());
//        System.out.println("find door");
        for (PairPos chemin : chemins) {

            if (chemin.getP1().equals(newPos)) {
                return chemin.getP2();
            }
            if (chemin.getP2().equals(newPos)) {
                return chemin.getP1();
            }
        }
        return null;
    }


        private boolean nextToDoor(Position pos){
            return findDoor(new Position(pos.getX()-1, pos.getY())) != null
                    || findDoor(new Position(pos.getX()+1, pos.getY())) != null
                    || findDoor(new Position(pos.getX(), pos.getY()-1)) != null
                    || findDoor(new Position(pos.getX(), pos.getY()+1)) != null;
        }




    private void generatePortal() {
            Position pos = randomPosPlayerInSalle(chooseSalle());
            while(nextToDoor(pos)){ //l'item ne peux pas etre generé devant une porte
                pos = randomPosPlayerInSalle(chooseSalle());
            }

            map[pos.getX()][pos.getY()]=new Case("§",CaseType.PORTAL);
    }

    /**
    Affiche la map
     */

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        String playerHud = String.format("HP : %d/%d | MP %d/%d | Lvl %d [%d/%d EXP]",
                (int)player.getHP(), (int)player.getMaxHp(), (int)player.getMP(), (int)player.getMaxMp(),
                player.getLvl(), (int)player.getExp(), (int)player.getExpMax());
        System.out.println(playerHud);
//        player.updateStats();

        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                if (i == 0 || j == BASE_WIDTH - 1 || j == 0 || i == BASE_HEIGHT - 1) {
                    sb.append('\"');
                }
                else {
                    sb.append(map[i][j].toString());
                }
            }
            sb.append("\n");
        }

//        sb.append('"');
//        sb.append(getPlayer().getStuff().toString());


        return sb.toString();
    }

    public List<Entities> getEntities() {
        return entities;
    }

    public Entities closeCheckAround(){
        Position playerPos = getPlayer().getPosition();

        System.out.println(playerPos);
        for (int i = playerPos.getX() - 1; i <= playerPos.getX() + 1; i++) {
            for (int j = playerPos.getY() - 1; j <= playerPos.getY() + 1; j++) {
                if (isInside(i, j) && map[i][j].getEntity() instanceof AbstractMonster) {
                    return getMap()[i][j].getEntity();
                }
            }
        }
        /*if (map[playerPos.getX() - 1][playerPos.getY()].getEntity() instanceof AbstractMonster){
            return map[playerPos.getX() - 1][playerPos.getY()].getEntity();
        }

        if (map[playerPos.getX() + 1][playerPos.getY()].getEntity() instanceof AbstractMonster){
            return map[playerPos.getX() + 1][playerPos.getY()].getEntity();
        }

        if (map[playerPos.getX()][playerPos.getY()-1].getEntity() instanceof AbstractMonster){
            return map[playerPos.getX()][playerPos.getY()-1].getEntity();
        }

        if (map[playerPos.getX()][playerPos.getY()+1].getEntity() instanceof AbstractMonster){
            return map[playerPos.getX()][playerPos.getY()+1].getEntity();
        }*/

        return null;
    }

    public List<Entities> zoneCheckAround(){
        Position playerPos = getPlayer().getPosition();
        List<Entities> monstersAround = new ArrayList<>();
        for (int i = playerPos.getX() - 1; i <= playerPos.getX() + 1; i++) {
            for (int j = playerPos.getY() - 1; j <= playerPos.getY() + 1; j++) {
                Entities entity = map[i][j].getEntity();
                if (isInside(i, j) && entity instanceof AbstractMonster
                        && !monstersAround.contains(entity)) {
                    monstersAround.add(map[i][j].getEntity());
                    System.out.println(map[i][j].getEntity().getPosition());
                }
            }
        }

        return monstersAround;
    }
}
