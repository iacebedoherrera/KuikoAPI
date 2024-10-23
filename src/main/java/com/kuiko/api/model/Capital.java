package com.kuiko.api.model;

public enum Capital {
    PAIS_VASCO("COMUNIDAD AUTONOMA DEL PAIS VASCO", "Vitoria-Gasteiz"),
    CASTILLA_LA_MANCHA("COMUNIDAD AUTONOMA DE CASTILLA-LA MANCHA","Toledo"),
    COMUNIDAD_VALENCIANA("COMUNIDAD VALENCIANA", "Valencia"),
    ANDALUCIA("COMUNIDAD AUTONOMA DE ANDALUCIA", "Sevilla"),
    CASTILLA_Y_LEON("COMUNIDAD AUTONOMA DE CASTILLA Y LEON", "Valladolid"),
    EXTREMADURA("COMUNIDAD AUTONOMA DE EXTREMADURA", "Mérida"),
    BALEARES("COMUNIDAD AUTONOMA DE ILLES BALEARS", "Palma de Mallorca"),
    CATALUÑA("COMUNIDAD AUTONOMA DE CATALUÑA", "Barcelona"),
    GALICIA("COMUNIDAD AUTONOMA DE GALICIA", "Santiago de Compostela"),
    ARAGON("COMUNIDAD AUTONOMA DE ARAGON", "Zaragoza"),
    LA_RIOJA("COMUNIDAD AUTONOMA DE LA RIOJA", "Logroño"),
    COMUNIDAD_DE_MADRID("COMUNIDAD DE MADRID", "Madrid"),
    MURCIA("REGION DE MURCIA", "Murcia"),
    NAVARRA("COMUNIDAD FORAL DE NAVARRA", "Pamplona"),
    ASTURIAS("PRINCIPADO DE ASTURIAS", "Oviedo"),
    CANARIAS("COMUNIDAD AUTONOMA DE CANARIAS", "Las Palmas de Gran Canaria"),
    CANTABRIA("COMUNIDAD AUTONOMA DE CANTABRIA", "Santander"),
    CEUTA("CIUDAD DE CEUTA", "Ceuta"),
    MEILLA("CIUDAD DE MELILLA", "Melilla");


    private final String nombre;
    private final String capital;

    Capital(String nombre, String capital) {
        this.nombre = nombre;
        this.capital = capital;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCapital() {
        return capital;
    }

    public static String valueOfNombre(String nombre) {
        for (Capital capital : Capital.values()) {
            if (capital.getNombre().equalsIgnoreCase(nombre)) {
                return capital.getCapital();
            }
        }
        throw new IllegalArgumentException("Comunidad autónoma no encontrada: " + nombre);
    }
}
