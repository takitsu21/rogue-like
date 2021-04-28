package com.mady.utils;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WelcomeMenu {
    private final List<String> selector = new ArrayList<>(
            Arrays.asList("PLAY", "HELP", "QUIT")
    );
    public static int CURSOR = 0;

    public List<String> getSelector() {
        return selector;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String blankLineAdjust = "                                                       ";
        sb.append(
                "\n           /\\                         ███╗   ███╗ █████╗ ██████╗ ██╗   ██╗                       /\\\n" +
                        " _         )( ________________________████╗ ████║██╔══██╗██╔══██╗╚██╗ ██╔╝______________________ )(         _\n" +
                        "(_)///////(**)________________________██╔████╔██║███████║██║  ██║ ╚████╔╝_______________________(**)\\\\\\\\\\\\\\(_)\n" +
                        "           )(                         ██║╚██╔╝██║██╔══██║██║  ██║  ╚██╔╝                         )(\n" +
                        "           \\/                         ██║ ╚═╝ ██║██║  ██║██████╔╝   ██║                          \\/\n" +
                        "                                      ╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝    ╚═╝   \n");
        for (int i = 0; i < selector.size(); i++) {
            StringBuilder sbTmp = new StringBuilder();
            sbTmp.append(blankLineAdjust);
            if (i == CURSOR) {
                sbTmp = new StringBuilder(Ansi.colorize(sbTmp.append(">").append(selector.get(i)).append("<").toString(),
                        Attribute.BRIGHT_BLUE_TEXT()));
            } else {
                sbTmp.append(selector.get(i));
            }
            sb.append(sbTmp).append("\n");

        }
        return sb.toString();
    }
}
