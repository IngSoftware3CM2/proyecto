package paquete;

public class Lugar {

    private String nombre = null;
    private String descrip = null;
    private String precio = null;
    private String contacto = null;
    private String ubicacion = null;
    private String id_lugar = null;
    private String foto = null;

    public Lugar(String nombre, String descrip, String precio, String contacto, String ubicacion, String id_l, String foto) {
        this.contacto = contacto;
        this.descrip = descrip;
        this.nombre = nombre;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.id_lugar = id_l;
        this.foto = foto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescr() {
        return this.descrip;
    }

    public String getPrecio() {
        return this.precio;
    }

    public String getUbic() {
        return this.ubicacion;
    }

    public String getID_LT() {
        return this.id_lugar;
    }

    public String getFoto() {
        return this.foto;
    }
    public String getContact() {
        return this.contacto;
    }
}
