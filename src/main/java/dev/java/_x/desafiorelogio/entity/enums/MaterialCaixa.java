package dev.java._x.desafiorelogio.entity.enums;

public enum MaterialCaixa {
    ACO,TITANIO,RESINA,BRONZE,CERAMICA;

    //Traduz de inglês para português
    public static MaterialCaixa fromApi(String valor){
        if (valor == null || valor.isBlank()) return null;

        return switch (valor){
            case "steel" -> ACO;
            case "titanium" -> TITANIO;
            case "resin" -> RESINA;
            case "bronze" -> BRONZE;
            case "ceramic" -> CERAMICA;
            default -> throw new IllegalArgumentException("Tipo de material da caixa inválido");
        };
    }
    //Traduz de português para inglês
    public String toApí(){
        return switch (this){
            case ACO -> "steel";
            case TITANIO -> "titanium";
            case RESINA -> "resin";
            case BRONZE -> "bronze";
            case CERAMICA -> "ceramic";
        };
    }
}
