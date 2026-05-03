package dev.java._x.desafiorelogio.entity.enums;

public enum TipoMovimento {

    QUARTZ, AUTOMATICO, MANUAL;

    //Traduz de inglês para português
    public static TipoMovimento fromApi(String valor) {
        if (valor == null || valor.isBlank()) return null;
        return switch (valor) {
            case "quartz" -> QUARTZ;
            case "automatic" -> AUTOMATICO;
            case "manual" -> MANUAL;
            default -> throw new IllegalArgumentException("Tipo de movimento inválido" + valor);
        };
    }

    //Traduz de português para inglês
    public String toApi(){
        return switch (this){
            case QUARTZ -> "quartz";
            case AUTOMATICO -> "automatic";
            case MANUAL -> "manual";
        };
    }
}
