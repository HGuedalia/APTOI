package dev.hguedalia.proteroc;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION_SETTINGS = 1;
    private static final int REQUEST_CODE_CSV_FILE = 2;
    private PacienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        getSupportActionBar().setTitle(R.string.tela_opcoes);

        dao = new PacienteDAO(this);

        // Verifica se a permissão já está concedida
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                // Chama a tela de configurações para permitir o gerenciamento de todos os arquivos
                openManageAllFilesSettings();
            }
        } else {
            // Neste caso, o aplicativo não está sendo executado no Android 12 ou posterior
            // Portanto, você pode usar a permissão de armazenamento antiga aqui, se necessário
        }

        ImageView princEscrever = findViewById(R.id.princEscrever);
        ImageView princConsultar = findViewById(R.id.princConsultar);
        ImageView princImpCSV = findViewById(R.id.princImpCsv);

        princEscrever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, CadastrarPacienteActivity.class);
                startActivity(intent);
            }
        });

        princConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, ListarFichasActivity.class);
                startActivity(intent);
            }
        });

        princImpCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }

    private void openManageAllFilesSettings() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE_PERMISSION_SETTINGS);
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, REQUEST_CODE_CSV_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PERMISSION_SETTINGS) {
            // Verifica se o usuário concedeu a permissão
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // Permissão concedida, você pode prosseguir com a lógica do seu aplicativo
                } else {
                    // Permissão não concedida, você pode exibir uma mensagem informando que a funcionalidade não está disponível
                }
            }
        } else if (requestCode == REQUEST_CODE_CSV_FILE) {
            if (resultCode == RESULT_OK) {
                Uri fileUri = data.getData();
                importCSVData(fileUri);
            }
        }
    }

    private void importCSVData(Uri fileUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(fileUri);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            List<Paciente> pacientes = new ArrayList<>();
            boolean isFirstLine = true; // Variável de controle para verificar se é a primeira linha do arquivo

            // Lê cada linha do arquivo CSV
            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Ignora a primeira linha e continua para a próxima linha
                }
                // Divide a linha em colunas usando o ponto-e-vírgula como separador
                String[] columns = line.split(",", -1);

                // Verifica se o número de colunas é válido
                if (columns.length >= 104) {
                    // Crie um objeto Paciente e defina os valores das colunas
                    Paciente paciente = new Paciente();
                    paciente.setNome(columns[0]);
                    paciente.setDataAtendimento(columns[1]);
                    paciente.setDataNascimento(columns[2]);
                    paciente.setIdade(columns[3]);
                    paciente.setLeito(columns[4]);
                    paciente.setProntuario(columns[5]);
                    paciente.setDiagnostico(columns[6]);
                    paciente.setHistOcup(columns[7]);
                    paciente.setIsolamento(columns[8]);
                    paciente.setDataAdmissao(columns[9]);
                    paciente.setFc(columns[10]);
                    paciente.setFr(columns[11]);
                    paciente.setSpo2(columns[12]);
                    paciente.setT(columns[13]);
                    paciente.setPa(columns[14]);
                    paciente.setDva(columns[15]);
                    paciente.setSedoanalgesia(columns[16]);
                    paciente.setAa(columns[17]);
                    paciente.setCaf(columns[18]);
                    paciente.setCatNasal(columns[19]);
                    paciente.setCanNasal(columns[20]);
                    paciente.setMascaraVenturi(columns[21]);
                    paciente.setElmo(columns[22]);
                    paciente.setTot(columns[23]);
                    paciente.setTqt(columns[24]);
                    paciente.setGlg(columns[25]);
                    paciente.setRass(columns[26]);
                    paciente.setLas(columns[27]);
                    paciente.setBo(columns[28]);
                    paciente.setAshw(columns[29]);
                    paciente.setAsia(columns[30]);
                    paciente.setBps(columns[31]);
                    paciente.setHemoglobina(columns[32]);
                    paciente.setLeucocitos(columns[33]);
                    paciente.setPlaquetas(columns[34]);
                    paciente.setTvp(columns[35]);
                    paciente.setTep(columns[36]);
                    paciente.setAptoMob(columns[37]);
                    paciente.setDecDorsal(columns[38]);
                    paciente.setDecLateral(columns[39]);
                    paciente.setSed(columns[40]);
                    paciente.setBip(columns[41]);
                    paciente.setAuM(columns[42]);
                    paciente.setAuRg(columns[43]);
                    paciente.setAuRa(columns[44]);
                    paciente.setAuV(columns[45]);
                    paciente.setAuRec(columns[46]);
                    paciente.setAuNat(columns[47]);
                    paciente.setAuTemp(columns[48]);
                    paciente.setAuResp(columns[49]);
                    paciente.setOlAs(columns[50]);
                    paciente.setOlAf(columns[51]);
                    paciente.setOlRec(columns[52]);
                    paciente.setOlNat(columns[53]);
                    paciente.setOlTemp(columns[54]);
                    paciente.setOlResp(columns[55]);
                    paciente.setViFei(columns[56]);
                    paciente.setViFee(columns[57]);
                    paciente.setViFai(columns[58]);
                    paciente.setViFae(columns[59]);
                    paciente.setViRec(columns[60]);
                    paciente.setViNat(columns[61]);
                    paciente.setViTemp(columns[62]);
                    paciente.setViResp(columns[63]);
                    paciente.setTaTl(columns[64]);
                    paciente.setTaTp(columns[65]);
                    paciente.setTaStf(columns[66]);
                    paciente.setTaStq(columns[67]);
                    paciente.setTaTr(columns[68]);
                    paciente.setTaEr(columns[69]);
                    paciente.setTaIec(columns[70]);
                    paciente.setTaRec(columns[71]);
                    paciente.setTaNat(columns[72]);
                    paciente.setTaTemp(columns[73]);
                    paciente.setTaResp(columns[74]);
                    paciente.setPrMal(columns[75]);
                    paciente.setPrMaa(columns[76]);
                    paciente.setPrMpa(columns[77]);
                    paciente.setPrCc(columns[78]);
                    paciente.setPrP(columns[79]);
                    paciente.setPrEstb(columns[80]);
                    paciente.setPrV(columns[81]);
                    paciente.setPrRec(columns[82]);
                    paciente.setPrNat(columns[83]);
                    paciente.setPrTemp(columns[84]);
                    paciente.setPrResp(columns[85]);
                    paciente.setCgS(columns[86]);
                    paciente.setCgE(columns[87]);
                    paciente.setCgM(columns[88]);
                    paciente.setCgRec(columns[89]);
                    paciente.setCgNat(columns[90]);
                    paciente.setCgTemp(columns[91]);
                    paciente.setCgResp(columns[92]);
                    paciente.setRoo(columns[93]);
                    paciente.setBbt(columns[94]);
                    paciente.setKbt(columns[95]);
                    paciente.setEmc(columns[96]);
                    paciente.setIse(columns[97]);
                    paciente.setFrk(columns[98]);
                    paciente.setJcb(columns[99]);
                    paciente.setAm(columns[100]);
                    paciente.setCc(columns[101]);
                    paciente.setCna(columns[102]);
                    paciente.setDdor(columns[103]);

                    // Adicione o objeto Paciente à lista de pacientes
                    pacientes.add(paciente);
                }
            }

            bufferedReader.close();

            // Insira os dados dos pacientes no banco de dados
            for (Paciente paciente : pacientes) {
                dao.inserir(paciente);
            }

            Toast.makeText(this, "DADOS IMPORTADOS COM SUCESSO", Toast.LENGTH_SHORT).show();

            // Redirecionar para a atividade 'ListarFichasActivity'
            Intent intent = new Intent(HomepageActivity.this, ListarFichasActivity.class);
            startActivity(intent);

        } catch (IOException e) {
            Toast.makeText(this, "ERRO AO IMPORTAR DADOS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}