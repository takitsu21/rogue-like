public class Salle {
    int largeur;
    int hauteur;
    int monster_number;
    int object_number;
    Case[][] representation;
    Salle(int largeur , int hauteur   ){
        this.largeur = largeur;
        this.hauteur = hauteur;
        representation = new Case[largeur][hauteur];
    }




    void create_salle(int l, int h){
        for(int j = 0 ; j < largeur; j++){
            representation[j][0] = new Case('#',null);
        }
        for(int i = 1 ; i < hauteur-1; i++){
            representation[0][i] = new Case('#',null);
            for(int k = 1; k < largeur-1; k ++){
                representation[k][i] = new Case(" ",null);
            }
            representation[largeur][i] = new Case('#',null);
        }
        for(int j = 0 ; j < largeur; j++){
            representation[j][hauteur] = new Case('#',null);
        }


    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < largeur; i++){
            for(int j = 0 ; j < hauteur; j ++ ){
                sb.append(representation[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
