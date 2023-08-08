package dev.hguedalia.proteroc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import org.apache.commons.text.StringEscapeUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListarFichasActivity extends AppCompatActivity {

    private ListView listView;
    private PacienteDAO dao;
    private List<Paciente> pacientes;
    private final List<Paciente> pacientesFiltrados = new ArrayList<>();
    private List<Paciente> pacienteFicha;
    private final List<Paciente> pacienteFichaFiltrado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_fichas);
        getSupportActionBar().setTitle(R.string.listar_paciente);

        listView = findViewById(R.id.lista_pacientes);
        dao = new PacienteDAO(this);
        pacientes = dao.obterTodos();
        pacientesFiltrados.addAll(pacientes);
        pacienteFicha = dao.obterFicha();
        pacienteFichaFiltrado.addAll(pacienteFicha);

        ArrayAdapter<Paciente> adaptador = new ArrayAdapter<Paciente>(this, android.R.layout.simple_list_item_1, pacientesFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Paciente pacienteSelecionado = pacientesFiltrados.get(position);

                Intent intent = new Intent(ListarFichasActivity.this, DetalharFichaActivity.class);
                intent.putExtra("id", pacienteSelecionado.getId());

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_listar, menu);

        SearchView sv = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.app_bar_search));

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraPaciente(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Verifica se o item selecionado é o MenuItem "expCSV"
        if (item.getItemId() == R.id.expCSV) {
            expCSV();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_opcoes, menu);
    }

    public void procuraPaciente(String leito) {
        pacientesFiltrados.clear();
        for (Paciente p : pacientes) {
            if (p.getLeito().toLowerCase().contains(leito.toLowerCase())) {
                pacientesFiltrados.add(p);
            }
        }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Paciente pacienteExcluir = pacientesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do registro?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pacientesFiltrados.remove(pacienteExcluir);
                        pacientes.remove(pacienteExcluir);
                        dao.excluir(pacienteExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void homepage(MenuItem item) {
        Intent it = new Intent(this, HomepageActivity.class);
        startActivity(it);
    }

    public void cadastrar(MenuItem item) {
        Intent it = new Intent(this, CadastrarPacienteActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Paciente pacienteAtualizar = pacientesFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastrarPacienteActivity.class);
        it.putExtra("paciente", pacienteAtualizar);
        startActivity(it);
    }

    public void atender(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Paciente pacienteAtender = pacienteFichaFiltrado.get(menuInfo.position);
        Intent it = new Intent(this, CadastrarPacienteActivity.class);
        it.putExtra("paciente", pacienteAtender);
        startActivity(it);
    }

    private void expCSV() {
        // Obter a lista de pacientes do banco de dados
        PacienteDAO dao = new PacienteDAO(this);
        List<Paciente> pacientes = dao.obterTodos();

        // Verificar se a lista de pacientes não está vazia
        if (pacientes.isEmpty()) {
            Toast.makeText(this, "SEM DADOS PARA EXPORTAR", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criação do arquivo CSV
        String nomeArquivo = "PacientesAPTOI.csv";
        File pastaDownload = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "BackupAPTOI");

        // Verifica se a pasta de downloads existe
        if (!pastaDownload.exists()) {
            if (!pastaDownload.mkdirs()) {
                Toast.makeText(this, "FALHA AO CRIAR A PASTA DE DOWNLOAD", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        File arquivoCSV = new File(pastaDownload, nomeArquivo);

        try {
            // Cria o escritor de arquivo CSV
            FileWriter writer = new FileWriter(arquivoCSV);

            // Escreve o cabeçalho do CSV
            writer.append("Nome; Data de atendimento; Data de nascimento; Idade; Leito; Prontuario; Diagnostico; Historico ocupacional; Isolamento; Data de admissao; Frequencia cardiaca; Frequencia respiratoria; Saturacao; Temperatura; Pressao arterial; Droga vasoativa; Sedoanalgesia; Ar ambiente; Canula de alto fluxo; Cateter nasal; Canula nasal; Mascara de Venturi; Elmo; Tubo oreotraqueal; Tubo de traqueostomia; Escala de Glasgow; Escala de Richmond; Escala Rancho los amigos; Escala de Borg; Escala de Ashworth; Escala ASIA; Escala BPS; Hemoglobina; Leucocitos; Plaquetas; Trombose venosa profunda; Tromboembolismo pulmonar; Apto a mobilizacao; Decubito dorsal; Decubito lateral; Sedestar; Bipedestar; AU Musicalidade; AU Ruido grave; AU Ruido agudo; AU Verbalizacao; AU Recurso; AU Natureza; AU Tempo; AU Resposta; OL Aroma suave; OL Aroma forte; OL Recurso; OL Natureza; OL Tempo; OL Resposta; VI Focalizacao estreita interna; VI Focalizacao estreita externa; VI Focalizacao ampla interna; VI Focalizacao ampla externa; VI Recurso; VI Natureza; VI Tempo; VI Resposta; TA Toque leve; TA Toque de pressao; TA Sensacao termica de frio; TA Sensacao termica de calor; TA Toque rugoso; TA Escovagem rapida; TA Inducao a exploracao corporal; TA Recurso; TA Natureza; TA Tempo; TA Resposta; PR Mobilidade ativa livre; PR Mobilidade ativa assistida; PR Mobilidade passiva; PR Co-contracao; PR Posicionamento; PR Estabilizacao; PR Vibracao; PR Recurso; PR Natureza; PR Tempo; PR Resposta; CG Sequenciacao; CG Elaboracao; CG Mentalizacao; CG Recurso; CG Natureza; CG Tempo; CG Resposta; Metodo de Rood; Metodo de Bobath; Metodo de Kabat; Estimulacao multissensorial controlada; Integracao Sensorial; Metodo de Frenkel; Relaxamento progressivo de Jacobson; Amplitude de movimento; Cognitivo comportamental; Cinesioatividade; Diario da dor\n");

            // Escreve os dados dos pacientes no CSV
            for (Paciente paciente : pacientes) {
                writer.append(escapeField(paciente.getNome())).append(";")
                        .append(escapeField(paciente.getDataAtendimento())).append(";")
                        .append(escapeField(paciente.getDataNascimento())).append(";")
                        .append(escapeField(paciente.getIdade())).append(";")
                        .append(escapeField(paciente.getLeito())).append(";")
                        .append(escapeField(paciente.getProntuario())).append(";")
                        .append(escapeField(paciente.getDiagnostico())).append(";")
                        .append(escapeField(paciente.getHistOcup())).append(";")
                        .append(escapeField(paciente.getIsolamento())).append(";")
                        .append(escapeField(paciente.getDataAdmissao())).append(";")
                        .append(escapeField(paciente.getFc())).append(";")
                        .append(escapeField(paciente.getFr())).append(";")
                        .append(escapeField(paciente.getSpo2())).append(";")
                        .append(escapeField(paciente.getT())).append(";")
                        .append(escapeField(paciente.getPa())).append(";")
                        .append(escapeField(paciente.getDva())).append(";")
                        .append(escapeField(paciente.getSedoanalgesia())).append(";")
                        .append(escapeField(paciente.getAa())).append(";")
                        .append(escapeField(paciente.getCaf())).append(";")
                        .append(escapeField(paciente.getCatNasal())).append(";")
                        .append(escapeField(paciente.getCanNasal())).append(";")
                        .append(escapeField(paciente.getMascaraVenturi())).append(";")
                        .append(escapeField(paciente.getElmo())).append(";")
                        .append(escapeField(paciente.getTot())).append(";")
                        .append(escapeField(paciente.getTqt())).append(";")
                        .append(escapeField(paciente.getGlg())).append(";")
                        .append(escapeField(paciente.getRass())).append(";")
                        .append(escapeField(paciente.getLas())).append(";")
                        .append(escapeField(paciente.getBo())).append(";")
                        .append(escapeField(paciente.getAshw())).append(";")
                        .append(escapeField(paciente.getAsia())).append(";")
                        .append(escapeField(paciente.getBps())).append(";")
                        .append(escapeField(paciente.getHemoglobina())).append(";")
                        .append(escapeField(paciente.getLeucocitos())).append(";")
                        .append(escapeField(paciente.getPlaquetas())).append(";")
                        .append(escapeField(paciente.getTvp())).append(";")
                        .append(escapeField(paciente.getTep())).append(";")
                        .append(escapeField(paciente.getAptoMob())).append(";")
                        .append(escapeField(paciente.getDecDorsal())).append(";")
                        .append(escapeField(paciente.getDecLateral())).append(";")
                        .append(escapeField(paciente.getSed())).append(";")
                        .append(escapeField(paciente.getBip())).append(";")
                        .append(escapeField(paciente.getAuM())).append(";")
                        .append(escapeField(paciente.getAuRg())).append(";")
                        .append(escapeField(paciente.getAuRa())).append(";")
                        .append(escapeField(paciente.getAuV())).append(";")
                        .append(escapeField(paciente.getAuRec())).append(";")
                        .append(escapeField(paciente.getAuNat())).append(";")
                        .append(escapeField(paciente.getAuTemp())).append(";")
                        .append(escapeField(paciente.getAuResp())).append(";")
                        .append(escapeField(paciente.getOlAs())).append(";")
                        .append(escapeField(paciente.getOlAf())).append(";")
                        .append(escapeField(paciente.getOlRec())).append(";")
                        .append(escapeField(paciente.getOlNat())).append(";")
                        .append(escapeField(paciente.getOlTemp())).append(";")
                        .append(escapeField(paciente.getOlResp())).append(";")
                        .append(escapeField(paciente.getViFei())).append(";")
                        .append(escapeField(paciente.getViFee())).append(";")
                        .append(escapeField(paciente.getViFai())).append(";")
                        .append(escapeField(paciente.getViFae())).append(";")
                        .append(escapeField(paciente.getViRec())).append(";")
                        .append(escapeField(paciente.getViNat())).append(";")
                        .append(escapeField(paciente.getViTemp())).append(";")
                        .append(escapeField(paciente.getViResp())).append(";")
                        .append(escapeField(paciente.getTaTl())).append(";")
                        .append(escapeField(paciente.getTaTp())).append(";")
                        .append(escapeField(paciente.getTaStf())).append(";")
                        .append(escapeField(paciente.getTaStq())).append(";")
                        .append(escapeField(paciente.getTaTr())).append(";")
                        .append(escapeField(paciente.getTaEr())).append(";")
                        .append(escapeField(paciente.getTaIec())).append(";")
                        .append(escapeField(paciente.getTaRec())).append(";")
                        .append(escapeField(paciente.getTaNat())).append(";")
                        .append(escapeField(paciente.getTaTemp())).append(";")
                        .append(escapeField(paciente.getTaResp())).append(";")
                        .append(escapeField(paciente.getPrMal())).append(";")
                        .append(escapeField(paciente.getPrMaa())).append(";")
                        .append(escapeField(paciente.getPrMpa())).append(";")
                        .append(escapeField(paciente.getPrCc())).append(";")
                        .append(escapeField(paciente.getPrP())).append(";")
                        .append(escapeField(paciente.getPrEstb())).append(";")
                        .append(escapeField(paciente.getPrV())).append(";")
                        .append(escapeField(paciente.getPrRec())).append(";")
                        .append(escapeField(paciente.getPrNat())).append(";")
                        .append(escapeField(paciente.getPrTemp())).append(";")
                        .append(escapeField(paciente.getPrResp())).append(";")
                        .append(escapeField(paciente.getCgS())).append(";")
                        .append(escapeField(paciente.getCgE())).append(";")
                        .append(escapeField(paciente.getCgM())).append(";")
                        .append(escapeField(paciente.getCgRec())).append(";")
                        .append(escapeField(paciente.getCgNat())).append(";")
                        .append(escapeField(paciente.getCgTemp())).append(";")
                        .append(escapeField(paciente.getCgResp())).append(";")
                        .append(escapeField(paciente.getRoo())).append(";")
                        .append(escapeField(paciente.getBbt())).append(";")
                        .append(escapeField(paciente.getKbt())).append(";")
                        .append(escapeField(paciente.getEmc())).append(";")
                        .append(escapeField(paciente.getIse())).append(";")
                        .append(escapeField(paciente.getFrk())).append(";")
                        .append(escapeField(paciente.getJcb())).append(";")
                        .append(escapeField(paciente.getAm())).append(";")
                        .append(escapeField(paciente.getCc())).append(";")
                        .append(escapeField(paciente.getCna())).append(";")
                        .append(escapeField(paciente.getDdor())).append(";")
                        .append("\n");
            }

            // Fecha o escritor de arquivo
            writer.flush();
            writer.close();

            Toast.makeText(this, "DADOS EXPORTADOS COM SUCESSO", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("ExpCSV", "ERRO AO EXPORTAR DADOS: " + e.getMessage());
            Toast.makeText(this, "FALHA AO EXPORTAR OS DADOS", Toast.LENGTH_SHORT).show();
        }
    }

    private static String escapeField(String field) {
        // Utiliza o StringEscapeUtils da biblioteca Apache Commons Text para escapar vírgulas e aspas
        return StringEscapeUtils.escapeCsv(field);
    }

    @Override
    public void onResume() {
        super.onResume();
        pacientes = dao.obterTodos();
        pacientesFiltrados.clear();
        pacientesFiltrados.addAll(pacientes);
        pacienteFicha = dao.obterFicha();
        pacienteFichaFiltrado.clear();
        pacienteFichaFiltrado.addAll(pacienteFicha);
        listView.invalidateViews();
    }

}