package dev.hguedalia.proteroc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastrarPacienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText dataAtendimento;
    private boolean isFormatting;
    private EditText dataNascimento;
    private EditText idade;
    private EditText leito;
    private EditText prontuario;
    private EditText diagnostico;
    private EditText histOcup;
    private EditText isolamento;
    private EditText dataAdmissao;
    private EditText fc;
    private EditText fr;
    private EditText spo2;
    private EditText t;
    private EditText pa;
    private EditText dva;
    private EditText sedoanalgesia;
    private EditText aa;
    private EditText caf;
    private EditText catNasal;
    private EditText canNasal;
    private EditText mascaraVenturi;
    private EditText elmo;
    private EditText tot;
    private EditText tqt;
    private EditText glg;
    private EditText rass;
    private EditText las;
    private EditText bo;
    private EditText ashw;
    private EditText asia;
    private EditText bps;
    private EditText hemoglobina;
    private EditText leucocitos;
    private EditText plaquetas;
    private EditText tvp;
    private EditText tep;
    private EditText aptoMob;
    private EditText decDorsal;
    private EditText decLateral;
    private EditText sed;
    private EditText bip;
    private EditText auM;
    private EditText auRg;
    private EditText auRa;
    private EditText auV;
    private EditText auRec;
    private EditText auNat;
    private EditText auTemp;
    private EditText auResp;
    private EditText olAs;
    private EditText olAf;
    private EditText olRec;
    private EditText olNat;
    private EditText olTemp;
    private EditText olResp;
    private EditText viFei;
    private EditText viFee;
    private EditText viFai;
    private EditText viFae;
    private EditText viRec;
    private EditText viNat;
    private EditText viTemp;
    private EditText viResp;
    private EditText taTl;
    private EditText taTp;
    private EditText taStf;
    private EditText taStq;
    private EditText taTr;
    private EditText taEr;
    private EditText taIec;
    private EditText taRec;
    private EditText taNat;
    private EditText taTemp;
    private EditText taResp;
    private EditText prMal;
    private EditText prMaa;
    private EditText prMpa;
    private EditText prCc;
    private EditText prP;
    private EditText prEstb;
    private EditText prV;
    private EditText prRec;
    private EditText prNat;
    private EditText prTemp;
    private EditText prResp;
    private EditText cgS;
    private EditText cgE;
    private EditText cgM;
    private EditText cgRec;
    private EditText cgNat;
    private EditText cgTemp;
    private EditText cgResp;
    private EditText roo;
    private EditText bbt;
    private EditText kbt;
    private EditText emc;
    private EditText ise;
    private EditText frk;
    private EditText jcb;
    private EditText am;
    private EditText cc;
    private EditText cna;
    private EditText ddor;
    private PacienteDAO dao;
    private Paciente paciente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_paciente);
        getSupportActionBar().setTitle(R.string.cadastro_paciente);

        nome = findViewById(R.id.editNome);
        dataAtendimento = findViewById(R.id.editDataAtendimento);
        dataAtendimento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isFormatting) {
                    isFormatting = false;
                    return;
                }

                String dataDigitada = s.toString().replaceAll("[^\\d]", "");
                String dataFormatada = FormatarData.dataFormatada(dataDigitada);

                isFormatting = true;
                dataAtendimento.setText(dataFormatada);
                dataAtendimento.setSelection(dataFormatada.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        dataNascimento = findViewById(R.id.editDataNascimento);
        dataNascimento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isFormatting) {
                    isFormatting = false;
                    return;
                }

                String dataDigitada = s.toString().replaceAll("[^\\d]", "");
                String dataFormatada = FormatarData.dataFormatada(dataDigitada);

                isFormatting = true;
                dataNascimento.setText(dataFormatada);
                dataNascimento.setSelection(dataFormatada.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        idade = findViewById(R.id.editIdade);
        leito = findViewById(R.id.editLeito);
        prontuario = findViewById(R.id.editProntuario);
        diagnostico = findViewById(R.id.editDiagnostico);
        histOcup = findViewById(R.id.editHistOcup);
        isolamento = findViewById(R.id.editIsolamento);
        dataAdmissao = findViewById(R.id.editDataAdmissao);
        dataAdmissao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isFormatting) {
                    isFormatting = false;
                    return;
                }

                String dataDigitada = s.toString().replaceAll("[^\\d]", "");
                String dataFormatada = FormatarData.dataFormatada(dataDigitada);

                isFormatting = true;
                dataAdmissao.setText(dataFormatada);
                dataAdmissao.setSelection(dataFormatada.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        fc = findViewById(R.id.editFc);
        fr = findViewById(R.id.editFr);
        spo2 = findViewById(R.id.editSpo2);
        t = findViewById(R.id.editT);
        pa = findViewById(R.id.editPa);
        dva = findViewById(R.id.editDva);
        sedoanalgesia = findViewById(R.id.editSedoanalgesia);
        aa = findViewById(R.id.editAa);
        caf = findViewById(R.id.editCaf);
        catNasal = findViewById(R.id.editCatNasal);
        canNasal = findViewById(R.id.editCanNasal);
        mascaraVenturi = findViewById(R.id.editMascaraVenturi);
        elmo = findViewById(R.id.editElmo);
        tot = findViewById(R.id.editTot);
        tqt = findViewById(R.id.editTqt);
        glg = findViewById(R.id.editGlg);
        rass = findViewById(R.id.editRass);
        las = findViewById(R.id.editLas);
        bo = findViewById(R.id.editBo);
        ashw = findViewById(R.id.editAshw);
        asia = findViewById(R.id.editAsia);
        bps = findViewById(R.id.editBps);
        hemoglobina = findViewById(R.id.editHemoglobina);
        leucocitos = findViewById(R.id.editLeucocitos);
        plaquetas = findViewById(R.id.editPlaquetas);
        tvp = findViewById(R.id.editTvp);
        tep = findViewById(R.id.editTep);
        aptoMob = findViewById(R.id.editAptoMob);
        decDorsal = findViewById(R.id.editDecDorsal);
        decLateral = findViewById(R.id.editDecLateral);
        sed = findViewById(R.id.editSed);
        bip = findViewById(R.id.editBip);
        auM = findViewById(R.id.editAuM);
        auRg = findViewById(R.id.editAuRg);
        auRa = findViewById(R.id.editAuRa);
        auV = findViewById(R.id.editAuV);
        auRec = findViewById(R.id.editAuRec);
        auNat = findViewById(R.id.editAuNat);
        auTemp = findViewById(R.id.editAuTemp);
        auResp = findViewById(R.id.editAuResp);
        olAs = findViewById(R.id.editOlAs);
        olAf = findViewById(R.id.editOlAf);
        olRec = findViewById(R.id.editOlRec);
        olNat = findViewById(R.id.editOlNat);
        olTemp = findViewById(R.id.editOlTemp);
        olResp = findViewById(R.id.editOlResp);
        viFei = findViewById(R.id.editViFei);
        viFee = findViewById(R.id.editViFee);
        viFai = findViewById(R.id.editViFai);
        viFae = findViewById(R.id.editViFae);
        viRec = findViewById(R.id.editViRec);
        viNat = findViewById(R.id.editViNat);
        viTemp = findViewById(R.id.editViTemp);
        viResp = findViewById(R.id.editViResp);
        taTl = findViewById(R.id.editTaTl);
        taTp = findViewById(R.id.editTaTp);
        taStf = findViewById(R.id.editTaStf);
        taStq = findViewById(R.id.editTaStq);
        taTr = findViewById(R.id.editTaTr);
        taEr = findViewById(R.id.editTaEr);
        taIec = findViewById(R.id.editTaIec);
        taRec = findViewById(R.id.editTaRec);
        taNat = findViewById(R.id.editTaNat);
        taTemp = findViewById(R.id.editTaTemp);
        taResp = findViewById(R.id.editTaResp);
        prMal = findViewById(R.id.editPrMal);
        prMaa = findViewById(R.id.editPrMaa);
        prMpa = findViewById(R.id.editPrMpa);
        prCc = findViewById(R.id.editPrCc);
        prP = findViewById(R.id.editPrP);
        prEstb = findViewById(R.id.editPrEstb);
        prV = findViewById(R.id.editPrV);
        prRec = findViewById(R.id.editPrRec);
        prNat = findViewById(R.id.editPrNat);
        prTemp = findViewById(R.id.editPrTemp);
        prResp = findViewById(R.id.editPrResp);
        cgS = findViewById(R.id.editCgS);
        cgE = findViewById(R.id.editCgE);
        cgM = findViewById(R.id.editCgM);
        cgRec = findViewById(R.id.editCgRec);
        cgNat = findViewById(R.id.editCgNat);
        cgTemp = findViewById(R.id.editCgTemp);
        cgResp = findViewById(R.id.editCgResp);
        roo = findViewById(R.id.editRoo);
        bbt = findViewById(R.id.editBbt);
        kbt = findViewById(R.id.editKbt);
        emc = findViewById(R.id.editEmc);
        ise = findViewById(R.id.editIse);
        frk = findViewById(R.id.editFrk);
        jcb = findViewById(R.id.editJcb);
        am = findViewById(R.id.editAm);
        cc = findViewById(R.id.editCc);
        cna = findViewById(R.id.editCna);
        ddor = findViewById(R.id.editDdor);
        dao = new PacienteDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("paciente")) {
            paciente = (Paciente) it.getSerializableExtra("paciente");
            nome.setText(paciente.getNome());
            dataAtendimento.setText(paciente.getDataAtendimento());
            dataNascimento.setText(paciente.getDataNascimento());
            idade.setText(paciente.getIdade());
            leito.setText(paciente.getLeito());
            prontuario.setText(paciente.getProntuario());
            diagnostico.setText(paciente.getDiagnostico());
            histOcup.setText(paciente.getHistOcup());
            isolamento.setText(paciente.getIsolamento());
            dataAdmissao.setText(paciente.getDataAdmissao());
            fc.setText(paciente.getFc());
            fr.setText(paciente.getFr());
            spo2.setText(paciente.getSpo2());
            t.setText(paciente.getT());
            pa.setText(paciente.getPa());
            dva.setText(paciente.getDva());
            sedoanalgesia.setText(paciente.getSedoanalgesia());
            aa.setText(paciente.getAa());
            caf.setText(paciente.getCaf());
            catNasal.setText(paciente.getCatNasal());
            canNasal.setText(paciente.getCanNasal());
            mascaraVenturi.setText(paciente.getMascaraVenturi());
            elmo.setText(paciente.getElmo());
            tot.setText(paciente.getTot());
            tqt.setText(paciente.getTqt());
            glg.setText(paciente.getGlg());
            rass.setText(paciente.getRass());
            las.setText(paciente.getLas());
            bo.setText(paciente.getBo());
            ashw.setText(paciente.getAshw());
            asia.setText(paciente.getAsia());
            bps.setText(paciente.getBps());
            hemoglobina.setText(paciente.getHemoglobina());
            leucocitos.setText(paciente.getLeucocitos());
            plaquetas.setText(paciente.getPlaquetas());
            tvp.setText(paciente.getTvp());
            tep.setText(paciente.getTep());
            aptoMob.setText(paciente.getAptoMob());
            decDorsal.setText(paciente.getDecDorsal());
            decLateral.setText(paciente.getDecLateral());
            sed.setText(paciente.getSed());
            bip.setText(paciente.getBip());
            auM.setText(paciente.getAuM());
            auRg.setText(paciente.getAuRg());
            auRa.setText(paciente.getAuRa());
            auV.setText(paciente.getAuV());
            auRec.setText(paciente.getAuRec());
            auNat.setText(paciente.getAuNat());
            auTemp.setText(paciente.getAuTemp());
            auResp.setText(paciente.getAuResp());
            olAs.setText(paciente.getOlAs());
            olAf.setText(paciente.getOlAf());
            olRec.setText(paciente.getOlRec());
            olNat.setText(paciente.getOlNat());
            olTemp.setText(paciente.getOlTemp());
            olResp.setText(paciente.getOlResp());
            viFei.setText(paciente.getViFei());
            viFee.setText(paciente.getViFee());
            viFai.setText(paciente.getViFai());
            viFae.setText(paciente.getViFae());
            viRec.setText(paciente.getViRec());
            viNat.setText(paciente.getViNat());
            viTemp.setText(paciente.getViTemp());
            viResp.setText(paciente.getViResp());
            taTl.setText(paciente.getTaTl());
            taTp.setText(paciente.getTaTp());
            taStf.setText(paciente.getTaStf());
            taStq.setText(paciente.getTaStq());
            taTr.setText(paciente.getTaTr());
            taEr.setText(paciente.getTaEr());
            taIec.setText(paciente.getTaIec());
            taRec.setText(paciente.getTaRec());
            taNat.setText(paciente.getTaNat());
            taTemp.setText(paciente.getTaTemp());
            taResp.setText(paciente.getTaResp());
            prMal.setText(paciente.getPrMal());
            prMaa.setText(paciente.getPrMaa());
            prMpa.setText(paciente.getPrMpa());
            prCc.setText(paciente.getPrCc());
            prP.setText(paciente.getPrP());
            prEstb.setText(paciente.getPrEstb());
            prV.setText(paciente.getPrV());
            prRec.setText(paciente.getPrRec());
            prNat.setText(paciente.getPrNat());
            prTemp.setText(paciente.getPrTemp());
            prResp.setText(paciente.getPrResp());
            cgS.setText(paciente.getCgS());
            cgE.setText(paciente.getCgE());
            cgM.setText(paciente.getCgM());
            cgRec.setText(paciente.getCgRec());
            cgNat.setText(paciente.getCgNat());
            cgTemp.setText(paciente.getCgTemp());
            cgResp.setText(paciente.getCgResp());
            roo.setText(paciente.getRoo());
            bbt.setText(paciente.getBbt());
            kbt.setText(paciente.getKbt());
            emc.setText(paciente.getEmc());
            ise.setText(paciente.getIse());
            frk.setText(paciente.getFrk());
            jcb.setText(paciente.getJcb());
            am.setText(paciente.getAm());
            cc.setText(paciente.getCc());
            cna.setText(paciente.getCna());
            ddor.setText(paciente.getDdor());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_cadastrar, menu);
        return true;
    }

    public void homepage(MenuItem item) {
        Intent it = new Intent(this, HomepageActivity.class);
        startActivity(it);
    }

    public void voltar(MenuItem item) {
        finish();
    }

    public void salvar(MenuItem item) {

        if (paciente == null || paciente.getDataAtendimento() == null) {
            paciente = new Paciente();
            paciente.setNome(nome.getText().toString());
            paciente.setDataAtendimento(dataAtendimento.getText().toString());
            paciente.setDataNascimento(dataNascimento.getText().toString());
            paciente.setIdade(idade.getText().toString());
            paciente.setLeito(leito.getText().toString());
            paciente.setProntuario(prontuario.getText().toString());
            paciente.setDiagnostico(diagnostico.getText().toString());
            paciente.setHistOcup(histOcup.getText().toString());
            paciente.setIsolamento(isolamento.getText().toString());
            paciente.setDataAdmissao(dataAdmissao.getText().toString());
            paciente.setFc(fc.getText().toString());
            paciente.setFr(fr.getText().toString());
            paciente.setSpo2(spo2.getText().toString());
            paciente.setT(t.getText().toString());
            paciente.setPa(pa.getText().toString());
            paciente.setDva(dva.getText().toString());
            paciente.setSedoanalgesia(sedoanalgesia.getText().toString());
            paciente.setAa(aa.getText().toString());
            paciente.setCaf(caf.getText().toString());
            paciente.setCatNasal(catNasal.getText().toString());
            paciente.setCanNasal(canNasal.getText().toString());
            paciente.setMascaraVenturi(mascaraVenturi.getText().toString());
            paciente.setElmo(elmo.getText().toString());
            paciente.setTot(tot.getText().toString());
            paciente.setTqt(tqt.getText().toString());
            paciente.setGlg(glg.getText().toString());
            paciente.setRass(rass.getText().toString());
            paciente.setLas(las.getText().toString());
            paciente.setBo(bo.getText().toString());
            paciente.setAshw(ashw.getText().toString());
            paciente.setAsia(asia.getText().toString());
            paciente.setBps(bps.getText().toString());
            paciente.setHemoglobina(hemoglobina.getText().toString());
            paciente.setLeucocitos(leucocitos.getText().toString());
            paciente.setPlaquetas(plaquetas.getText().toString());
            paciente.setTvp(tvp.getText().toString());
            paciente.setTep(tep.getText().toString());
            paciente.setAptoMob(aptoMob.getText().toString());
            paciente.setDecDorsal(decDorsal.getText().toString());
            paciente.setDecLateral(decLateral.getText().toString());
            paciente.setSed(sed.getText().toString());
            paciente.setBip(bip.getText().toString());
            paciente.setAuM(auM.getText().toString());
            paciente.setAuRg(auRg.getText().toString());
            paciente.setAuRa(auRa.getText().toString());
            paciente.setAuV(auV.getText().toString());
            paciente.setAuRec(auRec.getText().toString());
            paciente.setAuNat(auNat.getText().toString());
            paciente.setAuTemp(auTemp.getText().toString());
            paciente.setAuResp(auResp.getText().toString());
            paciente.setOlAs(olAs.getText().toString());
            paciente.setOlAf(olAf.getText().toString());
            paciente.setOlRec(olRec.getText().toString());
            paciente.setOlNat(olNat.getText().toString());
            paciente.setOlTemp(olTemp.getText().toString());
            paciente.setOlResp(olResp.getText().toString());
            paciente.setViFei(viFei.getText().toString());
            paciente.setViFee(viFee.getText().toString());
            paciente.setViFai(viFai.getText().toString());
            paciente.setViFae(viFae.getText().toString());
            paciente.setViRec(viRec.getText().toString());
            paciente.setViNat(viNat.getText().toString());
            paciente.setViTemp(viTemp.getText().toString());
            paciente.setViResp(viResp.getText().toString());
            paciente.setTaTl(taTl.getText().toString());
            paciente.setTaTp(taTp.getText().toString());
            paciente.setTaStf(taStf.getText().toString());
            paciente.setTaStq(taStq.getText().toString());
            paciente.setTaTr(taTr.getText().toString());
            paciente.setTaEr(taEr.getText().toString());
            paciente.setTaIec(taIec.getText().toString());
            paciente.setTaRec(taRec.getText().toString());
            paciente.setTaNat(taNat.getText().toString());
            paciente.setTaTemp(taTemp.getText().toString());
            paciente.setTaResp(taResp.getText().toString());
            paciente.setPrMal(prMal.getText().toString());
            paciente.setPrMaa(prMaa.getText().toString());
            paciente.setPrMpa(prMpa.getText().toString());
            paciente.setPrCc(prCc.getText().toString());
            paciente.setPrP(prP.getText().toString());
            paciente.setPrEstb(prEstb.getText().toString());
            paciente.setPrV(prV.getText().toString());
            paciente.setPrRec(prRec.getText().toString());
            paciente.setPrNat(prNat.getText().toString());
            paciente.setPrTemp(prTemp.getText().toString());
            paciente.setPrResp(prResp.getText().toString());
            paciente.setCgS(cgS.getText().toString());
            paciente.setCgE(cgE.getText().toString());
            paciente.setCgM(cgM.getText().toString());
            paciente.setCgRec(cgRec.getText().toString());
            paciente.setCgNat(cgNat.getText().toString());
            paciente.setCgTemp(cgTemp.getText().toString());
            paciente.setCgResp(cgResp.getText().toString());
            paciente.setRoo(roo.getText().toString());
            paciente.setBbt(bbt.getText().toString());
            paciente.setKbt(kbt.getText().toString());
            paciente.setEmc(emc.getText().toString());
            paciente.setIse(ise.getText().toString());
            paciente.setFrk(frk.getText().toString());
            paciente.setJcb(jcb.getText().toString());
            paciente.setAm(am.getText().toString());
            paciente.setCc(cc.getText().toString());
            paciente.setCna(cna.getText().toString());
            paciente.setDdor(ddor.getText().toString());
            long id = dao.inserir(paciente);
            Toast.makeText(this, "FICHA SALVA COM SUCESSO", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, ListarFichasActivity.class);
            startActivity(it);
        } else {
            paciente.setNome(nome.getText().toString());
            paciente.setDataAtendimento(dataAtendimento.getText().toString());
            paciente.setDataNascimento(dataNascimento.getText().toString());
            paciente.setIdade(idade.getText().toString());
            paciente.setLeito(leito.getText().toString());
            paciente.setProntuario(prontuario.getText().toString());
            paciente.setDiagnostico(diagnostico.getText().toString());
            paciente.setHistOcup(histOcup.getText().toString());
            paciente.setIsolamento(isolamento.getText().toString());
            paciente.setDataAdmissao(dataAdmissao.getText().toString());
            paciente.setFc(fc.getText().toString());
            paciente.setFr(fr.getText().toString());
            paciente.setSpo2(spo2.getText().toString());
            paciente.setT(t.getText().toString());
            paciente.setPa(pa.getText().toString());
            paciente.setDva(dva.getText().toString());
            paciente.setSedoanalgesia(sedoanalgesia.getText().toString());
            paciente.setAa(aa.getText().toString());
            paciente.setCaf(caf.getText().toString());
            paciente.setCatNasal(catNasal.getText().toString());
            paciente.setCanNasal(canNasal.getText().toString());
            paciente.setMascaraVenturi(mascaraVenturi.getText().toString());
            paciente.setElmo(elmo.getText().toString());
            paciente.setTot(tot.getText().toString());
            paciente.setTqt(tqt.getText().toString());
            paciente.setGlg(glg.getText().toString());
            paciente.setRass(rass.getText().toString());
            paciente.setLas(las.getText().toString());
            paciente.setBo(bo.getText().toString());
            paciente.setAshw(ashw.getText().toString());
            paciente.setAsia(asia.getText().toString());
            paciente.setBps(bps.getText().toString());
            paciente.setHemoglobina(hemoglobina.getText().toString());
            paciente.setLeucocitos(leucocitos.getText().toString());
            paciente.setPlaquetas(plaquetas.getText().toString());
            paciente.setTvp(tvp.getText().toString());
            paciente.setTep(tep.getText().toString());
            paciente.setAptoMob(aptoMob.getText().toString());
            paciente.setDecDorsal(decDorsal.getText().toString());
            paciente.setDecLateral(decLateral.getText().toString());
            paciente.setSed(sed.getText().toString());
            paciente.setBip(bip.getText().toString());
            paciente.setAuM(auM.getText().toString());
            paciente.setAuRg(auRg.getText().toString());
            paciente.setAuRa(auRa.getText().toString());
            paciente.setAuV(auV.getText().toString());
            paciente.setAuRec(auRec.getText().toString());
            paciente.setAuNat(auNat.getText().toString());
            paciente.setAuTemp(auTemp.getText().toString());
            paciente.setAuResp(auResp.getText().toString());
            paciente.setOlAs(olAs.getText().toString());
            paciente.setOlAf(olAf.getText().toString());
            paciente.setOlRec(olRec.getText().toString());
            paciente.setOlNat(olNat.getText().toString());
            paciente.setOlTemp(olTemp.getText().toString());
            paciente.setOlResp(olResp.getText().toString());
            paciente.setViFei(viFei.getText().toString());
            paciente.setViFee(viFee.getText().toString());
            paciente.setViFai(viFai.getText().toString());
            paciente.setViFae(viFae.getText().toString());
            paciente.setViRec(viRec.getText().toString());
            paciente.setViNat(viNat.getText().toString());
            paciente.setViTemp(viTemp.getText().toString());
            paciente.setViResp(viResp.getText().toString());
            paciente.setTaTl(taTl.getText().toString());
            paciente.setTaTp(taTp.getText().toString());
            paciente.setTaStf(taStf.getText().toString());
            paciente.setTaStq(taStq.getText().toString());
            paciente.setTaTr(taTr.getText().toString());
            paciente.setTaEr(taEr.getText().toString());
            paciente.setTaIec(taIec.getText().toString());
            paciente.setTaRec(taRec.getText().toString());
            paciente.setTaNat(taNat.getText().toString());
            paciente.setTaTemp(taTemp.getText().toString());
            paciente.setTaResp(taResp.getText().toString());
            paciente.setPrMal(prMal.getText().toString());
            paciente.setPrMaa(prMaa.getText().toString());
            paciente.setPrMpa(prMpa.getText().toString());
            paciente.setPrCc(prCc.getText().toString());
            paciente.setPrP(prP.getText().toString());
            paciente.setPrEstb(prEstb.getText().toString());
            paciente.setPrV(prV.getText().toString());
            paciente.setPrRec(prRec.getText().toString());
            paciente.setPrNat(prNat.getText().toString());
            paciente.setPrTemp(prTemp.getText().toString());
            paciente.setPrResp(prResp.getText().toString());
            paciente.setCgS(cgS.getText().toString());
            paciente.setCgE(cgE.getText().toString());
            paciente.setCgM(cgM.getText().toString());
            paciente.setCgRec(cgRec.getText().toString());
            paciente.setCgNat(cgNat.getText().toString());
            paciente.setCgTemp(cgTemp.getText().toString());
            paciente.setCgResp(cgResp.getText().toString());
            paciente.setRoo(roo.getText().toString());
            paciente.setBbt(bbt.getText().toString());
            paciente.setKbt(kbt.getText().toString());
            paciente.setEmc(emc.getText().toString());
            paciente.setIse(ise.getText().toString());
            paciente.setFrk(frk.getText().toString());
            paciente.setJcb(jcb.getText().toString());
            paciente.setAm(am.getText().toString());
            paciente.setCc(cc.getText().toString());
            paciente.setCna(cna.getText().toString());
            paciente.setDdor(ddor.getText().toString());
            dao.atualizar(paciente);
            Toast.makeText(this, "FICHA ATUALIZADA COM SUCESSO", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, ListarFichasActivity.class);
            startActivity(it);
        }
    }
}