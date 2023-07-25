package dev.hguedalia.proteroc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "MeusPacientes.sqlite";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE paciente (\n" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                " nome TEXT,\n" +
                " dataAtendimento TEXT,\n" +
                " dataNascimento TEXT,\n" +
                " idade TEXT,\n" +
                " leito TEXT,\n" +
                " prontuario TEXT,\n" +
                " diagnostico TEXT,\n" +
                " histOcup TEXT,\n" +
                " isolamento TEXT,\n" +
                " dataAdmissao TEXT,\n" +
                " fc TEXT,\n" +
                " fr TEXT,\n" +
                " spo2 TEXT,\n" +
                " t TEXT,\n" +
                " pa TEXT,\n" +
                " dva TEXT,\n" +
                " sedoanalgesia TEXT,\n" +
                " aa TEXT,\n" +
                " caf TEXT,\n" +
                " catNasal TEXT,\n" +
                " canNasal TEXT,\n" +
                " mascaraVenturi TEXT,\n" +
                " elmo TEXT,\n" +
                " tot TEXT,\n" +
                " tqt TEXT,\n" +
                " glg TEXT,\n" +
                " rass TEXT,\n" +
                " las TEXT,\n" +
                " bo TEXT,\n" +
                " ashw TEXT,\n" +
                " asia TEXT,\n" +
                " bps TEXT,\n" +
                " hemoglobina TEXT,\n" +
                " leucocitos TEXT,\n" +
                " plaquetas TEXT,\n" +
                " tvp TEXT,\n" +
                " tep TEXT,\n" +
                " aptoMob TEXT,\n" +
                " decDorsal TEXT,\n" +
                " decLateral TEXT,\n" +
                " sed TEXT,\n" +
                " bip TEXT,\n" +
                " auM TEXT,\n" +
                " auRg TEXT,\n" +
                " auRa TEXT,\n" +
                " auV TEXT,\n" +
                " auRec TEXT,\n" +
                " auNat TEXT,\n" +
                " auTemp TEXT,\n" +
                " auResp TEXT,\n" +
                " olAs TEXT,\n" +
                " olAf TEXT,\n" +
                " olRec TEXT,\n" +
                " olNat TEXT,\n" +
                " olTemp TEXT,\n" +
                " olResp TEXT,\n" +
                " viFei TEXT,\n" +
                " viFee TEXT,\n" +
                " viFai TEXT,\n" +
                " viFae TEXT,\n" +
                " viRec TEXT,\n" +
                " viNat TEXT,\n" +
                " viTemp TEXT,\n" +
                " viResp TEXT,\n" +
                " taTl TEXT,\n" +
                " taTp TEXT,\n" +
                " taStf TEXT,\n" +
                " taStq TEXT,\n" +
                " taTr TEXT,\n" +
                " taEr TEXT,\n" +
                " taIec TEXT,\n" +
                " taRec TEXT,\n" +
                " taNat TEXT,\n" +
                " taTemp TEXT,\n" +
                " taResp TEXT,\n" +
                " prMal TEXT,\n" +
                " prMaa TEXT,\n" +
                " prMpa TEXT,\n" +
                " prCc TEXT,\n" +
                " prP TEXT,\n" +
                " prEstb TEXT,\n" +
                " prV TEXT,\n" +
                " prRec TEXT,\n" +
                " prNat TEXT,\n" +
                " prTemp TEXT,\n" +
                " prResp TEXT,\n" +
                " cgS TEXT,\n" +
                " cgE TEXT,\n" +
                " cgM TEXT,\n" +
                " cgRec TEXT,\n" +
                " cgNat TEXT,\n" +
                " cgTemp TEXT,\n" +
                " cgResp TEXT,\n" +
                " roo TEXT,\n" +
                " bbt TEXT,\n" +
                " kbt TEXT,\n" +
                " emc TEXT,\n" +
                " ise TEXT,\n" +
                " frk TEXT,\n" +
                " jcb TEXT,\n" +
                " am TEXT,\n" +
                " cc TEXT,\n" +
                " cna TEXT,\n" +
                " ddor TEXT \n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}