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
            writer.append("Nome, Data de atendimento, Data de nascimento, Idade, Leito, Prontuario, Diagnostico, Historico ocupacional, Isolamento, Data de admissao, FC, FR, SpO2, T, PA, DVA, Sedoanalgesia, AA, CAF, Cat nasal, Can nasal, M Venturi, Elmo, TOT, TQT, GLG, RASS, LAS, BO, ASHW, ASIA, BPS, Hemoglobina, Leucocitos, Plaquetas, TVP, TEP, Apto para mobilizacao, Dec dorsal, Dec lateral, SED, BIP, Au M, Au RG, Au RA, Au V, Au Rec, Au Nat, Au Temp, Au Resp, Ol AS, Ol AF, Ol Rec, Ol Nat, Ol Temp, Ol Resp, Vi FEI, Vi FEE, Vi FAI, Vi FAE, Vi Rec, Vi Nat, Vi Temp, Vi Resp, Ta TI, Ta TP, Ta STF, Ta STQ, Ta TR, Ta ER, Ta Iec, Ta Rec, Ta Nat, Ta Temp, Ta Resp, Pr MAL, Pr MAA, Pr MPA, Pr CC, Pr P, Pr ESTB, Pr V, Pr Rec, Pr Nat, Pr Temp, Pr Resp, Cg  S, Cg  E, Cg  M, Cg  Rec, Cg  Nat, Cg  Temp, Cg  Resp, ROO, BBT, KBT, EMC, IS, FRK, JCB, AM, CC, CNA, DDOR\n");

            // Escreve os dados dos pacientes no CSV
            for (Paciente paciente : pacientes) {
                writer.append(paciente.getNome()).append(",")
                        .append(paciente.getDataAtendimento())
                        .append(",")
                        .append(paciente.getDataNascimento())
                        .append(",")
                        .append(paciente.getIdade())
                        .append(",")
                        .append(paciente.getLeito())
                        .append(",")
                        .append(paciente.getProntuario())
                        .append(",")
                        .append(paciente.getDiagnostico())
                        .append(",")
                        .append(paciente.getHistOcup())
                        .append(",")
                        .append(paciente.getIsolamento())
                        .append(",")
                        .append(paciente.getDataAdmissao())
                        .append(",")
                        .append(paciente.getFc())
                        .append(",")
                        .append(paciente.getFr())
                        .append(",")
                        .append(paciente.getSpo2())
                        .append(",")
                        .append(paciente.getT())
                        .append(",")
                        .append(paciente.getPa())
                        .append(",")
                        .append(paciente.getDva())
                        .append(",")
                        .append(paciente.getSedoanalgesia())
                        .append(",")
                        .append(paciente.getAa())
                        .append(",")
                        .append(paciente.getCaf())
                        .append(",")
                        .append(paciente.getCatNasal())
                        .append(",")
                        .append(paciente.getCanNasal())
                        .append(",")
                        .append(paciente.getMascaraVenturi())
                        .append(",")
                        .append(paciente.getElmo())
                        .append(",")
                        .append(paciente.getTot())
                        .append(",")
                        .append(paciente.getTqt())
                        .append(",")
                        .append(paciente.getGlg())
                        .append(",")
                        .append(paciente.getRass())
                        .append(",")
                        .append(paciente.getLas())
                        .append(",")
                        .append(paciente.getBo())
                        .append(",")
                        .append(paciente.getAshw())
                        .append(",")
                        .append(paciente.getAsia())
                        .append(",")
                        .append(paciente.getBps())
                        .append(",")
                        .append(paciente.getHemoglobina())
                        .append(",")
                        .append(paciente.getLeucocitos())
                        .append(",")
                        .append(paciente.getPlaquetas())
                        .append(",")
                        .append(paciente.getTvp())
                        .append(",")
                        .append(paciente.getTep())
                        .append(",")
                        .append(paciente.getAptoMob())
                        .append(",")
                        .append(paciente.getDecDorsal())
                        .append(",")
                        .append(paciente.getDecLateral())
                        .append(",")
                        .append(paciente.getSed())
                        .append(",")
                        .append(paciente.getBip())
                        .append(",")
                        .append(paciente.getAuM())
                        .append(",")
                        .append(paciente.getAuRg())
                        .append(",")
                        .append(paciente.getAuRa())
                        .append(",")
                        .append(paciente.getAuV())
                        .append(",")
                        .append(paciente.getAuRec())
                        .append(",")
                        .append(paciente.getAuNat())
                        .append(",")
                        .append(paciente.getAuTemp())
                        .append(",")
                        .append(paciente.getAuResp())
                        .append(",")
                        .append(paciente.getOlAs())
                        .append(",")
                        .append(paciente.getOlAf())
                        .append(",")
                        .append(paciente.getOlRec())
                        .append(",")
                        .append(paciente.getOlNat())
                        .append(",")
                        .append(paciente.getOlTemp())
                        .append(",")
                        .append(paciente.getOlResp())
                        .append(",")
                        .append(paciente.getViFei())
                        .append(",")
                        .append(paciente.getViFee())
                        .append(",")
                        .append(paciente.getViFai())
                        .append(",")
                        .append(paciente.getViFae())
                        .append(",")
                        .append(paciente.getViRec())
                        .append(",")
                        .append(paciente.getViNat())
                        .append(",")
                        .append(paciente.getViTemp())
                        .append(",")
                        .append(paciente.getViResp())
                        .append(",")
                        .append(paciente.getTaTl())
                        .append(",")
                        .append(paciente.getTaTp())
                        .append(",")
                        .append(paciente.getTaStf())
                        .append(",")
                        .append(paciente.getTaStq())
                        .append(",")
                        .append(paciente.getTaTr())
                        .append(",")
                        .append(paciente.getTaEr())
                        .append(",")
                        .append(paciente.getTaIec())
                        .append(",")
                        .append(paciente.getTaRec())
                        .append(",")
                        .append(paciente.getTaNat())
                        .append(",")
                        .append(paciente.getTaTemp())
                        .append(",")
                        .append(paciente.getTaResp())
                        .append(",")
                        .append(paciente.getPrMal())
                        .append(",")
                        .append(paciente.getPrMaa())
                        .append(",")
                        .append(paciente.getPrMpa())
                        .append(",")
                        .append(paciente.getPrCc())
                        .append(",")
                        .append(paciente.getPrP())
                        .append(",")
                        .append(paciente.getPrEstb())
                        .append(",")
                        .append(paciente.getPrV())
                        .append(",")
                        .append(paciente.getPrRec())
                        .append(",")
                        .append(paciente.getPrNat())
                        .append(",")
                        .append(paciente.getPrTemp())
                        .append(",")
                        .append(paciente.getPrResp())
                        .append(",")
                        .append(paciente.getCgS())
                        .append(",")
                        .append(paciente.getCgE())
                        .append(",")
                        .append(paciente.getCgM())
                        .append(",")
                        .append(paciente.getCgRec())
                        .append(",")
                        .append(paciente.getCgNat())
                        .append(",")
                        .append(paciente.getCgTemp())
                        .append(",")
                        .append(paciente.getCgResp())
                        .append(",")
                        .append(paciente.getRoo())
                        .append(",")
                        .append(paciente.getBbt())
                        .append(",")
                        .append(paciente.getKbt())
                        .append(",")
                        .append(paciente.getEmc())
                        .append(",")
                        .append(paciente.getIse())
                        .append(",")
                        .append(paciente.getFrk())
                        .append(",")
                        .append(paciente.getJcb())
                        .append(",")
                        .append(paciente.getAm())
                        .append(",")
                        .append(paciente.getCc())
                        .append(",")
                        .append(paciente.getCna())
                        .append(",")
                        .append(paciente.getDdor())
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