package dev.hguedalia.proteroc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalhesPacienteActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtDataAtendimento;
    private TextView txtDataNascimento;
    private TextView txtIdade;
    private TextView txtLeito;
    private TextView txtProntuario;
    private TextView txtDiagnostico;
    private TextView txtHistOcup;
    private TextView txtIsolamento;
    private TextView txtDataAdmissao;
    private TextView txtFc;
    private TextView txtFr;
    private TextView txtSpo2;
    private TextView txtT;
    private TextView txtPa;
    private TextView txtDva;
    private TextView txtSedoanalgesia;
    private TextView txtAa;
    private TextView txtCaf;
    private TextView txtCatNasal;
    private TextView txtCanNasal;
    private TextView txtMascaraVenturi;
    private TextView txtElmo;
    private TextView txtTot;
    private TextView txtTqt;
    private TextView txtGlg;
    private TextView txtRass;
    private TextView txtLas;
    private TextView txtBo;
    private TextView txtAshw;
    private TextView txtAsia;
    private TextView txtBps;
    private TextView txtHemoglobina;
    private TextView txtLeucocitos;
    private TextView txtPlaquetas;
    private TextView txtTvp;
    private TextView txtTep;
    private TextView txtAptoMob;
    private TextView txtDecDorsal;
    private TextView txtDecLateral;
    private TextView txtSed;
    private TextView txtBip;
    private TextView txtAuM;
    private TextView txtAuRg;
    private TextView txtAuRa;
    private TextView txtAuV;
    private TextView txtAuRec;
    private TextView txtAuNat;
    private TextView txtAuTemp;
    private TextView txtAuResp;
    private TextView txtOlAs;
    private TextView txtOlAf;
    private TextView txtOlRec;
    private TextView txtOlNat;
    private TextView txtOlTemp;
    private TextView txtOlResp;
    private TextView txtViFei;
    private TextView txtViFee;
    private TextView txtViFai;
    private TextView txtViFae;
    private TextView txtViRec;
    private TextView txtViNat;
    private TextView txtViTemp;
    private TextView txtViResp;
    private TextView txtTaTl;
    private TextView txtTaTp;
    private TextView txtTaStf;
    private TextView txtTaStq;
    private TextView txtTaTr;
    private TextView txtTaEr;
    private TextView txtTaIec;
    private TextView txtTaRec;
    private TextView txtTaNat;
    private TextView txtTaTemp;
    private TextView txtTaResp;
    private TextView txtPrMal;
    private TextView txtPrMaa;
    private TextView txtPrMpa;
    private TextView txtPrCc;
    private TextView txtPrP;
    private TextView txtPrEstb;
    private TextView txtPrV;
    private TextView txtPrRec;
    private TextView txtPrNat;
    private TextView txtPrTemp;
    private TextView txtPrResp;
    private TextView txtCgS;
    private TextView txtCgE;
    private TextView txtCgM;
    private TextView txtCgRec;
    private TextView txtCgNat;
    private TextView txtCgTemp;
    private TextView txtCgResp;
    private TextView txtRoo;
    private TextView txtBbt;
    private TextView txtKbt;
    private TextView txtEmc;
    private TextView txtIse;
    private TextView txtFrk;
    private TextView txtJcb;
    private TextView txtAm;
    private TextView txtCc;
    private TextView txtCna;
    private TextView txtDdor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_paciente);
        getSupportActionBar().setTitle(R.string.detalhes_paciente);

        txtNome = findViewById(R.id.txtNome);
        txtDataAtendimento = findViewById(R.id.txtDataAtendimento);
        txtDataNascimento = findViewById(R.id.txtDataNascimento);
        txtIdade = findViewById(R.id.txtIdade);
        txtLeito = findViewById(R.id.txtLeito);
        txtProntuario = findViewById(R.id.txtProntuario);
        txtDiagnostico = findViewById(R.id.txtDiagnostico);
        txtHistOcup = findViewById(R.id.txtHistOcup);
        txtIsolamento = findViewById(R.id.txtIsolamento);
        txtDataAdmissao = findViewById(R.id.txtDataAdmissao);
        txtFc = findViewById(R.id.txtFc);
        txtFr = findViewById(R.id.txtFr);
        txtSpo2 = findViewById(R.id.txtSpo2);
        txtT = findViewById(R.id.txtT);
        txtPa = findViewById(R.id.txtPa);
        txtDva = findViewById(R.id.txtDva);
        txtSedoanalgesia = findViewById(R.id.txtSedoanalgesia);
        txtAa = findViewById(R.id.txtAa);
        txtCaf = findViewById(R.id.txtCaf);
        txtCatNasal = findViewById(R.id.txtCatNasal);
        txtCanNasal = findViewById(R.id.txtCanNasal);
        txtMascaraVenturi = findViewById(R.id.txtMascaraVenturi);
        txtElmo = findViewById(R.id.txtElmo);
        txtTot = findViewById(R.id.txtTot);
        txtTqt = findViewById(R.id.txtTqt);
        txtGlg = findViewById(R.id.txtGlg);
        txtRass = findViewById(R.id.txtRass);
        txtLas = findViewById(R.id.txtLas);
        txtBo = findViewById(R.id.txtBo);
        txtAshw = findViewById(R.id.txtAshw);
        txtAsia = findViewById(R.id.txtAsia);
        txtBps = findViewById(R.id.txtBps);
        txtHemoglobina = findViewById(R.id.txtHemoglobina);
        txtLeucocitos = findViewById(R.id.txtLeucocitos);
        txtPlaquetas = findViewById(R.id.txtPlaquetas);
        txtTvp = findViewById(R.id.txtTvp);
        txtTep = findViewById(R.id.txtTep);
        txtAptoMob = findViewById(R.id.txtAptoMob);
        txtDecDorsal = findViewById(R.id.txtDecDorsal);
        txtDecLateral = findViewById(R.id.txtDecLateral);
        txtSed = findViewById(R.id.txtSed);
        txtBip = findViewById(R.id.txtBip);
        txtAuM = findViewById(R.id.txtAuM);
        txtAuRg = findViewById(R.id.txtAuRg);
        txtAuRa = findViewById(R.id.txtAuRa);
        txtAuV = findViewById(R.id.txtAuV);
        txtAuRec = findViewById(R.id.txtAuRec);
        txtAuNat = findViewById(R.id.txtAuNat);
        txtAuTemp = findViewById(R.id.txtAuTemp);
        txtAuResp = findViewById(R.id.txtAuResp);
        txtOlAs = findViewById(R.id.txtOlAs);
        txtOlAf = findViewById(R.id.txtOlAf);
        txtOlRec = findViewById(R.id.txtOlRec);
        txtOlNat = findViewById(R.id.txtOlNat);
        txtOlTemp = findViewById(R.id.txtOlTemp);
        txtOlResp = findViewById(R.id.txtOlResp);
        txtViFei = findViewById(R.id.txtViFei);
        txtViFee = findViewById(R.id.txtViFee);
        txtViFai = findViewById(R.id.txtViFai);
        txtViFae = findViewById(R.id.txtViFae);
        txtViRec = findViewById(R.id.txtViRec);
        txtViNat = findViewById(R.id.txtViNat);
        txtViTemp = findViewById(R.id.txtViTemp);
        txtViResp = findViewById(R.id.txtViResp);
        txtTaTl = findViewById(R.id.txtTaTl);
        txtTaTp = findViewById(R.id.txtTaTp);
        txtTaStf = findViewById(R.id.txtTaStf);
        txtTaStq = findViewById(R.id.txtTaStq);
        txtTaTr = findViewById(R.id.txtTaTr);
        txtTaEr = findViewById(R.id.txtTaEr);
        txtTaIec = findViewById(R.id.txtTaIec);
        txtTaRec = findViewById(R.id.txtTaRec);
        txtTaNat = findViewById(R.id.txtTaNat);
        txtTaTemp = findViewById(R.id.txtTaTemp);
        txtTaResp = findViewById(R.id.txtTaResp);
        txtPrMal = findViewById(R.id.txtPrMal);
        txtPrMaa = findViewById(R.id.txtPrMaa);
        txtPrMpa = findViewById(R.id.txtPrMpa);
        txtPrCc = findViewById(R.id.txtPrCc);
        txtPrP = findViewById(R.id.txtPrP);
        txtPrEstb = findViewById(R.id.txtPrEstb);
        txtPrV = findViewById(R.id.txtPrV);
        txtPrRec = findViewById(R.id.txtPrRec);
        txtPrNat = findViewById(R.id.txtPrNat);
        txtPrTemp = findViewById(R.id.txtPrTemp);
        txtPrResp = findViewById(R.id.txtPrResp);
        txtCgS = findViewById(R.id.txtCgS);
        txtCgE = findViewById(R.id.txtCgE);
        txtCgM = findViewById(R.id.txtCgM);
        txtCgRec = findViewById(R.id.txtCgRec);
        txtCgNat = findViewById(R.id.txtCgNat);
        txtCgTemp = findViewById(R.id.txtCgTemp);
        txtCgResp = findViewById(R.id.txtCgResp);
        txtRoo = findViewById(R.id.txtRoo);
        txtBbt = findViewById(R.id.txtBbt);
        txtKbt = findViewById(R.id.txtKbt);
        txtEmc = findViewById(R.id.txtEmc);
        txtIse = findViewById(R.id.txtIse);
        txtFrk = findViewById(R.id.txtFrk);
        txtJcb = findViewById(R.id.txtJcb);
        txtAm = findViewById(R.id.txtAm);
        txtCc = findViewById(R.id.txtCc);
        txtCna = findViewById(R.id.txtCna);
        txtDdor = findViewById(R.id.txtDdor);


        Intent intent = getIntent();
        if (intent != null) {
            int pacienteId = intent.getIntExtra("id", -1);
            PacienteDAO dao = new PacienteDAO(this);
            Paciente paciente = dao.obterPacientePorId(pacienteId);
            if (paciente != null) {
                txtNome.setText(paciente.getNome());
                txtDataAtendimento.setText(paciente.getDataAtendimento());
                txtDataNascimento.setText(paciente.getDataNascimento());
                txtIdade.setText(paciente.getIdade());
                txtLeito.setText(paciente.getLeito());
                txtProntuario.setText(paciente.getProntuario());
                txtDiagnostico.setText(paciente.getDiagnostico());
                txtHistOcup.setText(paciente.getHistOcup());
                txtIsolamento.setText(paciente.getIsolamento());
                txtDataAdmissao.setText(paciente.getDataAdmissao());
                txtFc.setText(paciente.getFc());
                txtFr.setText(paciente.getFr());
                txtSpo2.setText(paciente.getSpo2());
                txtT.setText(paciente.getT());
                txtPa.setText(paciente.getPa());
                txtDva.setText(paciente.getDva());
                txtSedoanalgesia.setText(paciente.getSedoanalgesia());
                txtAa.setText(paciente.getAa());
                txtCaf.setText(paciente.getCaf());
                txtCatNasal.setText(paciente.getCatNasal());
                txtCanNasal.setText(paciente.getCanNasal());
                txtMascaraVenturi.setText(paciente.getMascaraVenturi());
                txtElmo.setText(paciente.getElmo());
                txtTot.setText(paciente.getTot());
                txtTqt.setText(paciente.getTqt());
                txtGlg.setText(paciente.getGlg());
                txtRass.setText(paciente.getRass());
                txtLas.setText(paciente.getLas());
                txtBo.setText(paciente.getBo());
                txtAshw.setText(paciente.getAshw());
                txtAsia.setText(paciente.getAsia());
                txtBps.setText(paciente.getBps());
                txtHemoglobina.setText(paciente.getHemoglobina());
                txtLeucocitos.setText(paciente.getLeucocitos());
                txtPlaquetas.setText(paciente.getPlaquetas());
                txtTvp.setText(paciente.getTvp());
                txtTep.setText(paciente.getTep());
                txtAptoMob.setText(paciente.getAptoMob());
                txtDecDorsal.setText(paciente.getDecDorsal());
                txtDecLateral.setText(paciente.getDecLateral());
                txtSed.setText(paciente.getSed());
                txtBip.setText(paciente.getBip());
                txtAuM.setText(paciente.getAuM());
                txtAuRg.setText(paciente.getAuRg());
                txtAuRa.setText(paciente.getAuRa());
                txtAuV.setText(paciente.getAuV());
                txtAuRec.setText(paciente.getAuRec());
                txtAuNat.setText(paciente.getAuNat());
                txtAuTemp.setText(paciente.getAuTemp());
                txtAuResp.setText(paciente.getAuResp());
                txtOlAs.setText(paciente.getOlAs());
                txtOlAf.setText(paciente.getOlAf());
                txtOlRec.setText(paciente.getOlRec());
                txtOlNat.setText(paciente.getOlNat());
                txtOlTemp.setText(paciente.getOlTemp());
                txtOlResp.setText(paciente.getOlResp());
                txtViFei.setText(paciente.getViFei());
                txtViFee.setText(paciente.getViFee());
                txtViFai.setText(paciente.getViFai());
                txtViFae.setText(paciente.getViFae());
                txtViRec.setText(paciente.getViRec());
                txtViNat.setText(paciente.getViNat());
                txtViTemp.setText(paciente.getViTemp());
                txtViResp.setText(paciente.getViResp());
                txtTaTl.setText(paciente.getTaTl());
                txtTaTp.setText(paciente.getTaTp());
                txtTaStf.setText(paciente.getTaStf());
                txtTaStq.setText(paciente.getTaStq());
                txtTaTr.setText(paciente.getTaTr());
                txtTaEr.setText(paciente.getTaEr());
                txtTaIec.setText(paciente.getTaIec());
                txtTaRec.setText(paciente.getTaRec());
                txtTaNat.setText(paciente.getTaNat());
                txtTaTemp.setText(paciente.getTaTemp());
                txtTaResp.setText(paciente.getTaResp());
                txtPrMal.setText(paciente.getPrMal());
                txtPrMaa.setText(paciente.getPrMaa());
                txtPrMpa.setText(paciente.getPrMpa());
                txtPrCc.setText(paciente.getPrCc());
                txtPrP.setText(paciente.getPrP());
                txtPrEstb.setText(paciente.getPrEstb());
                txtPrV.setText(paciente.getPrV());
                txtPrRec.setText(paciente.getPrRec());
                txtPrNat.setText(paciente.getPrNat());
                txtPrTemp.setText(paciente.getPrTemp());
                txtPrResp.setText(paciente.getPrResp());
                txtCgS.setText(paciente.getCgS());
                txtCgE.setText(paciente.getCgE());
                txtCgM.setText(paciente.getCgM());
                txtCgRec.setText(paciente.getCgRec());
                txtCgNat.setText(paciente.getCgNat());
                txtCgTemp.setText(paciente.getCgTemp());
                txtCgResp.setText(paciente.getCgResp());
                txtRoo.setText(paciente.getRoo());
                txtBbt.setText(paciente.getBbt());
                txtKbt.setText(paciente.getKbt());
                txtEmc.setText(paciente.getEmc());
                txtIse.setText(paciente.getIse());
                txtFrk.setText(paciente.getFrk());
                txtJcb.setText(paciente.getJcb());
                txtAm.setText(paciente.getAm());
                txtCc.setText(paciente.getCc());
                txtCna.setText(paciente.getCna());
                txtDdor.setText(paciente.getDdor());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_detalhes, menu);
        return true;
    }

    public void homepage(MenuItem item) {
        Intent it = new Intent(this, TelaOpcoesActivity.class);
        startActivity(it);
    }

    public void voltar(MenuItem item) {
        Intent it = new Intent(this, ListarPacienteActivity.class);
        startActivity(it);
    }

    public void expPDF(MenuItem item) {

        Document document = new Document(PageSize.A4);

        Intent intent = getIntent();
        if (intent != null) {
            try {
                // Obter o ID do paciente passado como extra
                int pacienteId = intent.getIntExtra("id", -1);
                PacienteDAO dao = new PacienteDAO(this);
                Paciente paciente = dao.obterPacientePorId(pacienteId);

                // Cria uma variável com o nome do paciente
                String nomeLista = paciente.getNome();
                int primeiroEspaco = nomeLista.indexOf(" ");
                int segundoEspaco = nomeLista.indexOf(" ", primeiroEspaco + 1);

                String nomePaciente;
                if (segundoEspaco > primeiroEspaco) {
                    nomePaciente = nomeLista.substring(0, segundoEspaco);
                    int tamanhoCampo = 20;
                    int caracteresNovoNome = nomePaciente.length();
                    int espacosFinal = tamanhoCampo - caracteresNovoNome;

                    StringBuilder sb = new StringBuilder(nomePaciente);
                    for (int i = 0; i < espacosFinal; i++) {
                        sb.append(" ");
                    }
                    nomePaciente = sb.toString();
                    nomePaciente = nomePaciente.replace(" ", "_");
                } else {
                    nomePaciente = nomeLista.replace(" ", "_");
                }
                //FIM



                // Verifica se o armazenamento externo está disponível para salvar o PDF
                if (isExternalStorageWritable()) {
                    File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "RelatoriosAPTOI");
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    int contador = 0;
                    File file; // = new File(directory, "RelatorioPaciente.pdf");
                    do {
                        contador++;
                        String dataGeracao = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
                        String nomeArquivo = "[L" + paciente.getLeito() + ".R" + contador + "." + dataGeracao + "]_" + nomePaciente + "_.pdf";
                        file = new File(directory, nomeArquivo);
                    } while (file.exists());

                    FileOutputStream fOut = new FileOutputStream(file);
                    PdfWriter.getInstance(document, fOut);

                    // Abre o documento para escrita
                    document.open();

                    // Configura a fonte do texto (Calibri, Tamanho 11)
                    Font font = FontFactory.getFont("assets/fonts/Calibri.ttf", "Cp1252", true);
                    font.setSize(12);

                    // Adiciona os dados do paciente ao documento
                    String dadosPaciente =
                            "[ Data de atendimento: " + paciente.getDataAtendimento() + " ]" + "\n" + "\n"
                                    + "[ Leito: " + paciente.getLeito() + " ]" + "\n"
                                    + "[ Prontuario: " + paciente.getProntuario() + " ]" + "\n"
                                    + "[ Data de admissao: " + paciente.getDataAdmissao() + " ]" + "\n"
                                    + "[ Isolamento: " + paciente.getIsolamento() + " ]" + "\n" + "\n"

                                    + "DADOS_____________________________________________" + "\n"
                                    + "       Nome: " + paciente.getNome() + "\n"
                                    + "       Data de nascimento: " + paciente.getDataNascimento() + "\n"
                                    + "       Idade: " + paciente.getIdade() + "\n"
                                    + "       Diagnostico: " + paciente.getDiagnostico() + "\n"
                                    + "       Historico ocupacional: " + paciente.getHistOcup() + "\n" + "\n"

                                    + "SINAIS VITAIS_____________________________________" + "\n"
                                    + "       FC: " + paciente.getFc() + "\n"
                                    + "       FR: " + paciente.getFr() + "\n"
                                    + "       SpO2: " + paciente.getSpo2() + "\n"
                                    + "       T: " + paciente.getT() + "\n"
                                    + "       PA: " + paciente.getPa() + "\n" + "\n"

                                    + "FÁRMACOS__________________________________________" + "\n"
                                    + "       DVA: " + paciente.getDva() + "\n"
                                    + "       Sedoanalgesia: " + paciente.getSedoanalgesia() + "\n" + "\n"

                                    + "SUPORTE À VIDA____________________________________" + "\n"
                                    + "       AA: " + paciente.getAa() + "\n"
                                    + "       CAF: " + paciente.getCaf() + "\n"
                                    + "       Cat nasal: " + paciente.getCatNasal() + "\n"
                                    + "       Can nasal: " + paciente.getCanNasal() + "\n"
                                    + "       M Venturi: " + paciente.getMascaraVenturi() + "\n"
                                    + "       Elmo: " + paciente.getElmo() + "\n"
                                    + "       TOT: " + paciente.getTot() + "\n"
                                    + "       TQT: " + paciente.getTqt() + "\n" + "\n"

                                    + "AVALIAÇÕES________________________________________" + "\n"
                                    + "       GLG: " + paciente.getGlg() + "\n"
                                    + "       RASS: " + paciente.getRass() + "\n"
                                    + "       LAS: " + paciente.getLas() + "\n"
                                    + "       BO: " + paciente.getBo() + "\n"
                                    + "       ASHW: " + paciente.getAshw() + "\n"
                                    + "       ASIA: " + paciente.getAsia() + "\n"
                                    + "       BPS: " + paciente.getBps() + "\n" + "\n"

                                    + "EXAMES____________________________________________" + "\n"
                                    + "       Hemoglobina: " + paciente.getHemoglobina() + "\n"
                                    + "       Leucocitos: " + paciente.getLeucocitos() + "\n"
                                    + "       Plaquetas: " + paciente.getPlaquetas() + "\n"
                                    + "       TVP: " + paciente.getTvp() + "\n"
                                    + "       TEP: " + paciente.getTep() + "\n" + "\n"

                                    + "POSTURA___________________________________________" + "\n"
                                    + "       Apto para mobilizacao: " + paciente.getAptoMob() + "\n"
                                    + "       Dec dorsal: " + paciente.getDecDorsal() + "\n"
                                    + "       Dec lateral: " + paciente.getDecLateral() + "\n"
                                    + "       SED: " + paciente.getSed() + "\n"
                                    + "       BIP: " + paciente.getBip() + "\n" + "\n"

                                    + "ESTÍMULO AUDITIVO_________________________________" + "\n"
                                    + "       M: " + paciente.getAuM() + "\n"
                                    + "       RG: " + paciente.getAuRg() + "\n"
                                    + "       RA: " + paciente.getAuRa() + "\n"
                                    + "       V: " + paciente.getAuV() + "\n"
                                    + "       Rec: " + paciente.getAuRec() + "\n"
                                    + "       Nat: " + paciente.getAuNat() + "\n"
                                    + "       Temp: " + paciente.getAuTemp() + "\n"
                                    + "       Resp: " + paciente.getAuResp() + "\n" + "\n"

                                    + "ESTÍMULO OLFATIVO_________________________________" + "\n"
                                    + "       AS: " + paciente.getOlAs() + "\n"
                                    + "       AF: " + paciente.getOlAf() + "\n"
                                    + "       Rec: " + paciente.getOlRec() + "\n"
                                    + "       Nat: " + paciente.getOlNat() + "\n"
                                    + "       Temp: " + paciente.getOlTemp() + "\n"
                                    + "       Resp: " + paciente.getOlResp() + "\n" + "\n"

                                    + "ESTÍMULO VISUAL___________________________________" + "\n"
                                    + "       FEI: " + paciente.getViFei() + "\n"
                                    + "       FEE: " + paciente.getViFee() + "\n"
                                    + "       FAI: " + paciente.getViFai() + "\n"
                                    + "       FAE: " + paciente.getViFae() + "\n"
                                    + "       Rec: " + paciente.getViRec() + "\n"
                                    + "       Nat: " + paciente.getViNat() + "\n"
                                    + "       Temp: " + paciente.getViTemp() + "\n"
                                    + "       Resp: " + paciente.getViResp() + "\n" + "\n"

                                    + "ESTÍMULO TÁTIL____________________________________" + "\n"
                                    + "       TI: " + paciente.getTaTl() + "\n"
                                    + "       TP: " + paciente.getTaTp() + "\n"
                                    + "       STF: " + paciente.getTaStf() + "\n"
                                    + "       STQ: " + paciente.getTaStq() + "\n"
                                    + "       TR: " + paciente.getTaTr() + "\n"
                                    + "       ER: " + paciente.getTaEr() + "\n"
                                    + "       Iec: " + paciente.getTaIec() + "\n"
                                    + "       Rec: " + paciente.getTaRec() + "\n"
                                    + "       Nat: " + paciente.getTaNat() + "\n"
                                    + "       Temp: " + paciente.getTaTemp() + "\n"
                                    + "       Resp: " + paciente.getTaResp() + "\n" + "\n"

                                    + "ESTÍMULO PROPRIOCEPTIVO___________________________" + "\n"
                                    + "       MAL: " + paciente.getPrMal() + "\n"
                                    + "       MAA: " + paciente.getPrMaa() + "\n"
                                    + "       MPA: " + paciente.getPrMpa() + "\n"
                                    + "       CC: " + paciente.getPrCc() + "\n"
                                    + "       P: " + paciente.getPrP() + "\n"
                                    + "       ESTB: " + paciente.getPrEstb() + "\n"
                                    + "       V: " + paciente.getPrV() + "\n"
                                    + "       Rec: " + paciente.getPrRec() + "\n"
                                    + "       Nat: " + paciente.getPrNat() + "\n"
                                    + "       Temp: " + paciente.getPrTemp() + "\n"
                                    + "       Resp: " + paciente.getPrResp() + "\n" + "\n"

                                    + "ESTÍMULO COGNITIVO________________________________" + "\n"
                                    + "       S: " + paciente.getCgS() + "\n"
                                    + "       E: " + paciente.getCgE() + "\n"
                                    + "       M: " + paciente.getCgM() + "\n"
                                    + "       Rec: " + paciente.getCgRec() + "\n"
                                    + "       Nat: " + paciente.getCgNat() + "\n"
                                    + "       Temp: " + paciente.getCgTemp() + "\n"
                                    + "       Resp: " + paciente.getCgResp() + "\n" + "\n"

                                    + "MÉTODO TERAPÊUTICO________________________________" + "\n"
                                    + "       ROO: " + paciente.getRoo() + "\n"
                                    + "       BBT: " + paciente.getBbt() + "\n"
                                    + "       KBT: " + paciente.getKbt() + "\n"
                                    + "       EMC: " + paciente.getEmc() + "\n"
                                    + "       IS: " + paciente.getIse() + "\n"
                                    + "       FRK: " + paciente.getFrk() + "\n"
                                    + "       JCB: " + paciente.getJcb() + "\n"
                                    + "       AM: " + paciente.getAm() + "\n"
                                    + "       CC: " + paciente.getCc() + "\n"
                                    + "       CNA: " + paciente.getCna() + "\n"
                                    + "       DDOR: " + paciente.getDdor();

                    addTextoToDocument(document, dadosPaciente, font);

                    // Fecha o documento
                    document.close();

                    Toast.makeText(this, "RELATÓRIO GERADO COM SUCESSO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "ARMAZENAMENTO INDISPONÍVEL", Toast.LENGTH_SHORT).show();
                }
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "ERRO AO GERAR RELATÓRIO", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addTextoToDocument(Document document, String text, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        document.add(paragraph);
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

}
