package com.mady.utils.listener;

import com.github.rjeschke.txtmark.Processor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HelpListener implements ActionListener {
    private final JEditorPane editor = new JEditorPane();

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame helpFrame = new JFrame("Aide");
        helpFrame.setLocationRelativeTo(null);
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        renderMarkdown("# Travail Encadré d'études, de Recherches et de Developpement\n" +
                "\n" +
                "<p align=\"center\">\n" +
                "  <a href=\"https://example.com/\">\n" +
                "    <img src=\"https://i.imgur.com/cEn7FNa.png\" alt=\"Logo\" width=72 height=128>\n" +
                "  </a>\n" +
                "\n" +
                "<h3 align=\"center\">RogueLike MADY</h3>\n" +
                "\n" +
                "## <u>Développeurs :</u>\n" +
                "\n" +
                "- NOM : COUSSON Antoine - ID Github : MonsieurCo\n" +
                "- NOM : BRAULT Yann - ID Github : Rafhiky\n" +
                "- NOM : SCHMIED Margaux - ID Github : margauxschmied\n" +
                "- NOM : BATISSE Dylann - ID Github : takitsu21\n" +
                "\n" +
                "## <u>Introduction :</u>\n" +
                "\n" +
                "Le but de ce projet est de développer en groupe un jeu de type <b>RogueLike</b>, en s'appuyant sur des méthodes\n" +
                "utilisées en entreprise telle que la methode agile, pour developper le jeu nous nous somme aidé\n" +
                "de <i>[MAVEN](https://maven.apache.org/).</i>\n" +
                "\n" +
                "Un Rogue Like est un sous-genre de jeu vidéo de rôle dans lequel le joueur explore un donjon infesté de monstres qu’il\n" +
                "doit combattre pour gagner de l’expérience et des trésors. Le genre se caractérise notamment par la génération\n" +
                "procédurale de ses niveaux, son système de mort permanente, son gameplay au tour par tour et la représentation des\n" +
                "éléments qui le composent par des symboles ASCII sur une carte constituée de tuiles.\n" +
                "\n" +
                "## <u>Contrôle du Joueur :</u>\n" +
                "\n" +
                "- Déplacement:\n" +
                "    - `z` monter\n" +
                "    - `s` descendre\n" +
                "    - `q` gauche\n" +
                "    - `d` droite\n" +
                "    - `⇧ + déplacement` dash / roulade dans une direction\n" +
                "- Attaque:\n" +
                "    - `a` attaque de zone autour du joueur\n" +
                "    - `e` attaque un unique monstre\n" +
                "    - `t` attaque un unique monstre à distance\n" +
                "        - `←` `→` sélectionner les différents monstres autour de soi\n" +
                "        - `⏎` pour valider la sélection et attaquer le monstre\n" +
                "\n" +
                "- Inventaire: `i` (navigation `z s`, équiper l'objet `⏎`, jeter l'objet `⌫`)\n" +
                "\n" +
                "- Quitter n'importe quel menu d'interaction:\n" +
                "    - `ESC` (touche escape)\n" +
                "    - touche initiale d'interaction exemple : \n" +
                "      si on appuie sur `i` pour ouvrir l'inventaire on pourra le fermer avec `i` ou `esc`\n" +
        "- Consommer un elixir:\n" +
                "    - `&` elixir de vie\n" +
                "    - `é` elixir de mana\n" +
                "\n" +
                "- Interaction `x` (ouverture d'un coffre, achat d'un coffre dans le shop, achat de potions)\n" +
                "\n" +
                "- Vente\n" +
                "    - intéragir avec le marchand `!` dans le shop afin de vendre des objets.\n" +
                "    - `z`, `s` pour sélectioner un objet.\n" +
                "    - `⏎` pour confirmer la vente.\n" +
                "\n" +
                "\n" +
                "## <u>Representation du jeu :</u>\n" +
                "\n" +
                "- Monstre:\n" +
                "    - `o` Orc\n" +
                "    - `g` Goblin\n" +
                "    - `T` Troll\n" +
                "    - `w` Sorcière\n" +
                "    - `d` Druide noir\n" +
                "    - `B` Boss\n" +
                "- Coffre au trésor <span style=\"color:magenta\">C</span>, vous offre un item aléatoire a équiper, un coffre dans un shop est payant et contient un item prédéfini.\n" +
                "- Potion ou poison de mana <span style=\"color:orange\";>M</span>, vous octroie un malus ou bonus de MP.\n" +
                "- Potion ou poison de vie <span style=\"color:orange\";>V</span>, vous octroie un malus ou bonus de HP.\n" +
                "- Portail <span style=\"color:cyan\">§</span>, vous téléporte à la prochaine map.\n" +
                "    - Quand un boss est présent (toutes les 3 salles), le portail apparaît une fois le\n" +
                "    boss tué.\n" +
                "- Shop <span style=\"color:magenta\";>$</span>, vous téléporte à la map du shop.\n" +
                "- PNJ <span style=\"color:orange\";>!</span>, Henri, le marchand.\n" +
                "- Elixirs de Vie (présent dans le shop) <span style=\"background-color:red;color:red\">C</span>\n" +
                "- Elixirs de mana (présent dans le shop) <span style=\"background-color:blue;color:blue;\">C</span>\n" +
                "\n" +
                "\n" +
                "## <u>Conseils de jeu :</u>\n" +
                "\n" +
                "⚠️ Quand votre partie commence il vous faudra rester focus sur la fenêtre swing qui s'ouvrira. ⚠️\n" +
                "\n" +
                "Pour gagner de l'expérience vous devrez tuer des monstres. Cela vous permettra de monter en niveau et d'augmenter vos statistiques. \n" +
                "Ouvrir des coffres vous permet d'obtenir des équipements que vous pourrez visualiser dans l'inventaire et vendre au marchand. \n" +
                "Marcher sur des potions vous donnera l'opportunité de gagner des bonus ou des malus de vie ou de mana. \n" +
                "Pour changer de salle déplacer vous sur les portes et vous serez téléporté dans la salle relié. \n" +
                "Au cours de votre partie vous croiserez la porte menant au shop où vous pourrez vendre et acheter du materiel (⚠️ si vous sortez du shop vous ne pouvez plus y retourner). \n" +
                "Le changement de monde s'effectue lorsque vous marché sur le portail (⚠️ quand vous passez un portail vous ne pouvez pas revenir en arrière).\n" +
                "\n" +
                "Si au cours de la partie vous avez besoin d'un rappel des déplacements n'hésité pas utilisé le help du menue pause ou de la fenêtre swing.\n" +
                "\n" +
                "## <u>Bugs connus</u>\n" +
                "\n" +
                "- [X] Problème d'interactions avec le jeu de façon inattendu (on ne peut plus bouger).\n" +
                "- [ ] Les chemins peuvent se croiser entre eux mais sont tout de même utilisables.\n" +
                "- [ ] Problème de génération de chemins / portes dans les coins des salles.\n" +
                "- [ ] Génération de la map plus longue de temps en temps car nous avons augmenté sa taille et le nombre de salles présentes dans celle-ci.\n" +
                "\n" +
                "## <u>Pour run l'artefact : </u>\n" +
                "\n" +
                "Si vous êtes sur Windows avec wsl il vous faudra un serveur X graphique (ex: Xming) pour lancer le programme.\n" +
                "\n" +
                "CMD SHELL\n" +
                "\n" +
                "`java -jar RogueLikeMADY-1.0-SNAPSHOT.jar`\n" +
                "\n" +
                "<img src=\"https://i.imgur.com/d9cDliK.png\" alt=\"Logo\" width=48 height=48> <img src=\"https://i.imgur.com/fliRMaY.png\" alt=\"Logo\" width=48 height=48> <img src=\"https://i.imgur.com/zaCLOhf.png\" alt=\"Logo\" width=48 height=48>\n" +
                "\n" +
                "\n" +
                "\n");

        editor.setEditable(false);
        JScrollPane jsb = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        helpFrame.getContentPane().add(jsb);
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/MAD16x16.png")));
        helpFrame.setIconImage(img.getImage());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        helpFrame.setSize(screenSize.width / 2, screenSize.height / 2);

        helpFrame.setVisible(true);
    }

    public void renderMarkdown(String md) {
        String html = Processor.process(md);
        editor.setContentType("text/html");
        editor.setText(html);
    }
}
