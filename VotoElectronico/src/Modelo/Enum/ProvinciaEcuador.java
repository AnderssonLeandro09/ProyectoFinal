/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Modelo.Enum;

/**
 *
 * @author Stali
 */
public enum ProvinciaEcuador {
    AZUAY("Azuay", "Cuenca", "Gualaceo", "Paute"),
    BOLIVAR("Bolívar", "Guaranda", "Chillanes", "Echeandía"),
    CANAR("Cañar", "Azogues", "Biblián", "Cañar"),
    CARCHI("Carchi", "Tulcán", "Espejo", "Montúfar"),
    CHIMBORAZO("Chimborazo", "Riobamba", "Alausí", "Chambo"),
    COTOPAXI("Cotopaxi", "Latacunga", "La Maná", "Salcedo"),
    EL_ORO("El Oro", "Machala", "Santa Rosa", "Pasaje"),
    ESMERALDAS("Esmeraldas", "Esmeraldas", "Atacames", "San Lorenzo"),
    GALAPAGOS("Galápagos", "Puerto Baquerizo Moreno", "Santa Cruz", "San Cristóbal"),
    GUAYAS("Guayas", "Guayaquil", "Durán", "Daule"),
    IMBABURA("Imbabura", "Ibarra", "Otavalo", "Cotacachi"),
    LOJA("Loja", "Loja", "Malacatos", "Catamayo"),
    LOS_RIOS("Los Ríos", "Babahoyo", "Quevedo", "Vinces"),
    MANABI("Manabí", "Portoviejo", "Manta", "Jipijapa"),
    MORONA_SANTIAGO("Morona Santiago", "Macas", "Gualaquiza", "Sucúa"),
    NAPO("Napo", "Tena", "El Chaco", "Archidona"),
    ORELLANA("Orellana", "Orellana", "La Joya de los Sachas", "Loreto"),
    PASTAZA("Pastaza", "Puyo", "Santa Clara", "Mera"),
    PICHINCHA("Pichincha", "Quito", "Cayambe", "Machachi"),
    SANTA_ELENA("Santa Elena", "Santa Elena", "Salinas", "La Libertad"),
    SANTO_DOMINGO("Santo Domingo de los Tsáchilas", "Santo Domingo", "La Concordia", "Quevedo"),
    SUCUMBIOS("Sucumbíos", "Nueva Loja (Lago Agrio)", "Shushufindi", "Cuyabeno"),
    TUNGURAHUA("Tungurahua", "Ambato", "Baños", "Pelileo");

    private final String nombreProvincia;
    private final String[] cantones;

    ProvinciaEcuador(String nombreProvincia, String... cantones) {
        this.nombreProvincia = nombreProvincia;
        this.cantones = cantones;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public String[] getCantones() {
        return cantones;
    }
}