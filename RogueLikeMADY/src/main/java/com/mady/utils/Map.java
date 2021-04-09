package com.mady.utils;


import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.Item;
import com.mady.utils.entities.factories.items.ItemFactory;
import com.mady.utils.entities.factories.monster.MonsterFactory;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private Frame frame = new Frame();
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
        Map map = new Map(15, 30, 200);

        map.createMap();
        Salle salle= map.chooseSalle();
        Player player = new Player(map.randomPosPlayerInSalle(salle), 10, 5, 1, "@", salle);
        map.addPlayerToMap(player);
        System.out.println(map);

    }


    public Map(int nbSalles, int BASE_HEIGHT, int BASE_WIDTH) {
        this.nbSalles = nbSalles;
        this.BASE_HEIGHT = BASE_HEIGHT;
        this.BASE_WIDTH = BASE_WIDTH;
        this.map = new Case[BASE_HEIGHT][BASE_WIDTH];
    }

    public Map(int nbSalles) {
        this(nbSalles, 16, 128);
    }

    public void createMap() {
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                map[i][j] = new Case(" ");
            }
        }

        generateRooms();
        selectLien();
        generateEntities();
        generateItems();
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


    public Case getcase(Position p) {
        return this.map[p.getX()][p.getY()];
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

    private void generateRoom(Position p) {
        int x = p.getX();
        int y = p.getY();
        Salle s = new Salle(p);
        while (!checkFreeArea(p, s.getlignes(), s.getcolonnes())) {
            p = p.getRandomPos(BASE_HEIGHT, BASE_WIDTH);
            s = new Salle(p);
            x = p.getX();
            y = p.getY();
        }
        for (int i = 0; i < s.getlignes(); i++) {
            for (int j = 0; j < s.getcolonnes(); j++) {
                map[i + x][j + y] = s.getRepresentation()[i][j];

            }
        }
        salles.add(s);
    }

    public boolean isInside(int x, int y) {
        return (x >= 0 && x < BASE_HEIGHT)
                && (y >= 0 && y < BASE_WIDTH);
    }


    private void generateRooms() {
        Position p = new Position(0, 0);
        for (int i = 0; i < nbSalles; i++) {
            generateRoom(p.getRandomPos(BASE_HEIGHT, BASE_WIDTH));
        }
    }

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


    private void selectLien() {
        Salle s = salles.get(0);
        ArrayList<Boolean> relier = new ArrayList<>(salles.size());
        for (int i = 0; i < salles.size(); i++) {
            relier.add(false);
        }

        relier.set(0, true);

        while (relier.contains(false)) {
            Double distance = (double) Integer.MAX_VALUE;
            Salle salleselect = s;
            for (Salle s2 : salles) {
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
            relie(s, salleselect);
            s = salleselect;
            relier.set(salles.indexOf(salleselect), true);
        }
    }


    private void relie(Salle s1, Salle s2) {

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
        int[][] res = aStar.search(this, 0, pos1, pos2, s1, s2);

        setupPaths(res);
    }

    private void setupPaths(int[][] solvedPath) {
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
        chemins.add(new PairPos(portes.get(0), portes.get(1)));
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
            nbMonstersByRoom = Util.r.nextInt(10);
            addEntity(nbMonstersByRoom);
        }
    }

    private void addEntity(int nbMonsters) {
        for (int i = 0; i < nbMonsters; i++) {
            Salle salle=chooseSalle();
            Position pos = randomPosPlayerInSalle(salle);
            System.out.println("ok2");
            while(nextToDoor(pos)){
                System.out.println("ok");
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
            Item item = ItemFactory.getInstance().generate(pos, Util.getRandomItem());
            map[pos.getX()][pos.getY()].setItem(item);
        }
    }

    private void generateItems() {
        int nbMaxItems = Util.r.nextInt(10);
        addItems(nbMaxItems);

    }


    /*mouvement des entities */
    private void clearCase(Case c) {

        c.setItem(null);
        c.setEntity(null);
        if (c.isPath()) {
            c.setRepr("P");
        }
    }


    public boolean move(Entities e, Position p) {

        Position firstPos = e.getPosition();
        Position newPos = firstPos.incrementPos(p);
        Case oldCase = this.map[firstPos.getX()][firstPos.getY()];
        Case newCase = this.map[newPos.getX()][newPos.getY()];
        if (newCase.isFreeCase() && newCase.isSalle()) { //mouvement le plus basique
            System.out.println(newCase.getItem());
            clearCase(oldCase);
            newCase.setEntity(e);
            e.setPos(newPos);
            return true;
        }
        if (e instanceof Player) {
            if (oldCase.isPath() && newCase.isSalle()) {
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
            if (newCase.getItem() != null){
                System.out.println("item ramassé !!\nstats player:");
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

        }

        return false;
    }





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
        sb.append('"');
//        sb.append(getPlayer().getStuff().toString());
        char rph = getPlayer().getStuff().getHelmet() == null ? ' ' : '*';
        char rpg = getPlayer().getStuff().getGauntlet() == null ? ' ' : '*';
        char rpc = getPlayer().getStuff().getChest() == null ? ' ' : '*';
        char rpp = getPlayer().getStuff().getPant() == null ? ' ' : '*';
        char rps = getPlayer().getStuff().getShoes() == null ? ' ' : '*';
        char rpa = getPlayer().getStuff().getAmulet() == null ? ' ' : '*';
        char rpw = getPlayer().getStuff().getWeapon() == null ? ' ' : '*';
        String HelmetStats = getPlayer().getStuff().getHelmet() == null ? " " : getPlayer().getStuff().getHelmet().toString();
        String GauntletStats = getPlayer().getStuff().getGauntlet() == null ? " " : getPlayer().getStuff().getGauntlet().toString();
        String ChestPlateStats = getPlayer().getStuff().getChest()  == null ? " " : getPlayer().getStuff().getChest() .toString();
        String PantStats = getPlayer().getStuff().getPant() == null ? " " : getPlayer().getStuff().getPant().toString();
        String AmuletStats = getPlayer().getStuff().getAmulet() == null ? " " : getPlayer().getStuff().getAmulet().toString();
        String ShoesStats = getPlayer().getStuff().getShoes() == null ? " " : getPlayer().getStuff().getShoes().toString();
        String WeaponStats = getPlayer().getStuff().getWeapon() == null ? " " : getPlayer().getStuff().getWeapon().toString();
        String fillerH = Util.filler(BASE_WIDTH-(32+HelmetStats.length()));
        String fillerW =  Util.filler(BASE_WIDTH-(41+WeaponStats.length()+AmuletStats.length()));
        String fillerG = Util.filler(BASE_WIDTH-(34+GauntletStats.length()));
        String fillerC = Util.filler(BASE_WIDTH-(36+ChestPlateStats.length()));
        String fillerP = Util.filler (BASE_WIDTH - (30+PantStats.length()));
        String fillerS = Util.filler((BASE_WIDTH - (31+ShoesStats.length())));
        sb.append("\"\"\"\"\"\"\"\"\"\"\"\"\"");
        sb.append(" STUFF : ");
        sb.append(String.format("HElMET: %s%s\"",HelmetStats,fillerH));
        sb.append("\n\"");
        sb.append(String.format("    [%c] [%c] \"         WEAPON: %s AMULET: %s%s\"\n\"",rph,rpa,WeaponStats,AmuletStats,fillerW));
        sb.append(String.format(" [%c][%c][%c]  \"         GAUNTLET: %s%s\"\n\"",rpg,rpc,rpw,GauntletStats,fillerG));
        sb.append(String.format("    [%c]     \"         CHESTPLATE: %s%s\"\n\"",rpp,ChestPlateStats,fillerC));
        sb.append(String.format("  [%c] [%c]   \"         PANT: %s%s\"\n\"",rps,rps,PantStats,fillerP));
        sb.append(String.format("\"\"\"\"\"\"\"\"\"\"\"\"\"         SHOES: %s%s\"",ShoesStats,fillerS));
        sb.append("\n");
        for(int i =  0; i < BASE_WIDTH; i++){
            sb.append('"');
        }
        return sb.toString();
    }

    public List<Entities> getEntities() {
        return entities;
    }
}
