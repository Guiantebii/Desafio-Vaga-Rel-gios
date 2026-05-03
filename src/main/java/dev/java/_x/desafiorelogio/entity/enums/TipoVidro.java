package dev.java._x.desafiorelogio.entity.enums;


public enum TipoVidro {
    MINERAL,SAFIRA,ACRILICO;

    //Traduz de inglês para português
    public static TipoVidro fromApi(String valor){
        if(valor == null || valor.isBlank()) return null;
        return switch (valor){
            case "mineral" -> MINERAL;
            case "sapphire" -> SAFIRA;
            case "acrylic" -> ACRILICO;
            default -> throw new IllegalArgumentException("Tipo de vidro inválido" + valor);
        };
    }

    //Traduz de português para inglês
    public String toApi(){
        return switch (this){
            case MINERAL -> "mineral";
            case SAFIRA -> "sapphire";
            case ACRILICO -> "acrylic";
        };
    }
}
