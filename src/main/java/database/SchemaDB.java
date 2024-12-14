package database;

public interface SchemaDB {

    String DB_NAME = "concesionario";
    String TAB_CH = "coches";
    String COL_ID = "id";
    String COL_CH_MAT = "matricula";
    String COL_CH_MAR = "marca";
    String COL_CH_MOD = "modelo";
    String COL_CH_COL = "color";
    String TAB_PAS = "pasajeros";
    String COL_NOM = "nombre";
    String COL_ED = "edad";
    String COL_PES = "peso";
    String FK_COCH = "pasajeros_coches_FK";
}
