package proyecto;

public class Residente {
    private String nombre;
    private String numeroResidencia;
    private String direccionResidencia;


    // *contructor Residente */
    public Residente(String direccionResidencia,String nombre, String numeroResidencia) {
        this.nombre = nombre;
        this.numeroResidencia = numeroResidencia;
    }

    // *getter de nombre */
    public String getNombre() {
        return nombre;
    }

    // *getter numeroResidencia */
    public String getNumeroResidencia() {
        return numeroResidencia;
    }

    //* getter direccionResidencia */
    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    // *setter de nombre */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // *setter numeroResidencia */
    public void setNumeroResidencia(String numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }

    // *setter direccionResidencia */
    public void setDireccionResidencia (String direccionResidencia){
        this.direccionResidencia = direccionResidencia;
    }

    // Método toString para mostrar la información del residente
    @Override
    public String toString() {
        return "Residente [nombre=" + nombre + ", numeroResidencia=" + numeroResidencia + "]";
    }
    //                                                           //


}
