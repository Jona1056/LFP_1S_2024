package Utils;

public enum TipoError {
    LEXICO ("LEXICO"),
    SINTACTICO ("SINTACTICO");

    private String value;

    private TipoError(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}