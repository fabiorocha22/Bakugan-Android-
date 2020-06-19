package br.edu.unifei.ecot12.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifei.ecot12.bakugan.Bakugan;
import br.edu.unifei.ecot12.bakugan.Humano;
import br.edu.unifei.ecot12.bakugan.R;
import br.edu.unifei.ecot12.dao.BakuganDao;
import br.edu.unifei.ecot12.dao.DatabaseHelper;
import br.edu.unifei.ecot12.dao.HumanoDao;

public class EditHumanActivity extends AppCompatActivity {

    private DatabaseHelper dh;
    private HumanoDao humanoDao;
    private BakuganDao bakuganDao;
    private List<Bakugan> bakugans;
    private Humano h;
    private boolean delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_human);

        dh = new DatabaseHelper(EditHumanActivity.this);

        //Pega a Intent que inicou a activity e extrai a string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_ID);

        delete = false;

        Switch switchButton = findViewById(R.id.switchBattleBakugan);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                delete = !delete;
                Toast.makeText(EditHumanActivity.this,
                        "DELETE = " + delete,
                        Toast.LENGTH_SHORT).show();
            }
        });

        try {
            humanoDao = new HumanoDao(dh.getConnectionSource());
            bakuganDao = new BakuganDao(dh.getConnectionSource());

            //GET SPECIFIC LINE BY ID
            h = humanoDao.queryForId(Integer.parseInt(message));

            TextView textView = findViewById(R.id.txtHumanChoosed);
            textView.setText("LISTA DE BAKUGANS DE " + h.getNome().toUpperCase() +
                            " (ID " + message + ")\n\nClique no Bakugan para obter informações\n\n" +
                            "Ative a opção de deletar e clique no Bakugan");
            textView.setTypeface(null, Typeface.BOLD_ITALIC);

            GridView gridview = (GridView) findViewById(R.id.gridBakugans);
            bakugans = new ArrayList<Bakugan>(h.getBakugans());
            BakuganAdapter adapter = new BakuganAdapter(this, bakugans);
            gridview.setAdapter(adapter);

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bakugan b = bakugans.get(i/3);
                    Toast.makeText(EditHumanActivity.this,
                            "Nome: " + b.getNome() +
                                "\nAtributo: " + b.getAtributo().toString() +
                                "\nAtaque: " + b.getAtaque() +
                                "\nDefesa: " + b.getDefesa() +
                                "\nGPower: " + b.getVidaGPower(),
                            Toast.LENGTH_SHORT).show();

                    try {
                        if(delete)
                            bakuganDao.deleteById((int) b.getId());
                        finish();
                        startActivity(getIntent());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
