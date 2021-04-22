package com.mady.utils;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.factories.items.Item;

public class Case {
    private String repr;
    private Object item; // objet qui representera l'objet associé a cette case (monstre, coffre etc...)
    private CaseType ct;
    private Entities entity;
    private boolean attackBoss=false;

    /**
     * Représente une case dans la map.
     *
     * @param repr Représentation de  la case.
     * @param item Item présent en interne à cette case.
     * @param ct   Type de la case (WALL, PATH, etc...)
     */
    public Case(String repr, Object item, CaseType ct) {
        this.repr = repr;
        this.item = item;
        this.ct = ct;
    }

    /**
     * Représente une case dans la map.
     * @param repr représentation de la case.
     * @param entity entité présente sur la case.
     */
    public Case(String repr, Entities entity) {
        this.repr = repr;
        this.entity = entity;
        this.ct = CaseType.MAP;
    }

    public Case(String repr) {
        this(repr, null, CaseType.MAP);
    }

    public Case(CaseType ct) {
        this(" ", null, ct);
    }

    public Case(String repr, CaseType ct) {
        this(repr, null, ct);
    }

    /**
     * @return true si la case est occupé.
     */
    public boolean isOccupied() {
        return item != null || entity != null;
    }

    /**
     * @return true si l'entité présente sur la case est un joueur.
     */
    public boolean isPlayer() {
        return entity instanceof Player;
    }

    @Override
    public String toString() {
        return getRepr();
    }

    /**
     * @return true si la case est un mur.
     */
    public boolean isWall() {
        return CaseType.WALL == ct;
    }

    /**
     * @return true si la case est une salle.
     */
    public boolean isSalle() {
        return CaseType.SALLE == ct;
    }

    /**
     * @return true si la case fait partie de la map.
     */
    public boolean isMap() {
        return CaseType.MAP == ct;
    }

    /**
     * @return une représentation de la case.
     */
    public String getRepr() {
        if (repr.equals("P")) {
            return (Ansi.colorize(repr, Attribute.BRIGHT_WHITE_TEXT(), Attribute.BRIGHT_WHITE_BACK()));
        } else if (isWall()) {
            return (Ansi.colorize(repr, Attribute.BRIGHT_BLACK_BACK(), Attribute.BRIGHT_BLACK_TEXT()));
        } else if (isPath()) {
            return (Ansi.colorize(repr, Attribute.BRIGHT_WHITE_TEXT(), Attribute.BRIGHT_WHITE_BACK()));
        } else if (isPortal()) {
            return Ansi.colorize(repr, Attribute.BRIGHT_CYAN_TEXT());
        }else if (attackBoss) {
            attackBoss=false;
            return Ansi.colorize(repr, Attribute.RED_BACK());
        }
        return repr;
    }

    /**
     *
     * @return représentation réel d'une case sans les couleurs.
     */
    public String getRealRepr() {
        return repr;
    }

    public void setRepr(String repr) {
        this.repr = repr;
    }

    public Item getItem() {
        return (Item) item;
    }

    public void setItem(Item item) {
        this.item = item;
        this.repr = item == null ? " " : item.getRepresentation();
    }

    public CaseType getCt() {
        return ct;
    }

    public void setCt(CaseType ct) {
        this.ct = ct;
    }

    public Entities getEntity() {
        return entity;
    }

    public void setEntity(Entities entity) {
        this.entity = entity;
        repr = entity == null ? " " : entity.getRepr();
    }

    /**
     * @return true si la case n'est pas du tout occupé et si ce n'est pas un mur.
     */
    public boolean isFreeCase() {
        return !isWall() && !isOccupied();
    }

    /**
     * @return true si la case est un chemin.
     */
    public boolean isPath() {
        return CaseType.PATH == ct;
    }

    public boolean isPortal() {
        return CaseType.PORTAL == ct;
    }

    public boolean isAttackBoss() {
        return attackBoss;
    }

    public void setAttackBoss(boolean attackBoss) {
        this.attackBoss = attackBoss;
    }
}
