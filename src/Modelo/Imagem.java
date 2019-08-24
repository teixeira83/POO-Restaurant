package Modelo;


public class Imagem {

    final String caminhoPasta = "../POO-Restaurant/src/Imagens/";

    public String montarCaminho(String s){
        return caminhoPasta + s ;
    }

}
