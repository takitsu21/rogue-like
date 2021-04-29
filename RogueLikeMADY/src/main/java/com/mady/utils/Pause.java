package com.mady.utils;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pause {
    private final List<String> liste = new ArrayList<>(Arrays.asList("Resume", "Restart", "Quit"));
    private int selection;

    public Pause() {
        selection = 0;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public List<String> getListe() {
        return liste;
    }

    public String toString(Case[][] map) {
        StringBuilder sb = new StringBuilder();
        int height = map.length;
        int width = map[0].length;
        int heightPause = height / 3;
        int widthPause = width / 3;
        String[][] pause_rep = new String[heightPause][widthPause];
        String[][] finalRep = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                finalRep[i][j] = map[i][j].toString();

            }

        }
        String pREp = "--PAUSE--";
        String resRep = "RESUME";
        String restartREp = "RESTART";
        String quitrep = "QUIT";
        switch (selection) {
            case 0:
                resRep = String.format(">%s<", resRep);
                break;
            case 1:
                restartREp = String.format(">%s<", restartREp);

                break;
            case 2:
                quitrep = String.format(">%s<", quitrep);

                break;
            default:
                break;

        }
        int positionPause = (widthPause - pREp.length()) / 2;
        int positionResume = (widthPause - resRep.length()) / 2;
        int positionRestart = (widthPause - restartREp.length()) / 2;
        int positionQuit = (widthPause - quitrep.length()) / 2;

        //création du carré au milieu de la map
        for (int i = 0; i < heightPause; i++) {
            for (int j = 0; j < widthPause; j++) {
                if (i == 0 || i == heightPause - 1 || j == 0 || j == widthPause - 1) {
                    pause_rep[i][j] = Ansi.colorize("#", Attribute.BRIGHT_BLACK_TEXT(),
                            Attribute.BRIGHT_BLACK_BACK());

                    continue;

                }
                if (i == 1) {
                    if (j >= positionPause && j < pREp.length() + positionPause) {

                        pause_rep[i][j] = String.valueOf(pREp.charAt(j - (positionPause - 1) - 1));


                    } else {
                        pause_rep[i][j] = " ";

                    }

                    continue;

                }
                if (i == 2) {
                    if (j >= positionResume && j < resRep.length() + positionResume) {
                        String text = String.valueOf(resRep.charAt(j - (positionResume - 1) - 1));
                        if (selection == 0) {
                            pause_rep[i][j] = Ansi.colorize(text, Attribute.BLUE_TEXT());
                        } else {
                            pause_rep[i][j] = text;

                        }

                    } else {
                        pause_rep[i][j] = " ";

                    }
                    continue;


                }
                if (i == 3) {
                    if (j >= positionRestart && j < restartREp.length() + positionRestart) {
                        String text = String.valueOf(restartREp.charAt(j - (positionRestart - 1) - 1));
                        if (selection == 1) {
                            pause_rep[i][j] = Ansi.colorize(text, Attribute.BLUE_TEXT());
                        } else {
                            pause_rep[i][j] = text;

                        }

                    } else {
                        pause_rep[i][j] = " ";

                    }
                    continue;


                }
                if (i == 4) {
                    if (j >= positionQuit && j < quitrep.length() + positionQuit) {
                        String text = String.valueOf(quitrep.charAt(j - (positionQuit - 1) - 1));
                        if (selection == 2) {
                            pause_rep[i][j] = Ansi.colorize(text, Attribute.BLUE_TEXT());
                        } else {
                            pause_rep[i][j] = text;

                        }
                    } else {
                        pause_rep[i][j] = " ";

                    }
                    continue;

                }
                pause_rep[i][j] = " ";


            }


        }
        for (int i = 0; i < heightPause; i++) {
            for (int j = 0; j < widthPause; j++) {

                finalRep[i + heightPause][j + widthPause] = pause_rep[i][j];
            }

        }
        for (int i = 0; i <= width + 1; i++) {
            sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT()));
        }
        sb.append("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == height - 1 && j == 0) {
                    sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT()));
                } else if (j == 0) {
                    sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT()));
                    sb.append(finalRep[i][j]);
                } else if (i == height - 1) {
                    sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT()));
                } else {
                    sb.append(finalRep[i][j]);
                }
            }
            if (i == height - 1) {
                sb.append(Ansi.colorize("\"\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT())).append("\n");
            } else {
                sb.append(Ansi.colorize("\"", Attribute.BLACK_BACK(), Attribute.BLACK_TEXT())).append("\n");
            }
        }


        return sb.toString();
    }


}
