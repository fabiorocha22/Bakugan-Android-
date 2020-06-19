package br.edu.unifei.ecot12.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;

import java.sql.SQLException;
import java.util.List;

import br.edu.unifei.ecot12.bakugan.Bakugan;
import br.edu.unifei.ecot12.bakugan.Batalha;
import br.edu.unifei.ecot12.bakugan.Combatente;
import br.edu.unifei.ecot12.bakugan.Humano;
import br.edu.unifei.ecot12.bakugan.MediadorRound;
import br.edu.unifei.ecot12.bakugan.R;
import br.edu.unifei.ecot12.dao.BakuganDao;
import br.edu.unifei.ecot12.dao.BatalhaDao;
import br.edu.unifei.ecot12.dao.CombatenteDao;
import br.edu.unifei.ecot12.dao.DatabaseHelper;
import br.edu.unifei.ecot12.dao.HumanoDao;

public class BattleActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private DatabaseHelper dh;
    private HumanoDao humanoDao;
    private BakuganDao bakuganDao;
    private CombatenteDao combatenteDao;
    private BatalhaDao batalhaDao;
    private Humano humano;
    private Combatente combatente1, combatente2;
    private Batalha batalha;
    private List<Humano> humanos;
    private boolean batalhar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        mTextMessage = (TextView) findViewById(R.id.message);

        dh = new DatabaseHelper(BattleActivity.this);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_ID);

        Switch switchButton = findViewById(R.id.switchBattleBakugan);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                batalhar = !batalhar;
                Toast.makeText(BattleActivity.this,
                        "BATALHAR = " + batalhar,
                        Toast.LENGTH_SHORT).show();
            }
        });

        try {
            humanoDao = new HumanoDao(dh.getConnectionSource());

            humano = humanoDao.queryForId(Integer.parseInt(message));
            mTextMessage.setText("LISTA DE ADVERSÁRIOS DE " + humano.getNome().toUpperCase() +
                            " (ID " + message + ")\n\nClique no Bakugan para obter informações\n\n" +
                            "Ative a opção de batalhar e clique no Bakugan");
            mTextMessage.setTypeface(null, Typeface.BOLD_ITALIC);

            if(humano.getBakugans().size() > 0) {
                mTextMessage = findViewById(R.id.info);
                mTextMessage.setText("Nome: " + humano.getNome() +
                        "\nBakugan principal: " + humano.getBakugans().iterator().next().getNome() +
                        "\nAtaque: " + humano.getBakugans().iterator().next().getAtaque() +
                        "\nDefesa: " + humano.getBakugans().iterator().next().getDefesa() +
                        "\nVidaGpower: " + humano.getBakugans().iterator().next().getVidaGPower());
                mTextMessage.setTypeface(null, Typeface.NORMAL);
            }

            humanos = humanoDao.queryForAll();
            int pos = 0;
            for(Humano itHumano : humanos){
                if((int) itHumano.getId() == Integer.parseInt(message)){
                    break;
                }
                pos++;
            }

            humanos.remove(pos);
            GridView gridview = (GridView) findViewById(R.id.gridAdversarios);
            HumanoAdapter adapter = new HumanoAdapter(this, humanos);
            gridview.setAdapter(adapter);

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Humano h = humanos.get(i/3);

                    Toast.makeText(BattleActivity.this,
                            "Nome: " + h.getNome() +
                                "\nBakugan principal: " + h.getBakugans().iterator().next().getNome() +
                                "\nAtaque: " + h.getBakugans().iterator().next().getAtaque() +
                                "\nDefesa: " + h.getBakugans().iterator().next().getDefesa() +
                                "\nVidaGpower: " + h.getBakugans().iterator().next().getVidaGPower(),
                            Toast.LENGTH_SHORT).show();

                    if(batalhar &&
                            humano.getBakugans().size() >=3 &&
                            h.getBakugans().size() >= 3){
                        combatente1 = new Combatente(humano);
                        combatente2 = new Combatente(h);

                        int vit = combatente1.getVitorias();

                        batalha = new Batalha();
                        batalha.setCombatente1(combatente1);
                        batalha.setCombatente2(combatente2);
                        batalha.setRound(new MediadorRound());
                        batalha.batalhar();

                        if(combatente1.getVitorias() > vit){
                            Toast.makeText(BattleActivity.this,
                                    "VOCÊ SAIU VITORIOSO DESTA BATALHA" +
                                            "\nVOCÊ É UM MITO!",
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(BattleActivity.this,
                                    "VOCÊ SAIU DERROTADO DESTA BATALHA" +
                                            "\nPERDEDOR MIZERÁVEL!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
